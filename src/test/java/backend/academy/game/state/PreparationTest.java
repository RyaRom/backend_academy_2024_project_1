package backend.academy.game.state;

import backend.academy.config.GameConfig;
import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.data.enums.WordTheme;
import backend.academy.game.GameContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.CUSTOM_WORD_FILE_LOCATION;
import static backend.academy.config.GameConfig.EASY_WORDS;
import static backend.academy.config.GameConfig.HARD_WORDS;
import static backend.academy.config.GameConfig.MEDIUM_WORDS;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.utils.FileParser.WordConfig;
import static backend.academy.utils.FileParser.getJsonInDir;
import static backend.academy.utils.GraphicUtils.DIFFICULTY_MENU;
import static backend.academy.utils.GraphicUtils.HANGMAN_PREVIEW;
import static backend.academy.utils.GraphicUtils.MAIN_MENU;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        assertNotNull(gameContext.theme());
        assertNotNull(gameContext.difficulty());
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
        assertNotNull(gameContext.difficulty());
        assertEquals(GameDifficulty.HARD, gameContext.difficulty());
        assertTrue(HARD_WORDS.stream().anyMatch(w -> w.equals(gameContext.word())));
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

    @Test
    void addCustomWordlist() throws IOException {
        Word easy = new Word("testEasy", WordTheme.SCIENCE, "test");
        Word medium = new Word("testMedium", WordTheme.SCIENCE, "test");
        Word hard = new Word("testHard", WordTheme.SCIENCE, "test");
        var wordlist = new WordConfig(
            List.of(easy),
            List.of(medium),
            List.of(hard)
        );
        File words = new File(GameConfig.CUSTOM_WORD_FILE_LOCATION + "/test_wordlist.json");
        new ObjectMapper().writeValue(words, wordlist);

        int option = getJsonInDir(CUSTOM_WORD_FILE_LOCATION).length;
        BufferedReader startGame = new BufferedReader(new StringReader("5\n%s\n4".formatted(option)));

        gameContext.init(startGame, writer);

        assertTrue(EASY_WORDS.contains(easy));
        assertTrue(MEDIUM_WORDS.contains(medium));
        assertTrue(HARD_WORDS.contains(hard));

        Files.delete(words.toPath());
    }
}
