package backend.academy.utils;

import backend.academy.data.Word;
import backend.academy.data.enums.WordTheme;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileParserTest {
    @Test
    void testParseValidJson() throws IOException {
        File validJsonFile =
            createTemporaryFile(
                "{\"content\":\"testWord\",\"theme\":\"ANIMALS\",\"hint\":\"testHint\"}"
            );

        Word word = FileParser.parseJson(validJsonFile, Word.class);

        assertEquals("testWord", word.content());
        assertEquals(WordTheme.ANIMALS, word.theme());
        assertEquals("testHint", word.hint());
    }

    @Test
    void testParseInvalidJson() throws IOException {
        File invalidJsonFile =
            createTemporaryFile(
                "{\"content\":\"test\",\"hint\":}"
            );

        assertThrows(RuntimeException.class, () -> FileParser.parseJson(invalidJsonFile, Word.class));
    }

    private File createTemporaryFile(String content) throws IOException {
        File tempFile = File.createTempFile("temp", ".json");
        try (java.io.FileWriter writer = new java.io.FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }
}
