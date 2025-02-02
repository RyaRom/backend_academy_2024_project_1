package backend.academy.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String[] themes = {"ANIMALS", "FOOD", "BIOLOGY"};
        String result =
            GraphicUtils.getCustomMenu(
                Arrays.stream(themes).toList(),
                "theme");
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
        assertThatThrownBy(() -> GraphicUtils.getCustomMenu(Collections.emptyList(), ""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("No string from the empty array");
    }

    @Test
    void clearScreen() throws IOException {
        try (
            var out = new ByteArrayOutputStream();
            var printStream = new PrintStream(out)) {
            assertThatNoException().isThrownBy(() -> GraphicUtils.clearScreen(printStream));
            assertEquals(System.lineSeparator().repeat(50) +
                "\033[H\033[2J", out.toString());
        }
    }
}
