package backend.academy.utils;

import backend.academy.data.Word;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.WORD_FILE_LOCATION;

@Log4j2
@UtilityClass
public class FileParser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private static final WordConfig WORD_CONFIG = parseJson(
        new File(WORD_FILE_LOCATION),
        WordConfig.class);

    public static <T> T parseJson(File json, Class<T> clazz) {
        try {
            return JSON_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Error parsing JSON file", e);
            throw new RuntimeException(e);
        }
    }

    public static Word[] getEasyWords() {
        return WORD_CONFIG.easyWords.toArray(new Word[0]);
    }

    public static Word[] getMediumWords() {
        return WORD_CONFIG.mediumWords.toArray(new Word[0]);
    }

    public static Word[] getHardWords() {
        return WORD_CONFIG.hardWords.toArray(new Word[0]);
    }

    private record WordConfig(
        @JsonProperty("EASY_WORDS") List<Word> easyWords,
        @JsonProperty("MEDIUM_WORDS") List<Word> mediumWords,
        @JsonProperty("HARD_WORDS") List<Word> hardWords) {
    }
}
