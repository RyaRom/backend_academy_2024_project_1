package backend.academy.game.state;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.exception.NoWordsWithParametersException;
import backend.academy.game.GameContext;
import backend.academy.repo.SimpleWordRepository;
import backend.academy.repo.WordRepository;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.CUSTOM_WORD_FILE_LOCATION;
import static backend.academy.config.GameConfig.NO_WORD_TEXT;
import static backend.academy.config.GameConfig.WORD_FILE_LOCATION;
import static backend.academy.utils.FileParser.getJsonInDir;
import static backend.academy.utils.FileParser.parseJsonToWordList;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static backend.academy.utils.GraphicUtils.clearScreen;
import static backend.academy.utils.GraphicUtils.getCustomMenu;

@Log4j2
@RequiredArgsConstructor
@SuppressWarnings({"MagicNumber", "MultipleStringLiterals", "PATH_TRAVERSAL_IN"})
public class PreparationState implements GameState {

    private final WordRepository wordRepository;

    private List<String> localThemes;

    private List<Difficulty> localDifficulties;

    private boolean noWordError = false;

    public PreparationState() {
        this.wordRepository = new SimpleWordRepository(parseJsonToWordList(new File(WORD_FILE_LOCATION)));
    }

    public void loadContext(GameContext gameContext) {
        try {
            updateLocalContext();
            gameContext.difficulty(wordRepository.getRandomDifficulty());
            gameContext.theme(wordRepository.getRandomTheme());
        } catch (NoWordsWithParametersException e) {
            noWordError = true;
        }
        gameCycle(gameContext);
    }

    private void updateLocalContext() throws NoWordsWithParametersException {
        localThemes = wordRepository.getThemes();
        localDifficulties = wordRepository.getDifficulties();
    }

    @Override
    public void gameCycle(GameContext gameContext) {
        if (gameContext.terminate()) {
            return;
        }

        clearScreen(gameContext.outputWriter());
        gameContext.outputWriter().print(HANGMAN_PREVIEW);
        gameContext.outputWriter().print(MAIN_MENU
            .formatted(gameContext.theme(), gameContext.difficulty().toString())
        );

        if (noWordError) {
            gameContext.outputWriter().println(NO_WORD_TEXT);
            noWordError = false;
        }

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

        gameCycle(gameContext);
    }

    private void loadCustomWordsSelector(GameContext gameContext) {
        String[] customWords = getJsonInDir(CUSTOM_WORD_FILE_LOCATION);
        String customWordsMenu = "custom wordlist (path: %s)".formatted(CUSTOM_WORD_FILE_LOCATION);
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
        log.info("Adding words from JSON file: {}", chosenPath);

        File json = new File(chosenPath);
        List<Word> newWords = parseJsonToWordList(json);
        try {
            wordRepository.addWords(newWords);
            updateLocalContext();
        } catch (NoWordsWithParametersException e) {
            noWordError = true;
        }

        gameCycle(gameContext);
    }

    @Override
    public void nextState(GameContext gameContext) {
        log.info("Before game configuration. Difficulty: {}. Theme: {}.",
            gameContext.difficulty(),
            gameContext.theme());

        try {
            gameContext.word(wordRepository.getRandomWordByThemeAndDifficulty(
                gameContext.theme(), gameContext.difficulty()
            ));
        } catch (NoWordsWithParametersException e) {
            noWordError = true;
            gameCycle(gameContext);
            return;
        }

        log.info("Game configured. Difficulty: {} {}. Theme: {}. Word: {}",
            gameContext.difficulty(), gameContext.difficulty().level(), gameContext.theme(),
            gameContext.word());

        String[] emptyWordArray = new String[gameContext.word().length()];
        Arrays.fill(emptyWordArray, "");
        gameContext.state(
            new InProgressState(0, false, emptyWordArray, new HashSet<>()));

        log.info("Game is started");
        gameContext.state().gameCycle(gameContext);
    }
}
