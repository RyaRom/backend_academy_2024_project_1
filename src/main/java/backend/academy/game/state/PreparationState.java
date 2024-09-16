package backend.academy.game.state;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.GameContext;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.CUSTOM_WORD_FILE_LOCATION;
import static backend.academy.config.GameConfig.EASY_WORDS;
import static backend.academy.config.GameConfig.HARD_WORDS;
import static backend.academy.config.GameConfig.MEDIUM_WORDS;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.utils.FileParser.addWordsFromJson;
import static backend.academy.utils.FileParser.getJsonInDir;
import static backend.academy.utils.GameUtils.pickRandomObject;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GraphicUtils.DIFFICULTY_MENU;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static backend.academy.utils.GraphicUtils.clearScreen;
import static backend.academy.utils.GraphicUtils.getCustomWordsMenu;
import static backend.academy.utils.GraphicUtils.getThemeMenu;

@Log4j2
@NoArgsConstructor
@SuppressWarnings({"MagicNumber", "MultipleStringLiterals", "PATH_TRAVERSAL_IN"})
public class PreparationState implements GameState {
    @Override
    public void gameCycle(GameContext gameContext) {
        if (gameContext.terminate()) {
            return;
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
        String themeMenu = getThemeMenu(THEMES);
        gameContext.outputWriter().print(themeMenu);
        int themeMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, THEMES.length);
        if (themeMenuChoice == 0) {
            gameCycle(gameContext);
            return;
        }

        //Themes in menu are shifted by 1
        gameContext.theme(THEMES[themeMenuChoice - 1]);
        log.info("Theme {} chosen.", THEMES[themeMenuChoice - 1]);
        gameCycle(gameContext);
    }

    private void loadDifficultySelector(GameContext gameContext) {
        gameContext.outputWriter().print(DIFFICULTY_MENU);
        int difficultyMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, 3);

        if (difficultyMenuChoice == 0) {
            gameCycle(gameContext);
            return;
        }

        GameDifficulty difficulty = switch (difficultyMenuChoice) {
            case 1 -> GameDifficulty.EASY;
            case 2 -> GameDifficulty.MEDIUM;
            case 3 -> GameDifficulty.HARD;
            default -> throw new IllegalStateException("Unexpected value: " + difficultyMenuChoice);
        };
        log.info("Difficulty {} chosen.", difficulty);
        gameContext.difficulty(difficulty);
        gameCycle(gameContext);
    }

    private void loadCustomWordsSelector(GameContext gameContext) {
        String[] customWords = getJsonInDir(CUSTOM_WORD_FILE_LOCATION);
        gameContext.outputWriter().println(getCustomWordsMenu(customWords));

        int customWordsMenuChoice = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, customWords.length);
        if (customWordsMenuChoice == 0) {
            gameCycle(gameContext);
            return;
        }

        String chosenPath = CUSTOM_WORD_FILE_LOCATION + "/" + customWords[customWordsMenuChoice - 1];
        addWordsFromJson(chosenPath);

        log.info("Custom words path {} chosen.", chosenPath);
        gameCycle(gameContext);
    }

    private Word selectRandomWord(GameContext gameContext) {
        GameDifficulty difficulty = gameContext.difficulty();
        var wordlist = switch (difficulty) {
            case EASY -> EASY_WORDS;
            case MEDIUM -> MEDIUM_WORDS;
            case HARD -> HARD_WORDS;
        };
        wordlist =
            wordlist.stream()
                .filter(word -> word.theme() == gameContext.theme())
                .collect(Collectors.toSet());
        log.info("All words filtered by theme and difficulty: {}", wordlist);
        return pickRandomObject(wordlist);
    }

    @Override
    public void nextState(GameContext gameContext) {
        log.info("Before game configuration. Difficulty: {}. Theme: {}.", gameContext.difficulty(),
            gameContext.theme());

        gameContext.word(
            selectRandomWord(gameContext));

        log.info("Game configured. Difficulty: {}. Theme: {}. Word: {}", gameContext.difficulty(), gameContext.theme(),
            gameContext.word());

        String[] emptyWordArray = new String[gameContext.word().length()];
        Arrays.fill(emptyWordArray, "");
        gameContext.state(
            new InProgressState(0, false, emptyWordArray, new HashSet<>()));

        log.info("Game is started");
        gameContext.state().gameCycle(gameContext);
    }
}
