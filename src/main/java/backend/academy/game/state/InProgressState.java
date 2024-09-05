package backend.academy.game.state;

import backend.academy.game.GameContext;
import java.util.Locale;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.STAGES;
import static backend.academy.utils.GameUtils.HELP_COMMAND;
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
public class InProgressState extends GameState {
    private Integer gameStage;
    private Boolean hintEnabled;
    private String[] guessedLetters;
    private Set<String> wrongLetters;

    @Override
    public void gameCycle(GameContext gameContext) {
        inProgressValidation(gameContext);

        clearScreen();
        System.out.print(GAME_STAGES[gameStage]);
        printGameMenu(gameContext);
        readInput(gameContext);
    }

    @Override
    public void nextState(GameContext gameContext) {

    }

    private void inProgressValidation(GameContext gameContext) {

    }

    private void readInput(GameContext gameContext) {
        String letter = readLetter(inputReader);
        if (letter.equals(HELP_COMMAND)) {
            hintEnabled = !hintEnabled;
            gameCycle(gameContext);
        }
        String wordText = gameContext.word().content();
        if (!wordText.toUpperCase(Locale.ROOT).contains(letter)) {
            incorrectLetterAction(gameContext, letter);
        } else {
            correctLetterAction(gameContext, letter);
        }
        gameCycle(gameContext);
    }

    private void incorrectLetterAction(GameContext gameContext, String letter) {
        log.info("Letter {} is incorrect. Word: {}.", letter, gameContext.word().content());
        wrongLetters.add(letter);
        switch (gameContext.difficulty()) {
            case EASY -> gameStage++;
            case MEDIUM -> gameStage += 2;
            case HARD -> gameStage += 3;
        }
    }

    private void correctLetterAction(GameContext gameContext, String guessedLetter) {
        log.info("Letter {} is correct. Word: {}.", guessedLetter, gameContext.word().content());
        char[] word = gameContext.word().content().toCharArray();
        for (int i = 0; i < word.length; i++) {
            if (("" + word[i]).toUpperCase().equals(guessedLetter)) {
                guessedLetters[i] = guessedLetter;
            }
        }
    }

    private void printGameMenu(GameContext gameContext) {
        String wordLetters = getHangmanWordString(guessedLetters);
        String guessedLetters = String.join(", ", wrongLetters);
        String hint = hintEnabled ? gameContext.word().hint() : NO_HINT_TEXT;
        String theme = gameContext.theme();
        String difficulty = gameContext.difficulty().toString().toLowerCase(Locale.ROOT);
        String attempts = String.valueOf(STAGES - gameStage);
        String menu = WORD_MENU.formatted(wordLetters, guessedLetters, hint, theme, difficulty, attempts);
        System.out.println(menu);
    }
}
