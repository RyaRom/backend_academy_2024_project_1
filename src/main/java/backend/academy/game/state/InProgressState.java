package backend.academy.game.state;

import backend.academy.data.Difficulty;
import backend.academy.game.GameContext;
import java.util.Arrays;
import java.util.Locale;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.EMPTY_HINT_TEXTS;
import static backend.academy.config.GameConfig.EXIT_COMMAND;
import static backend.academy.config.GameConfig.HELP_COMMAND;
import static backend.academy.config.GameConfig.STAGES;
import static backend.academy.config.GameConfig.globalDifficulties;
import static backend.academy.utils.GameUtils.pickRandomObject;
import static backend.academy.utils.GameUtils.readLetter;
import static backend.academy.utils.GraphicUtils.GAME_STAGES;
import static backend.academy.utils.GraphicUtils.NO_HINT_TEXT;
import static backend.academy.utils.GraphicUtils.WORD_MENU;
import static backend.academy.utils.GraphicUtils.clearScreen;
import static backend.academy.utils.GraphicUtils.getHangmanWordString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class InProgressState implements GameState {
    private Integer gameStage;

    private Boolean hintEnabled;

    private String[] guessedLetters;

    private Set<String> wrongLetters;

    @Override
    public void gameCycle(GameContext gameContext) {
        if (gameContext.terminate() || inProgressValidation(gameContext)) {
            return;
        }

        clearScreen(gameContext.outputWriter());
        gameContext.outputWriter().print(GAME_STAGES[gameStage]);
        printGameMenu(gameContext);
        readInput(gameContext);
    }

    @Override
    public void nextState(GameContext gameContext) {

    }

    private void finishGame(GameContext gameContext, boolean isVictory) {
        gameContext.state(new FinishedState(isVictory));
        gameContext.state().gameCycle(gameContext);
    }

    private boolean inProgressValidation(GameContext gameContext) {
        if (Arrays.stream(guessedLetters).noneMatch(String::isEmpty)) {
            log.info("Game finished. Victory");
            finishGame(gameContext, true);
            return true;
        } else if (STAGES - gameStage < 0) {
            log.info("Game finished. Death");
            finishGame(gameContext, false);
            return true;
        }
        return false;
    }

    private void readInput(GameContext gameContext) {
        String letter = readLetter(gameContext.inputReader(), gameContext.outputWriter());
        log.info("Read letter {}", letter);
        if (HELP_COMMAND.equals(letter)) {
            hintEnabled = !hintEnabled;
            gameCycle(gameContext);
            return;
        }
        if (EXIT_COMMAND.equals(letter)) {
            gameContext.finish();
        }
        String wordText = gameContext.word().toString();
        if (!wordText.toUpperCase(Locale.ROOT).contains(letter)) {
            incorrectLetterAction(gameContext, letter);
        } else {
            correctLetterAction(gameContext, letter);
        }
        gameCycle(gameContext);
    }

    private void incorrectLetterAction(GameContext gameContext, String letter) {
        log.info("Letter {} is incorrect. Word: {}.", letter, gameContext.word());
        if (!wrongLetters.add(letter)) {
            return;
        }
        int step = globalDifficulties.stream()
            .filter(dif -> gameContext.difficulty().equals(dif))
            .map(Difficulty::level)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No such difficulty"));

        gameStage += step;
    }

    private void correctLetterAction(GameContext gameContext, String guessedLetter) {
        log.info("Letter {} is correct. Word: {}.", guessedLetter, gameContext.word());
        char[] word = gameContext.word().content().toCharArray();
        for (int i = 0; i < word.length; i++) {
            if (("" + word[i]).toUpperCase().equals(guessedLetter)) {
                guessedLetters[i] = guessedLetter;
            }
        }
    }

    private void printGameMenu(GameContext gameContext) {
        String wordLetters = getHangmanWordString(guessedLetters);
        String wrongLettersString = String.join(", ", wrongLetters);
        String theme = gameContext.theme();
        String difficulty = gameContext.difficulty().toString();
        String attempts = String.valueOf(STAGES - gameStage);

        String hint = hintEnabled ? gameContext.word().hint() : NO_HINT_TEXT;
        if (hint == null || hint.isEmpty() || hint.isBlank()) {
            hint = pickRandomObject(EMPTY_HINT_TEXTS);
        }

        String menu = WORD_MENU.formatted(
            wordLetters, wrongLettersString, hint, theme, difficulty, attempts);
        gameContext.outputWriter().println(menu);
    }
}
