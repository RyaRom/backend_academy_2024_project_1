package backend.academy.game;

import backend.academy.data.Word;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordListBuilderTest {

    private GameContext gameContext;

    private ByteArrayOutputStream outputStream;

    private PrintStream outputWriter;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        outputWriter = new PrintStream(outputStream);
        gameContext = new GameContext();
    }

    @Test
    void testCreateWord() throws IOException {
        try (BufferedReader bufferedReader =
                 new BufferedReader(new StringReader("word\ntheme\n\neasy\n5\n"))) {

            gameContext.inputReader(bufferedReader);
            gameContext.outputWriter(outputWriter);
            WordListBuilder builder = new WordListBuilder();

            builder.createWord(gameContext);
            List<Word> words = builder.words();
            assertEquals(1, words.size());

            Word createdWord = words.getFirst();
            assertEquals("word", createdWord.content());
            assertEquals("theme", createdWord.theme());
            assertEquals("", createdWord.hint());
            assertEquals("easy", createdWord.difficulty().name());
            assertEquals(5, createdWord.difficulty().level());
        }
    }

    @Test
    void testChangeName() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new StringReader("newWordList\n"))) {
            WordListBuilder builder = new WordListBuilder();
            gameContext.inputReader(bufferedReader);
            gameContext.outputWriter(outputWriter);

            builder.changeName(gameContext);

            assertEquals("newwordlist", builder.name());
        }
    }

    @AfterEach
    void tearDown() throws IOException {
        outputWriter.close();
        outputStream.close();
    }
}
