package backend.academy.utils;

import backend.academy.data.Word;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@Log4j2
@UtilityClass
public class FileParser {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

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
