package backend.academy.game.state;

import backend.academy.game.GameContext;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PreparationTest {
    private ByteArrayOutputStream outputStream;

    private PrintStream writer;

    private GameContext gameContext;

    private BufferedReader startGame;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        writer = new PrintStream(outputStream);
        gameContext = new GameContext();
    }

    @Test
    void noOptions() {
        startGame = new BufferedReader(new StringReader("1\nexit"));

        gameContext.init(startGame, writer);

        assertTrue(outputStream.toString().contains(HANGMAN_PREVIEW));
        assertTrue(outputStream.toString().contains(MAIN_MENU));
        assertNotNull(gameContext.theme());
        assertNotNull(gameContext.difficulty());
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }

    @Test
    void selectDifficulty() {
        startGame = new BufferedReader(new StringReader("3\n0\n3\n1\n3\n2\n3\n3\n1\nexit"));

        gameContext.init(startGame, writer);

        assertTrue(outputStream.toString().contains(HANGMAN_PREVIEW));
        assertTrue(outputStream.toString().contains(MAIN_MENU));
        assertNotNull(gameContext.difficulty());
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }

    @Test
    void selectTheme() {
        int theme = 1;
        startGame = new BufferedReader(new StringReader("2\n3\n2\n%s\n1\nexit".formatted(theme)));

        gameContext.init(startGame, writer);

        assertTrue(outputStream.toString().contains(HANGMAN_PREVIEW));
        assertTrue(outputStream.toString().contains(MAIN_MENU));
        assertEquals(THEMES.get(theme - 1), gameContext.theme());
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }

    @AfterEach
    void tearDown() throws IOException {
        outputStream.close();
        writer.close();
        startGame.close();
    }
}
