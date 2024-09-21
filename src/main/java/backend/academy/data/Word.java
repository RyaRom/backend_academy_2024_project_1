package backend.academy.data;

import lombok.Builder;

@Builder
public record Word(String content, String theme, String hint, Difficulty difficulty) {
    public Word {
        if (content.isBlank() || content.length() > 50) {
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
