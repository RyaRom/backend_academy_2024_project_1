package backend.academy.utils;

import backend.academy.data.Word;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Set;
import org.instancio.Instancio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.EXIT_COMMAND;
import static backend.academy.config.GameConfig.HELP_COMMAND;
import static backend.academy.utils.GameUtils.pickRandomObject;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GameUtils.readLetter;
import static backend.academy.utils.GameUtils.readWord;
import static backend.academy.utils.GraphicUtils.INPUT_NOT_RECOGNIZED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameUtilsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final PrintStream outputWriter = new PrintStream(outputStream);

    @Test
    void readLetterValidInput() throws IOException {
        try (BufferedReader correctLetter = new BufferedReader(new StringReader("A\n"));
             BufferedReader correctLetterLowerCase = new BufferedReader(new StringReader("a\n"));
             BufferedReader correctHelp = new BufferedReader(new StringReader("HeLP\n"));
             BufferedReader correctExit = new BufferedReader(new StringReader("   ExiT \n"))) {

            String correctLetterResult = readLetter(correctLetter, outputWriter);
            String correctLetterLowerCaseResult = readLetter(correctLetterLowerCase, outputWriter);
            String correctHelpResult = readLetter(correctHelp, outputWriter);
            String correctExitResult = readLetter(correctExit, outputWriter);

            assertEquals("A", correctLetterResult);
            assertEquals("A", correctLetterLowerCaseResult);
            assertEquals(HELP_COMMAND, correctHelpResult);
            assertEquals(EXIT_COMMAND, correctExitResult);
        }
    }

    @Test
    void readLetterInvalidInput() throws IOException {
        try (BufferedReader incorrectInputLetters = new BufferedReader(new StringReader("aaa\nAP\ny"));
             BufferedReader incorrectInputOther = new BufferedReader(new StringReader("%\nHeLp"))) {

            String incorrectInputLettersResult = readLetter(incorrectInputLetters, outputWriter);
            String incorrectInputOtherResult = readLetter(incorrectInputOther, outputWriter);

            assertEquals(incorrectInputLettersResult, "Y");
            assertEquals(incorrectInputOtherResult, "help");
        }
    }

    @Test
    void readCommandValidInput() throws IOException {
        try (BufferedReader validInputInRange = new BufferedReader(new StringReader("5\n"));
             BufferedReader validInputLowerBound = new BufferedReader(new StringReader("1\n"));
             BufferedReader validInputUpperBound = new BufferedReader(new StringReader("10\n"))) {

            Integer resultInRange = readCommand(validInputInRange, outputWriter, 1, 10);
            Integer resultLowerBound = readCommand(validInputLowerBound, outputWriter, 1, 10);
            Integer resultUpperBound = readCommand(validInputUpperBound, outputWriter, 1, 10);

            assertEquals(Integer.valueOf(5), resultInRange);
            assertEquals(Integer.valueOf(1), resultLowerBound);
            assertEquals(Integer.valueOf(10), resultUpperBound);
        }
    }

    @Test
    void readCommandInvalidInput() throws IOException {
        try (BufferedReader invalidInputNonNumeric = new BufferedReader(new StringReader("abc\n12\n2"));
             BufferedReader invalidInputOutOfRange = new BufferedReader(new StringReader("15\n7\n"))) {

            Integer resultNonNumeric = readCommand(invalidInputNonNumeric, outputWriter, 1, 10);
            Integer resultOutOfRange = readCommand(invalidInputOutOfRange, outputWriter, 1, 10);

            assertEquals(Integer.valueOf(2), resultNonNumeric);
            assertEquals(Integer.valueOf(7), resultOutOfRange);
            assertTrue(outputStream.toString().contains(INPUT_NOT_RECOGNIZED));
        }
    }

    @Test
    void readWordValidInput() throws IOException {
        try (BufferedReader correctWordLower = new BufferedReader(new StringReader("hello\n"));
             BufferedReader correctWordUpper = new BufferedReader(new StringReader("WORLD\n"));
             BufferedReader correctWordWithSpaces = new BufferedReader(new StringReader("test test   \n"));
             BufferedReader emptyHint = new BufferedReader(new StringReader("\n"))) {

            String resultLower = readWord(correctWordLower, outputWriter, GameUtils.ReadWordMode.WORD);
            String resultUpper = readWord(correctWordUpper, outputWriter, GameUtils.ReadWordMode.WORD);
            String resultWithSpaces = readWord(correctWordWithSpaces, outputWriter, GameUtils.ReadWordMode.DESCRIPTION);
            String resultEmptyHint = readWord(emptyHint, outputWriter, GameUtils.ReadWordMode.HINT);

            assertEquals("hello", resultLower);
            assertEquals("world", resultUpper);
            assertEquals("test test", resultWithSpaces);
            assertEquals("", resultEmptyHint);
        }
    }

    @Test
    void readWordInvalidInput() throws IOException {
        try (BufferedReader invalidWordNumbers = new BufferedReader(new StringReader("12345\nhello\n"));
             BufferedReader invalidWordSymbols = new BufferedReader(new StringReader("@hello!\nWORLD\n"));
             BufferedReader invalidWordMix = new BufferedReader(new StringReader("hello hello\n   test   \n"))) {

            String resultInvalidNumbers =
                readWord(invalidWordNumbers, outputWriter, GameUtils.ReadWordMode.DESCRIPTION);
            String resultInvalidSymbols =
                readWord(invalidWordSymbols, outputWriter, GameUtils.ReadWordMode.DESCRIPTION);
            String resultInvalidMix = readWord(invalidWordMix, outputWriter, GameUtils.ReadWordMode.WORD);

            assertTrue(outputStream.toString().contains(INPUT_NOT_RECOGNIZED));
            assertEquals("hello", resultInvalidNumbers);
            assertEquals("world", resultInvalidSymbols);
            assertEquals("test", resultInvalidMix);
        }
    }

    @Test
    void pickRandomObjectTest() {
        String[] oneElement = {""};
        Set<Word> words = Instancio.createSet(Word.class);
        Word word = pickRandomObject(words);

        assertThatThrownBy(() -> pickRandomObject(new String[0]))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("No random element in the empty array");
        assertEquals("", pickRandomObject(oneElement));
        assertTrue(words.contains(word));
    }

    @AfterEach
    void tearDown() throws IOException {
        outputStream.close();
        outputWriter.close();
    }
}
