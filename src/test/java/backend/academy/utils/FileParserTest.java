package backend.academy.utils;

import backend.academy.data.Word;
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
                "[{\"content\":\"testWord\",\"theme\":\"ANIMALS\",\"hint\":\"testHint\"}]"
            );

        Word word = FileParser.parseJsonToWordList(validJsonFile).getFirst();

        assertEquals("testWord", word.content());
        assertEquals("ANIMALS", word.theme());
        assertEquals("testHint", word.hint());
    }

    @Test
    void testParseInvalidJson() throws IOException {
        File invalidJsonFile =
            createTemporaryFile(
                "{\"content\":\"test\",\"hint\":}"
            );

        assertThrows(RuntimeException.class, () -> FileParser.parseJsonToWordList(invalidJsonFile));
    }

    private File createTemporaryFile(String content) throws IOException {
        File tempFile = File.createTempFile("temp", ".json");
        try (java.io.FileWriter writer = new java.io.FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }
}
