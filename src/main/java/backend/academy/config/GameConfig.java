package backend.academy.config;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.experimental.UtilityClass;
import static backend.academy.utils.FileParser.DEFAULT_WORD_CONFIG;

@UtilityClass
@SuppressWarnings("MultipleStringLiterals")
public class GameConfig {
    public static final String WORD_FILE_LOCATION = "src/main/resources/words.json";

    public static final String CUSTOM_WORD_FILE_LOCATION = "src/main/resources/customWords/";

    public static final String HELP_COMMAND = "help";

    public static final String EXIT_COMMAND = "exit";

    public static final Integer STAGES = 12;

    public static Set<Word> WORDS_LIST = new HashSet<>(DEFAULT_WORD_CONFIG);

    public static List<String> THEMES = WORDS_LIST.stream()
        .map(Word::theme)
        .distinct()
        .toList();

    public static List<Difficulty> DIFFICULTIES = WORDS_LIST.stream()
        .map(Word::difficulty)
        .distinct()
        .toList();
}
