package backend.academy.utils;

import java.io.BufferedReader;
import java.util.Locale;
import java.util.Random;
import lombok.SneakyThrows;
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

    @SneakyThrows public String readLetter(BufferedReader inputReader) {
        while (true) {
            String input = inputReader.readLine();
            input = input.toUpperCase(Locale.ROOT);
            if (input.matches("[A-Z]")) {
                return input;
            } else if (input.equals("HELP")) {
                return "help";
            } else {
                System.out.println("Input wasn't recognized. Write command again: ");
            }
        }
    }

    @SneakyThrows public Integer readCommand(BufferedReader inputReader, int lowerBound, int upperBound) {
        while (true) {
            String input = inputReader.readLine();
            if (input.matches("\\d+")) {
                int command = Integer.parseInt(input);
                if (command >= lowerBound && command <= upperBound) {
                    return command;
                }
            }
            System.out.println("Input wasn't recognized. Write command again: ");
        }
    }
}
