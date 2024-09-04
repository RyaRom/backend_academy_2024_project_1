package backend.academy.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
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
        " __   _____  _   _   _    ___   ___  ___ ___ _ ",
        " \\ \\ / / _ \\| | | | | |  / _ \\ / _ \\/ __| __| |",
        "  \\ V / (_) | |_| | | |_| (_) | (_) \\__ \\ _||_|",
        "   |_| \\___/ \\___/  |____\\___/ \\___/|___/___(_)",
        "                                               ",
        "1. Restart",
        "2. Exit"
    );

    public static final String VICTORY_SCREEN = String.join(System.lineSeparator(),
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
        "2. Select Theme",
        "3. Select Difficulty",
        "Enter your choice: "
    );

    public static final String DIFFICULTY_MENU = String.join(System.lineSeparator(),
        "Select Difficulty:",
        "0. Go back",
        "1. Easy",
        "2. Medium",
        "3. Hard",
        "Enter your choice: "
    );

    public static String getThemeMenu(String[] themes) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Select theme:").append(System.lineSeparator());
        stringBuilder.append("0. Go back").append(System.lineSeparator());
        for (int i = 0; i < themes.length; i++) {
            stringBuilder.append(i + 1).append(". ").append(themes[i]).append(System.lineSeparator());
        }
        stringBuilder.append("Enter your choice: ");
        return stringBuilder.toString();
    }

    public static void clearScreen() {
        //for IDE's terminal
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
