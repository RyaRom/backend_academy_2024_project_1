package backend.academy.data;

import lombok.Builder;

@Builder
public record Word(String content, String theme, String hint, Difficulty difficulty) {
    @Override public String toString() {
        return content;
    }

    public int length() {
        return content.length();
    }
}
