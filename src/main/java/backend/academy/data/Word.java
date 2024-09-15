package backend.academy.data;

import backend.academy.data.enums.WordTheme;

public record Word(String content, WordTheme theme, String hint) {
    @Override public String toString() {
        return content;
    }

    public int length() {
        return content.length();
    }
}
