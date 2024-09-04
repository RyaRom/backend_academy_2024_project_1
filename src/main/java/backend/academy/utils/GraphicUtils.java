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
        "                                               "
    );

    public static final String VICTORY_SCREEN = String.join(System.lineSeparator(),
        " __   _____  _   _  __      _____ _  _ _ ",
        " \\ \\ / / _ \\| | | | \\ \\    / /_ _| \\| | |",
        "  \\ V / (_) | |_| |  \\ \\/\\/ / | || .` |_|",
        "   |_| \\___/ \\___/    \\_/\\_/ |___|_|\\_(_)",
        "                                         "
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

}
