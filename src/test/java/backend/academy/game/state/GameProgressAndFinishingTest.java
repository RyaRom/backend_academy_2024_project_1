package backend.academy.game.state;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.GameContext;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.EASY_WORDS;
import static backend.academy.utils.GraphicUtils.DEATH_SCREEN;
import static backend.academy.utils.GraphicUtils.GAME_STAGES;
import static backend.academy.utils.GraphicUtils.NO_HINT_TEXT;
import static backend.academy.utils.GraphicUtils.VICTORY_SCREEN;
import static backend.academy.utils.GraphicUtils.getHangmanWordString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameProgressAndFinishingTest {

    private final Word word = EASY_WORDS[0];

    private final GameDifficulty difficulty = GameDifficulty.EASY;

    private ByteArrayOutputStream outputStream;

    private GameContext gameContext;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        PrintStream writer = new PrintStream(outputStream);

        gameContext = new GameContext();
        gameContext.word(word);
        gameContext.outputWriter(writer);
        gameContext.difficulty(difficulty);
        gameContext.theme(word.theme());
        String[] emptyWordArray = new String[gameContext.word().content().length()];
        Arrays.fill(emptyWordArray, "");
        gameContext.state(new InProgressState(0, false, emptyWordArray, new HashSet<>()));
    }

    @Test
    void looseGame() {
        BufferedReader gameInput =
            new BufferedReader(new StringReader("q\ns\ne\nr\nn\ny\n.\n>>\ni\no\np\nl\nk\nj\nh\nq\ny\ng\nd\nm\nl\n2"));
        gameContext.inputReader(gameInput);

        gameContext.state().gameCycle(gameContext);
        String output = outputStream.toString();

        Arrays.stream(GAME_STAGES).forEach(stage ->
            assertTrue(output.contains(stage)));
        assertTrue(output.contains(word.theme().toString()));
        assertTrue(output.contains(difficulty.toString().toLowerCase()));
        assertTrue(output.contains(NO_HINT_TEXT));
        assertTrue(output.contains(DEATH_SCREEN.formatted(word.content())));
        assertTrue(output.contains(getHangmanWordString(new String[] {"", "", ""})));
        assertTrue(output.contains(getHangmanWordString(new String[] {"s", "", ""})));
        assertTrue(output.contains(getHangmanWordString(new String[] {"s", "", "n"})));
        assertTrue(output.contains(getHangmanWordString(new String[] {"s", "", "n"})));
        assertEquals(FinishedState.class, gameContext.state().getClass());
        FinishedState state = (FinishedState) gameContext.state();
        assertFalse(state.isVictory());
    }

    @Test
    void winGame() {
        BufferedReader gameInput =
            new BufferedReader(new StringReader("w\ns\nu\nn\n2"));
        gameContext.inputReader(gameInput);

        gameContext.state().gameCycle(gameContext);
        String output = outputStream.toString();

        assertTrue(output.contains(GAME_STAGES[0]));
        assertTrue(output.contains(GAME_STAGES[1]));
        assertFalse(output.contains(GAME_STAGES[2]));
        assertTrue(output.contains(word.theme().toString()));
        assertTrue(output.contains(difficulty.toString().toLowerCase()));
        assertTrue(output.contains(NO_HINT_TEXT));
        assertTrue(output.contains(VICTORY_SCREEN.formatted(word.content())));
        assertTrue(output.contains(getHangmanWordString(new String[] {"", "", ""})));
        assertTrue(output.contains(getHangmanWordString(new String[] {"s", "", ""})));
        assertTrue(output.contains(getHangmanWordString(new String[] {"s", "u", ""})));
        assertEquals(FinishedState.class, gameContext.state().getClass());
        FinishedState state = (FinishedState) gameContext.state();
        assertTrue(state.isVictory());
    }

    @Test
    void sameLetterCorrect() {
        BufferedReader gameInput = new BufferedReader(new StringReader("s\ns\ns\ns\ns\ns\nexit"));
        gameContext.inputReader(gameInput);

        gameContext.state().gameCycle(gameContext);
        String output = outputStream.toString();

        assertFalse(output.contains(GAME_STAGES[1]));
        assertTrue(output.contains(getHangmanWordString(new String[] {"s", "", ""})));
    }

    @Test
    void sameLetterIncorrect() {
        gameContext.difficulty(GameDifficulty.HARD);
        BufferedReader gameInput = new BufferedReader(new StringReader("w\nw\nw\nw\nw\nw\nw\nw\nw\nw\n2"));
        gameContext.inputReader(gameInput);

        gameContext.state().gameCycle(gameContext);
        String output = outputStream.toString();

        assertFalse(output.contains(GAME_STAGES[1]));
        assertTrue(output.contains(GAME_STAGES[0]));
        assertTrue(output.contains(GAME_STAGES[3]));
        assertTrue(output.contains(GAME_STAGES[6]));
        assertTrue(output.contains(GAME_STAGES[9]));
        assertTrue(output.contains(GAME_STAGES[12]));
        assertTrue(output.contains(getHangmanWordString(new String[] {"", "", ""})));
        assertTrue(output.contains("Wrong letters: W"));
    }

    @Test
    void hint() {
        BufferedReader gameInput = new BufferedReader(new StringReader("Help\nexit"));
        gameContext.inputReader(gameInput);

        gameContext.state().gameCycle(gameContext);
        String output = outputStream.toString();

        assertFalse(output.contains(GAME_STAGES[1]));
        assertTrue(output.contains(getHangmanWordString(new String[] {"", "", ""})));
        assertTrue(output.contains(word.hint()));
    }

    @Test
    void restartAfterFinish() {
        BufferedReader gameInput = new BufferedReader(new StringReader("s\nu\nn\n1\n4"));
        gameContext.inputReader(gameInput);

        gameContext.state().gameCycle(gameContext);

        assertEquals(PreparationState.class, gameContext.state().getClass());
    }
}
