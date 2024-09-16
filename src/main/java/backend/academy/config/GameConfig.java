package backend.academy.config;

import backend.academy.data.Word;
import backend.academy.data.enums.WordTheme;
import lombok.experimental.UtilityClass;
import static backend.academy.utils.FileParser.getEasyWords;
import static backend.academy.utils.FileParser.getHardWords;
import static backend.academy.utils.FileParser.getMediumWords;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameConfig {
    public static final String WORD_FILE_LOCATION = "src/main/resources/words.json";

    public static final String HELP_COMMAND = "help";

    public static final String EXIT_COMMAND = "exit";

    public static final WordTheme[] THEMES = WordTheme.values();

    public static final Integer STAGES = 12;

    public static final Integer EASY_MODE_STEPS = 1;

    public static final Integer MEDIUM_MODE_STEPS = 2;

    public static final Integer HARD_MODE_STEPS = 3;

    public static final Word[] EASY_WORDS = getEasyWords();

    public static final Word[] MEDIUM_WORDS = getMediumWords();

    public static final Word[] HARD_WORDS = getHardWords();
}
