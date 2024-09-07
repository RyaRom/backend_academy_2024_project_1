package backend.academy.utils;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.Locale;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameUtils {

    public static final String HELP_COMMAND = "help";

    private static final SecureRandom RANDOM = new SecureRandom();

    @SafeVarargs
    public static <T> T pickRandomObject(T... objects) {
        if (objects.length < 1) {
            throw new IllegalArgumentException("No random element in the empty array");
        }

        int index = RANDOM.nextInt(objects.length);
        return objects[index];
    }

    @SneakyThrows public static String readLetter(BufferedReader inputReader, PrintStream outputWriter) {
        while (true) {
            String input = inputReader.readLine();
            if (input == null) {
                continue;
            }
            input = input.toUpperCase(Locale.ROOT);
            if (input.matches("[A-Z]")) {
                return input;
            } else if ("HELP".equals(input)) {
                return HELP_COMMAND;
            } else {
                outputWriter.println("Input wasn't recognized. Write command again: ");
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
            if (input.matches("\\d+")) {
                int command = Integer.parseInt(input);
                if (command >= lowerBound && command <= upperBound) {
                    return command;
                }
            }
            outputWriter.println("Input wasn't recognized. Write command again: ");
        }
    }
}
