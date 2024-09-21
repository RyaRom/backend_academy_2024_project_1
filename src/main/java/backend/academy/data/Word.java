package backend.academy.data;

import lombok.Builder;
import static backend.academy.config.GameConfig.WORD_MAX_LEN;

@Builder
public record Word(String content, String theme, String hint, Difficulty difficulty) {
    public Word {
        if (content.isBlank() || content.length() > WORD_MAX_LEN) {
            content = "empty";
        }
    }

    @Override public String toString() {
        return content;
    }

    public int length() {
        return content.length();
    }
}
