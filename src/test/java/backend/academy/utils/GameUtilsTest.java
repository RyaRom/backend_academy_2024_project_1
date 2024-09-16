package backend.academy.utils;

import backend.academy.config.GameConfig;
import backend.academy.data.Word;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static backend.academy.config.GameConfig.EXIT_COMMAND;
import static backend.academy.config.GameConfig.HELP_COMMAND;
import static backend.academy.utils.GraphicUtils.INPUT_NOT_RECOGNIZED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameUtilsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private final PrintStream outputWriter = new PrintStream(outputStream);

    @Test
    void readLetterValidInput() {
        BufferedReader correctLetter = new BufferedReader(new StringReader("A\n"));
        BufferedReader correctLetterLowerCase = new BufferedReader(new StringReader("a\n"));
        BufferedReader correctHelp = new BufferedReader(new StringReader("HeLP\n"));
        BufferedReader correctExit = new BufferedReader(new StringReader("   ExiT \n"));

        String correctLetterResult = GameUtils.readLetter(correctLetter, outputWriter);
        String correctLetterLowerCaseResult = GameUtils.readLetter(correctLetterLowerCase, outputWriter);
        String correctHelpResult = GameUtils.readLetter(correctHelp, outputWriter);
        String correctExitResult = GameUtils.readLetter(correctExit, outputWriter);

        assertEquals("A", correctLetterResult);
        assertEquals("A", correctLetterLowerCaseResult);
        assertEquals(HELP_COMMAND, correctHelpResult);
        assertEquals(EXIT_COMMAND, correctExitResult);
    }

    @Test
    void readLetterInvalidInput() {
        BufferedReader incorrectInputLetters = new BufferedReader(new StringReader("aaa\nAP\ny"));
        BufferedReader incorrectInputOther = new BufferedReader(new StringReader("%\nHeLp"));

        String incorrectInputLettersResult = GameUtils.readLetter(incorrectInputLetters, outputWriter);
        String incorrectInputOtherResult = GameUtils.readLetter(incorrectInputOther, outputWriter);

        assertEquals(incorrectInputLettersResult, "Y");
        assertEquals(incorrectInputOtherResult, "help");
    }

    @Test
    void readCommandValidInput() {
        BufferedReader validInputInRange = new BufferedReader(new StringReader("5\n"));
        BufferedReader validInputLowerBound = new BufferedReader(new StringReader("1\n"));
        BufferedReader validInputUpperBound = new BufferedReader(new StringReader("10\n"));

        Integer resultInRange = GameUtils.readCommand(validInputInRange, outputWriter, 1, 10);
        Integer resultLowerBound = GameUtils.readCommand(validInputLowerBound, outputWriter, 1, 10);
        Integer resultUpperBound = GameUtils.readCommand(validInputUpperBound, outputWriter, 1, 10);

        assertEquals(Integer.valueOf(5), resultInRange);
        assertEquals(Integer.valueOf(1), resultLowerBound);
        assertEquals(Integer.valueOf(10), resultUpperBound);
    }

    @Test
    void readCommandInvalidInput() {
        BufferedReader invalidInputNonNumeric = new BufferedReader(new StringReader("abc\n12\n2"));
        BufferedReader invalidInputOutOfRange = new BufferedReader(new StringReader("15\n7\n"));

        Integer resultNonNumeric = GameUtils.readCommand(invalidInputNonNumeric, outputWriter, 1, 10);
        Integer resultOutOfRange = GameUtils.readCommand(invalidInputOutOfRange, outputWriter, 1, 10);

        assertEquals(Integer.valueOf(2), resultNonNumeric);
        assertEquals(Integer.valueOf(7), resultOutOfRange);
        assertTrue(outputStream.toString().contains(INPUT_NOT_RECOGNIZED));
    }

    @Test
    void pickRandomObjectTest() {
        String[] oneElement = {""};
        Set<Word> words = GameConfig.EASY_WORDS;
        Word word = GameUtils.pickRandomObject(words);

        assertThatThrownBy(() -> GameUtils.pickRandomObject(new String[0]))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("No random element in the empty array");
        assertEquals("", GameUtils.pickRandomObject(oneElement));
        assertTrue(words.contains(word));
    }

    @AfterEach
    void tearDown() throws IOException {
        outputStream.close();
        outputWriter.close();
    }
}
