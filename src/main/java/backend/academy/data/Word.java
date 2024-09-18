package backend.academy.data;

public record Word(String content, String theme, String hint, Difficulty difficulty) {
    @Override public String toString() {
        return content;
    }

    public int length() {
        return content.length();
    }
}
