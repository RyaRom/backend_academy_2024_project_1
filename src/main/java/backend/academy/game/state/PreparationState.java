package backend.academy.game.state;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.game.GameContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.CUSTOM_WORD_FILE_LOCATION;
import static backend.academy.config.GameConfig.globalDifficulties;
import static backend.academy.config.GameConfig.globalThemes;
import static backend.academy.config.GameConfig.wordsList;
import static backend.academy.utils.FileParser.addWordsFromJson;
import static backend.academy.utils.FileParser.getJsonInDir;
import static backend.academy.utils.GameUtils.pickRandomObject;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static backend.academy.utils.GraphicUtils.clearScreen;
import static backend.academy.utils.GraphicUtils.getCustomMenu;

@Log4j2
@NoArgsConstructor
@SuppressWarnings({"MagicNumber", "MultipleStringLiterals", "PATH_TRAVERSAL_IN"})
public class PreparationState implements GameState {
    private List<String> localThemes = globalThemes;

    private List<Difficulty> localDifficulties = globalDifficulties;

    @Override
    public void gameCycle(GameContext gameContext) {
        if (gameContext.terminate()) {
            return;
        }

        if (localDifficulties.isEmpty() || localThemes.isEmpty()) {
            log.warn("No words available for chosen theme or difficulty {}, {}",
                gameContext.theme(), gameContext.difficulty());
            localThemes = globalThemes;
            localDifficulties = globalDifficulties;
        }

        clearScreen(gameContext.outputWriter());
        gameContext.outputWriter().print(HANGMAN_PREVIEW);
        gameContext.outputWriter().print(MAIN_MENU);

        log.info("Menu Loaded");
        int mainMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            1, 5);
        switch (mainMenuChoice) {
            case 1 -> nextState(gameContext);
            case 2 -> loadThemeSelector(gameContext);
            case 3 -> loadDifficultySelector(gameContext);
            case 4 -> gameContext.finish();
            case 5 -> loadCustomWordsSelector(gameContext);
            default -> throw new IllegalStateException("Unexpected value: " + mainMenuChoice);
        }
    }

    private void loadThemeSelector(GameContext gameContext) {
        String themeMenu = getCustomMenu(localThemes, "theme");
        gameContext.outputWriter().print(themeMenu);
        int themeMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, localThemes.size());
        if (themeMenuChoice == 0) {
            gameCycle(gameContext);
            return;
        }

        //Themes in menu are shifted by 1
        String theme = localThemes.get(themeMenuChoice - 1);
        gameContext.theme(theme);
        log.info("Theme {} chosen.", theme);

        localDifficulties = wordsList.stream()
            .filter(word -> word.theme().equalsIgnoreCase(theme))
            .map(Word::difficulty)
            .distinct()
            .toList();

        gameCycle(gameContext);
    }

    private void loadDifficultySelector(GameContext gameContext) {
        gameContext.outputWriter().print(
            getCustomMenu(localDifficulties.stream().map(Difficulty::name).toList(),
                "difficulty"));
        int difficultyMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, localDifficulties.size());

        if (difficultyMenuChoice == 0) {
            gameCycle(gameContext);
            return;
        }

        Difficulty difficulty = localDifficulties.get(difficultyMenuChoice - 1);
        gameContext.difficulty(difficulty);
        log.info("Difficulty {} chosen.", difficulty);

        localThemes = wordsList.stream()
            .filter(word -> word.difficulty().equals(difficulty))
            .map(Word::theme)
            .distinct()
            .toList();

        gameCycle(gameContext);
    }

    private void loadCustomWordsSelector(GameContext gameContext) {
        String[] customWords = getJsonInDir(CUSTOM_WORD_FILE_LOCATION);
        String customWordsMenu = "custom wordlist (path: %s):".formatted(CUSTOM_WORD_FILE_LOCATION);
        gameContext.outputWriter().println(getCustomMenu(
            Arrays.stream(customWords).toList(),
            customWordsMenu));

        int customWordsMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, customWords.length);
        if (customWordsMenuChoice == 0) {
            gameCycle(gameContext);
            return;
        }

        String chosenPath = CUSTOM_WORD_FILE_LOCATION + customWords[customWordsMenuChoice - 1];
        addWordsFromJson(chosenPath);

        localThemes = globalThemes;
        localDifficulties = globalDifficulties;

        log.info("Custom words path {} chosen.", chosenPath);
        gameCycle(gameContext);
    }

    private Word selectRandomWord(GameContext gameContext) {
        Set<Word> wordlist =
            wordsList.stream()
                .filter(word -> word.theme().equalsIgnoreCase(gameContext.theme()))
                .filter(word -> word.difficulty().equals(gameContext.difficulty()))
                .collect(Collectors.toSet());
        log.info("All words filtered by theme and difficulty: {}", wordlist);
        return pickRandomObject(wordlist);
    }

    @Override
    public void nextState(GameContext gameContext) {
        log.info("Before game configuration. Difficulty: {}. Theme: {}.",
            gameContext.difficulty(),
            gameContext.theme());

        gameContext.word(
            selectRandomWord(gameContext));

        log.info("Game configured. Difficulty: {}. Theme: {}. Word: {}",
            gameContext.difficulty(), gameContext.theme(),
            gameContext.word());

        String[] emptyWordArray = new String[gameContext.word().length()];
        Arrays.fill(emptyWordArray, "");
        gameContext.state(
            new InProgressState(0, false, emptyWordArray, new HashSet<>()));

        log.info("Game is started");
        gameContext.state().gameCycle(gameContext);
    }
}
