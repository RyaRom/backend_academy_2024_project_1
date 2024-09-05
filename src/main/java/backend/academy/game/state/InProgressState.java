package backend.academy.game.state;

import backend.academy.game.GameContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;
import static backend.academy.utils.GraphicUtils.*;
import static backend.academy.utils.GameUtils.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InProgressState extends GameState {
    private Integer gameStage;
    private Boolean hintEnabled;
    private String[] guessedLetters;
    private List<String> wrongLetters;

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
        if (!wordText.contains(letter)) {
            incorrectLetterAction(gameContext, letter);
        } else {
            correctLetterAction(gameContext, letter);
        }
        gameCycle(gameContext);
    }

    private void incorrectLetterAction(GameContext gameContext, String letter) {
        wrongLetters.add(letter);
        switch (gameContext.difficulty()) {
            case EASY -> gameStage++;
            case MEDIUM -> gameStage += 2;
            case HARD -> gameStage += 3;
        }
    }

    private void correctLetterAction(GameContext gameContext, String guessedLetter) {
        char[] word = gameContext.word().content().toCharArray();
        for (int i = 0; i < word.length; i++) {
            if (word[i] == guessedLetter.charAt(0)) {
                guessedLetters[i] = guessedLetter;
            }
        }
    }

    private void printGameMenu(GameContext gameContext) {
        String wordLetters = getHangmanWordString(guessedLetters);
        String guessedLetters = String.join(", ", wrongLetters);
        String hint = hintEnabled ? gameContext.word().hint() : NO_HINT_TEXT;
        String theme = gameContext.theme();
        String menu = WORD_MENU.formatted(wordLetters, guessedLetters, hint, theme);
        System.out.println(menu);
    }
}
