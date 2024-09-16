package backend.academy.utils;

import backend.academy.data.Word;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Collections;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.EASY_WORDS;
import static backend.academy.config.GameConfig.HARD_WORDS;
import static backend.academy.config.GameConfig.MEDIUM_WORDS;
import static backend.academy.config.GameConfig.WORD_FILE_LOCATION;

@Log4j2
@UtilityClass
public class FileParser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final WordConfig WORD_CONFIG = parseJson(
        new File(WORD_FILE_LOCATION),
        WordConfig.class);

    public static void addWordsFromJson(String path) {
        File json = new File(path);
        WordConfig config = parseJson(json, WordConfig.class);
        EASY_WORDS.addAll(config.easyWords);
        MEDIUM_WORDS.addAll(config.mediumWords);
        HARD_WORDS.addAll(config.hardWords);
    }

    public static <T> T parseJson(File json, Class<T> clazz) {
        try {
            return JSON_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Error parsing JSON file", e);
            throw new RuntimeException(e);
        }
    }

    public static List<Word> getEasyWords() {
        return WORD_CONFIG.easyWords;
    }

    public static List<Word> getMediumWords() {
        return WORD_CONFIG.mediumWords;
    }

    public static List<Word> getHardWords() {
        return WORD_CONFIG.hardWords;
    }

    public static String[] getJsonInDir(String srcPath) {
        File src = new File(srcPath);
        if (!src.isDirectory()) {
            throw new IllegalArgumentException("The path is not a directory: " + src);
        }
        return src.list((f, name) -> name.toLowerCase().endsWith(".json"));
    }

    private record WordConfig(
        List<Word> easyWords,
        List<Word> mediumWords,
        List<Word> hardWords) {
        public WordConfig {
            if (easyWords == null) {
                easyWords = Collections.emptyList();
            }
            if (mediumWords == null) {
                mediumWords = Collections.emptyList();
            }
            if (hardWords == null) {
                hardWords = Collections.emptyList();
            }
        }
    }
}
