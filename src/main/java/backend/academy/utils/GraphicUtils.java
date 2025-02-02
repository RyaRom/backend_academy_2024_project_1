package backend.academy.utils;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GraphicUtils {
    public static final String HANGMAN_PREVIEW = String.join(System.lineSeparator(),
        " ░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓███████▓▒░ ░▒▓██████▓▒░░▒▓██████████████▓▒░ ░▒▓██████▓▒░░▒▓███████▓▒░  ",
        " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ",
        " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ",
        " ░▒▓████████▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒▒▓███▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ ",
        " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ",
        " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ",
        " ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░░▒▓██████▓▒░░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░ ",
        "                                                                                                     ",
        "                                            Version 0.0.1                                            ",
        ""
    );

    public static final String DEATH_SCREEN = String.join(System.lineSeparator(),
        "              The word was: \"%s\"",
        "",
        " __   _____  _   _   _    ___   ___  ___ ___ _ ",
        " \\ \\ / / _ \\| | | | | |  / _ \\ / _ \\/ __| __| |",
        "  \\ V / (_) | |_| | | |_| (_) | (_) \\__ \\ _||_|",
        "   |_| \\___/ \\___/  |____\\___/ \\___/|___/___(_)",
        "                                               ",
        "1. Restart",
        "2. Exit"
    );

    public static final String VICTORY_SCREEN = String.join(System.lineSeparator(),
        "              The word was: \"%s\"",
        "",
        " __   _____  _   _  __      _____ _  _ _ ",
        " \\ \\ / / _ \\| | | | \\ \\    / /_ _| \\| | |",
        "  \\ V / (_) | |_| |  \\ \\/\\/ / | || .` |_|",
        "   |_| \\___/ \\___/    \\_/\\_/ |___|_|\\_(_)",
        "                                         ",
        "1. Restart",
        "2. Exit"
    );

    public static final String[] GAME_STAGES = {
        String.join(System.lineSeparator(),
            "         ",    // Stage 0
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "         ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            "         ",    // Stage 1
            "         ",
            "         ",
            "         ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            "         ",    // Stage 2
            "         ",
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            "         ",    // Stage 3
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ___     ",    // Stage 4
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 5
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 6
            "|     |  ",
            "|        ",
            "|        ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 7
            "|     |  ",
            "|     0  ",
            "|        ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 8
            "|     |  ",
            "|     0  ",
            "|     |  ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 9
            "|     |  ",
            "|    \\0  ",
            "|     |  ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 10
            "|     |  ",
            "|    \\0/ ",
            "|     |  ",
            "|        ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 11
            "|     |  ",
            "|    \\0/ ",
            "|     |  ",
            "|    /   ",
            "|        ",
            "=========",
            ""
        ),

        String.join(System.lineSeparator(),
            " ______  ",    // Stage 12
            "|     |  ",
            "|    \\0/ ",
            "|     |  ",
            "|    / \\ ",
            "|        ",
            "=========",
            ""
        ),
    };

    public static final String MAIN_MENU = String.join(System.lineSeparator(),
        "1. Play Game",
        "2. Select Theme                                            Selected theme: %s",
        "3. Select Difficulty                                       Selected difficulty: %s",
        "4. Exit",
        "5. Add custom words",
        "6. Create new wordlist",
        "Enter your choice: "
    );

    public static final String WORD_MENU = String.join(System.lineSeparator(),
        "Word:               %s",
        "",
        "Wrong letters: %s",
        "",
        "Hint: %s   Theme: %s",
        "",
        "Difficulty: %s                 Lives remaining: %s",
        "",
        "Enter \"exit\" to leave game",
        "");

    public static final String INPUT_NOT_RECOGNIZED = "Input wasn't recognized. Write command again: ";

    public static final String NO_HINT_TEXT = "Enter \"help\" for hint. Enter \"help\" again to disable";

    private static final Integer IDE_CLEAN_TERMINAL = 50;

    public static String getHangmanWordString(String[] word) {
        if (word.length < 1) {
            throw new IllegalArgumentException("No string from the empty array");
        }
        return Arrays.stream(word).map(letter -> {
            if (letter == null || letter.isEmpty()) {
                return "_";
            } else {
                return letter.toUpperCase();
            }
        }).collect(Collectors.joining(" "));
    }

    public static String getCustomMenu(List<String> options, String menuName) {
        if (options.isEmpty()) {
            throw new IllegalArgumentException("No string from the empty array");
        }

        StringBuilder stringBuilder = new StringBuilder()
            .append("Select ")
            .append(menuName)
            .append(":")
            .append(System.lineSeparator())
            .append("0. Go back")
            .append(System.lineSeparator());

        for (int i = 0; i < options.size(); i++) {
            if (options.get(i) == null) {
                continue;
            }
            stringBuilder.append(i + 1)
                .append(". ")
                .append(options.get(i))
                .append(System.lineSeparator());
        }
        stringBuilder.append("Enter your choice: ");
        return stringBuilder.toString();
    }

    public static void clearScreen(PrintStream outputWriter) {
        //for IDE's terminal
        for (int i = 0; i < IDE_CLEAN_TERMINAL; i++) {
            outputWriter.println();
        }

        outputWriter.print("\033[H\033[2J");
        outputWriter.flush();
    }
}
