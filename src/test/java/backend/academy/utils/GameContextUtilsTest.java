package backend.academy.utils;

import backend.academy.config.GameConfig;
import backend.academy.data.Word;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameContextUtilsTest {

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
