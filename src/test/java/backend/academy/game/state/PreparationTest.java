package backend.academy.game.state;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.exception.NoWordsWithParametersException;
import backend.academy.game.GameContext;
import backend.academy.repo.SimpleWordRepository;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.NO_WORD_TEXT;
import static backend.academy.config.GameConfig.WORD_FILE_LOCATION;
import static backend.academy.utils.FileParser.parseJsonToWordList;
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
        assertTrue(outputStream.toString().contains(MAIN_MENU
            .formatted(gameContext.theme(), gameContext.difficulty())));
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
        assertTrue(outputStream.toString().contains(MAIN_MENU
            .formatted(gameContext.theme(), gameContext.difficulty())));
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
        assertTrue(outputStream.toString().contains(MAIN_MENU
            .formatted(gameContext.theme(), gameContext.difficulty())));
        assertEquals(
            parseJsonToWordList(new File(WORD_FILE_LOCATION))
                .stream().map(Word::theme)
                .map(String::toUpperCase)
                .distinct()
                .toList().get(theme + 1),
            gameContext.theme());
        assertEquals(gameContext.theme(), gameContext.word().theme());
        assertEquals(InProgressState.class, gameContext.state().getClass());
    }

    @Test
    void noWordsWithThemeDifficulty()
        throws NoSuchFieldException, IllegalAccessException {
        var words = Instancio.createList(Word.class);
        var repo = new SimpleWordRepository(words);
        startGame = new BufferedReader(new StringReader("1\n4"));

        PreparationState state = new PreparationState(repo);
        changePrivateField("state", gameContext, state);
        changePrivateField("theme", gameContext, "newTheme");
        changePrivateField("difficulty", gameContext, Instancio.create(Difficulty.class));
        changePrivateField("outputWriter", gameContext, writer);
        changePrivateField("inputReader", gameContext, startGame);

        state.gameCycle(gameContext);

        assertTrue(outputStream.toString().contains(NO_WORD_TEXT));
    }

    @AfterEach
    void tearDown() throws IOException {
        outputStream.close();
        writer.close();
        startGame.close();
    }

    private void changePrivateField(String name, Object obj, Object newVal)
        throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, newVal);
    }
}
