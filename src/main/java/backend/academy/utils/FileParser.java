package backend.academy.utils;

import backend.academy.data.Word;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.DIFFICULTIES;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.config.GameConfig.WORDS_LIST;
import static backend.academy.config.GameConfig.WORD_FILE_LOCATION;

@Log4j2
@UtilityClass
public class FileParser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static final List<Word> DEFAULT_WORD_CONFIG = parseJsonToWordList(
        new File(WORD_FILE_LOCATION));

    public static void addWordsFromJson(String path) {
        File json = new File(path);
        List<Word> config = parseJsonToWordList(json);

        log.info("Adding words from JSON file: {}", config);

        WORDS_LIST.addAll(config);

        DIFFICULTIES = WORDS_LIST.stream()
            .map(Word::difficulty)
            .distinct()
            .toList();

        THEMES = WORDS_LIST.stream()
            .map(Word::theme)
            .distinct()
            .toList();
    }

    //Generic types are leading to ClassCastException here
    public static List<Word> parseJsonToWordList(File json) {
        try {
            TypeReference<List<Word>> typeRef = new TypeReference<>() {
            };
            return JSON_MAPPER.readValue(json, typeRef);
        } catch (Exception e) {
            log.error("Error parsing JSON file", e);
            throw new RuntimeException(e);
        }
    }

    public static String[] getJsonInDir(String srcPath) {
        File src = new File(srcPath);
        if (!src.isDirectory()) {
            throw new IllegalArgumentException("The path is not a directory: " + src);
        }
        return src.list((f, name) -> name.toLowerCase().endsWith(".json"));
    }
}
