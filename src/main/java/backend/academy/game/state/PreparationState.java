package backend.academy.game.state;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.GameContext;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.EASY_WORDS;
import static backend.academy.config.GameConfig.HARD_WORDS;
import static backend.academy.config.GameConfig.MEDIUM_WORDS;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.utils.GameUtils.pickRandomObject;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GraphicUtils.DIFFICULTY_MENU;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static backend.academy.utils.GraphicUtils.clearScreen;
import static backend.academy.utils.GraphicUtils.getThemeMenu;

@Log4j2
public class PreparationState extends GameState {
    @Override
    public void gameCycle(GameContext gameContext) {
        clearScreen();
        System.out.print(HANGMAN_PREVIEW);
        System.out.print(MAIN_MENU);

        gameContext.difficulty(GameDifficulty.EMPTY);
        gameContext.theme("");
        log.info("Menu Loaded");
        int mainMenuChoice = readCommand(inputReader, 1, 3);
        switch (mainMenuChoice) {
            case 1 -> nextState(gameContext);
            case 2 -> loadThemeSelector(gameContext);
            case 3 -> loadDifficultySelector(gameContext);
        }
    }

    private void loadThemeSelector(GameContext gameContext) {
        String themeMenu = getThemeMenu(THEMES);
        System.out.print(themeMenu);
        int themeMenuChoice = readCommand(inputReader, 0, THEMES.length);
        if (themeMenuChoice == 0) {
            gameCycle(gameContext);
        }

        //Themes in menu are shifted by 1
        gameContext.theme(THEMES[themeMenuChoice - 1]);
        log.info("Theme {} chosen.", THEMES[themeMenuChoice - 1]);
        gameCycle(gameContext);
    }

    private void loadDifficultySelector(GameContext gameContext) {
        System.out.print(DIFFICULTY_MENU);
        int difficultyMenuChoice = readCommand(inputReader, 0, 3);
        GameDifficulty difficulty = GameDifficulty.EMPTY;
        switch (difficultyMenuChoice) {
            case 0 -> gameCycle(gameContext);
            case 1 -> difficulty = GameDifficulty.EASY;
            case 2 -> difficulty = GameDifficulty.MEDIUM;
            case 3 -> difficulty = GameDifficulty.HARD;
        }
        log.info("Difficulty {} chosen.", difficulty);
        gameContext.difficulty(difficulty);
        gameCycle(gameContext);
    }

    private void randomiseEmptyOptions(GameContext gameContext) {
        if (gameContext.theme().isEmpty()) {
            String theme = pickRandomObject(THEMES);
            gameContext.theme(theme);
        }

        if (gameContext.difficulty() == GameDifficulty.EMPTY) {
            GameDifficulty difficulty =
                pickRandomObject(GameDifficulty.EASY, GameDifficulty.MEDIUM, GameDifficulty.HARD);
            gameContext.difficulty(difficulty);
        }
    }

    private Word selectRandomWord(GameContext gameContext) {
        GameDifficulty difficulty = gameContext.difficulty();
        Word[] wordlist;
        switch (difficulty) {
            case EASY -> wordlist = EASY_WORDS;
            case MEDIUM -> wordlist = MEDIUM_WORDS;
            case HARD -> wordlist = HARD_WORDS;
            default -> throw new IllegalStateException("Unexpected value: " + difficulty);
        }
        wordlist =
            Arrays.stream(wordlist).filter(word -> word.theme().equals(gameContext.theme())).toArray(Word[]::new);
        log.info("All words filtered by theme and difficulty: {}", Arrays.toString(wordlist));
        return pickRandomObject(wordlist);
    }

    @Override
    public void nextState(GameContext gameContext) {
        randomiseEmptyOptions(gameContext);
        gameContext.word(
            selectRandomWord(gameContext));
        log.info("game configured. Difficulty: {}. Theme: {}. Word: {}", gameContext.difficulty(), gameContext.theme(),
            gameContext.word());

        String[] emptyWordArray = new String[gameContext.word().content().length()];
        gameContext.state(
            new InProgressState(0, false, emptyWordArray, new ArrayList<>()));
        gameContext.start();
    }
}
