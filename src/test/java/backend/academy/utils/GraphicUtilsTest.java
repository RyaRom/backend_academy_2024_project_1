package backend.academy.utils;

import backend.academy.data.enums.WordTheme;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
        assertThatThrownBy(() -> GraphicUtils.getHangmanWordString(new String[] {}))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("No string from the empty array");
    }

    @Test
    void getThemeMenuValidInput() {
        WordTheme[] themes = {WordTheme.ANIMALS, WordTheme.FOOD, WordTheme.BIOLOGY};
        String result = GraphicUtils.getThemeMenu(themes);
        String expected = String.join(System.lineSeparator(),
            "Select theme:",
            "0. Go back",
            "1. ANIMALS",
            "2. FOOD",
            "3. BIOLOGY",
            "Enter your choice: "
        );
        assertEquals(expected, result);
    }

    @Test
    void getThemeMenuInvalidInput() {
        WordTheme[] themes = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            GraphicUtils.getThemeMenu(themes);
        });
        assertEquals("No string from the empty array", exception.getMessage());
    }

    @Test
    void clearScreen() {
        GraphicUtils.clearScreen(System.out);
    }
}
