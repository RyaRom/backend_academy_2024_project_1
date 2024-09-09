package backend.academy.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GraphicUtilsTest {

    @Test
    void getHangmanWordValidInput() {
        String[] word = {"H", "", "N", null};
        String result = GraphicUtils.getHangmanWordString(word);
        assertEquals("H _ N _", result);
    }

    @Test
    void getHangmanWordInvalidInput() {
        String[] word = {};
        Exception exception =
            assertThrows(IllegalArgumentException.class, () -> GraphicUtils.getHangmanWordString(word));
        assertEquals("No string from the empty array", exception.getMessage());
    }

    @Test
    void getThemeMenuValidInput() {
        String[] themes = {"Animals", "Movies", "Books"};
        String result = GraphicUtils.getThemeMenu(themes);
        String expected = String.join(System.lineSeparator(),
            "Select theme:",
            "0. Go back",
            "1. Animals",
            "2. Movies",
            "3. Books",
            "Enter your choice: "
        );
        assertEquals(expected, result);
    }

    @Test
    void getThemeMenuInvalidInput() {
        String[] themes = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            GraphicUtils.getThemeMenu(themes);
        });
        assertEquals("No string from the empty array", exception.getMessage());
    }
}
