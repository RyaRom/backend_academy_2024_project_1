package backend.academy.game.state;

import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.GameContext;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.HARD_WORDS;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.utils.GraphicUtils.DIFFICULTY_MENU;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreparationTest {
    private ByteArrayOutputStream outputStream;

    private PrintStream writer;

    private GameContext gameContext;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        writer = new PrintStream(outputStream);
        gameContext = new GameContext();
    }

    @Test
    void noOptions() {
        BufferedReader startGame = new BufferedReader(new StringReader("1\nexit"));

        gameContext.init(startGame, writer);

        assertTrue(outputStream.toString().contains(HANGMAN_PREVIEW));
        assertTrue(outputStream.toString().contains(MAIN_MENU));
        assertFalse(gameContext.theme().isEmpty());
        assertNotSame(gameContext.difficulty(), GameDifficulty.EMPTY);
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }

    @Test
    void selectDifficulty() {
        BufferedReader startGame = new BufferedReader(new StringReader("3\n0\n3\n1\n3\n2\n3\n3\n1\nexit"));

        gameContext.init(startGame, writer);

        assertTrue(outputStream.toString().contains(HANGMAN_PREVIEW));
        assertTrue(outputStream.toString().contains(MAIN_MENU));
        assertTrue(outputStream.toString().contains(DIFFICULTY_MENU));
        assertFalse(gameContext.theme().isEmpty());
        assertEquals(GameDifficulty.HARD, gameContext.difficulty());
        assertTrue(Arrays.stream(HARD_WORDS).anyMatch(w -> w.equals(gameContext.word())));
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }

    @Test
    void selectTheme() {
        int theme = 1;
        BufferedReader startGame = new BufferedReader(new StringReader("2\n3\n2\n%s\n1\nexit".formatted(theme)));

        gameContext.init(startGame, writer);

        assertTrue(outputStream.toString().contains(HANGMAN_PREVIEW));
        assertTrue(outputStream.toString().contains(MAIN_MENU));
        assertEquals(THEMES[theme - 1], gameContext.theme());
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }
}
