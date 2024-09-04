package backend.academy.utils;

import backend.academy.data.Word;
import java.util.Random;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GameUtils {

    @SafeVarargs
    public static <T> T pickRandomObject(T... objects) {
        if (objects.length < 1) {
            throw new IllegalArgumentException("No random element in the empty array");
        }
        Random random = new Random();
        int index = random.nextInt(objects.length);
        return objects[index];
    }
}
