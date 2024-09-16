package backend.academy.utils;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import static backend.academy.config.GameConfig.EXIT_COMMAND;
import static backend.academy.config.GameConfig.HELP_COMMAND;
import static backend.academy.utils.GraphicUtils.INPUT_NOT_RECOGNIZED;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    @SafeVarargs
    public static <T> T pickRandomObject(T... objects) {
        if (objects.length < 1) {
            throw new IllegalArgumentException("No random element in the empty array");
        }

        int index = RANDOM.nextInt(objects.length);
        return objects[index];
    }

    public static <T> T pickRandomObject(List<T> objects) {
        if (objects.isEmpty()) {
            throw new IllegalArgumentException("No random element in the empty array");
        }

        int index = RANDOM.nextInt(objects.size());
        return objects.get(index);
    }

    @SneakyThrows public static String readLetter(BufferedReader inputReader, PrintStream outputWriter) {
        while (true) {
            String input = inputReader.readLine();
            if (input == null) {
                continue;
            }
            input = input.toUpperCase(Locale.ROOT).trim();
            if (input.matches("[A-Z]")) {
                return input;
            } else if (HELP_COMMAND.equalsIgnoreCase(input) || EXIT_COMMAND.equalsIgnoreCase(input)) {
                return input.toLowerCase();
            } else {
                outputWriter.print(INPUT_NOT_RECOGNIZED);
            }
        }
    }

    @SneakyThrows public static Integer readCommand(
        BufferedReader inputReader,
        PrintStream outputWriter,
        int lowerBound,
        int upperBound
    ) {
        while (true) {
            String input = inputReader.readLine();
            if (input == null) {
                continue;
            }
            input = input.trim();
            if (input.matches("\\d+")) {
                int command = Integer.parseInt(input);
                if (command >= lowerBound && command <= upperBound) {
                    return command;
                }
            }
            outputWriter.print(INPUT_NOT_RECOGNIZED);
        }
    }
}
