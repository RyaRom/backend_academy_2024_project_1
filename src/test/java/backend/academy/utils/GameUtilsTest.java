package backend.academy.utils;

import backend.academy.config.GameConfig;
import backend.academy.data.Word;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameUtilsTest {

    @Test
    void readLetterValidInput() {
        BufferedReader correctLetter = new BufferedReader(new StringReader("A\n"));
        BufferedReader correctLetterLowerCase = new BufferedReader(new StringReader("a\n"));
        BufferedReader correctHelp = new BufferedReader(new StringReader("HeLP\n"));

        String correctLetterResult = GameUtils.readLetter(correctLetter);
        String correctLetterLowerCaseResult = GameUtils.readLetter(correctLetterLowerCase);
        String correctHelpResult = GameUtils.readLetter(correctHelp);

        assertEquals("A", correctLetterResult);
        assertEquals("A", correctLetterLowerCaseResult);
        assertEquals("help", correctHelpResult);
    }

    @Test
    void readLetterInvalidInput() {
        BufferedReader incorrectInputLetters = new BufferedReader(new StringReader("aaa\nAP\ny"));
        BufferedReader incorrectInputOther = new BufferedReader(new StringReader("%\nHeLp"));

        String incorrectInputLettersResult = GameUtils.readLetter(incorrectInputLetters);
        String incorrectInputOtherResult = GameUtils.readLetter(incorrectInputOther);

        assertEquals(incorrectInputLettersResult, "Y");
        assertEquals(incorrectInputOtherResult, "help");
    }

    @Test
    void readCommandValidInput() {
        BufferedReader validInputInRange = new BufferedReader(new StringReader("5\n"));
        BufferedReader validInputLowerBound = new BufferedReader(new StringReader("1\n"));
        BufferedReader validInputUpperBound = new BufferedReader(new StringReader("10\n"));

        Integer resultInRange = GameUtils.readCommand(validInputInRange, 1, 10);
        Integer resultLowerBound = GameUtils.readCommand(validInputLowerBound, 1, 10);
        Integer resultUpperBound = GameUtils.readCommand(validInputUpperBound, 1, 10);

        assertEquals(Integer.valueOf(5), resultInRange);
        assertEquals(Integer.valueOf(1), resultLowerBound);
        assertEquals(Integer.valueOf(10), resultUpperBound);
    }

    @Test
    void readCommandInvalidInput() {
        BufferedReader invalidInputNonNumeric = new BufferedReader(new StringReader("abc\n12\n2"));
        BufferedReader invalidInputOutOfRange = new BufferedReader(new StringReader("15\n7\n"));

        Integer resultNonNumeric = GameUtils.readCommand(invalidInputNonNumeric, 1, 10);
        Integer resultOutOfRange = GameUtils.readCommand(invalidInputOutOfRange, 1, 10);

        assertEquals(Integer.valueOf(2), resultNonNumeric);
        assertEquals(Integer.valueOf(7), resultOutOfRange);
    }

    @Test
    void pickRandomObjectTest() {
        String[] empty = new String[0];
        String[] oneElement = {""};
        Word[] words = GameConfig.EASY_WORDS;
        Word word = GameUtils.pickRandomObject(words);

        assertThrows(IllegalArgumentException.class, () -> GameUtils.pickRandomObject(empty));
        assertEquals("", GameUtils.pickRandomObject(oneElement));
        assertTrue(Arrays.asList(words).contains(word));
    }
}
