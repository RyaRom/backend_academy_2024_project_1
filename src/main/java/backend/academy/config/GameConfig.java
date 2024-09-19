package backend.academy.config;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameConfig {
    public static final String NO_WORD_TEXT =
        """
             No words found with this theme or difficulty. Load new wordset or change game parameters
            """;

    public static final String WORD_FILE_LOCATION = "src/main/resources/words.json";

    public static final String CUSTOM_WORD_FILE_LOCATION = "src/main/resources/customWords/";

    public static final String HELP_COMMAND = "help";

    public static final String EXIT_COMMAND = "exit";

    public static final Integer STAGES = 12;

    public static final String[] EMPTY_HINT_TEXTS =
        {"Waiting for something to happen?", "Nothing is here", "No help for you"};
}
