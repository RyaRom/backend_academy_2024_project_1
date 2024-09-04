package backend.academy.game.state;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.GameContext;
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

public class PreparationState extends GameState {
    @Override
    public void gameCycle(GameContext gameContext) {
        clearScreen();
        System.out.println(HANGMAN_PREVIEW);
        System.out.println(MAIN_MENU);

        gameContext.difficulty(GameDifficulty.EMPTY);
        gameContext.theme("");
        int mainMenuChoice = readCommand(inputReader, 1, 3);
        switch (mainMenuChoice) {
            case 1 -> nextState(gameContext);
            case 2 -> loadThemeSelector(gameContext);
            case 3 -> loadDifficultySelector(gameContext);
        }
    }

    private void loadThemeSelector(GameContext gameContext) {
        String themeMenu = getThemeMenu(THEMES);
        System.out.println(themeMenu);
        int themeMenuChoice = readCommand(inputReader, 0, THEMES.length);
        if (themeMenuChoice == 0) {
            gameCycle(gameContext);
        }

        //Themes in menu are shifted by 1
        gameContext.theme(THEMES[themeMenuChoice - 1]);
        gameCycle(gameContext);
    }

    private void loadDifficultySelector(GameContext gameContext) {
        System.out.println(DIFFICULTY_MENU);
        int difficultyMenuChoice = readCommand(inputReader, 0, 3);
        switch (difficultyMenuChoice) {
            case 0 -> gameCycle(gameContext);
            case 1 -> gameContext.difficulty(GameDifficulty.EASY);
            case 2 -> gameContext.difficulty(GameDifficulty.MEDIUM);
            case 3 -> gameContext.difficulty(GameDifficulty.HARD);
        }
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

    private Word selectRandomWord(GameDifficulty difficulty) {
        return switch (difficulty) {
            case EASY -> pickRandomObject(EASY_WORDS);
            case MEDIUM -> pickRandomObject(MEDIUM_WORDS);
            case HARD -> pickRandomObject(HARD_WORDS);
            default -> throw new IllegalStateException("Unexpected value: " + difficulty);
        };
    }

    @Override
    public void nextState(GameContext gameContext) {
        randomiseEmptyOptions(gameContext);
        gameContext.word(
            selectRandomWord(gameContext.difficulty()));

        gameContext.state(new InProgressState());
        gameContext.start();
    }
}
