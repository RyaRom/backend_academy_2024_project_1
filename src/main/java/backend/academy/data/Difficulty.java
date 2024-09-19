package backend.academy.data;

import static java.lang.Math.abs;
import static java.lang.Math.max;

@SuppressWarnings("MultipleStringLiterals")
public record Difficulty(String name, int level) implements Comparable<Difficulty> {
    public Difficulty(String name, int level) {
        this.name = name;
        this.level = max(abs(level), 1);
    }

    @Override public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Difficulty other) {
            return name.equalsIgnoreCase(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.toLowerCase().hashCode();
    }

    @Override
    public int compareTo(Difficulty dif) {
        return switch (this.name.toLowerCase()) {
            case "easy" -> -1;
            case "medium" -> switch (dif.name) {
                case "easy" -> 1;
                default -> -1;
            };
            case "hard" -> switch (dif.name) {
                case "easy", "medium" -> 1;
                default -> -1;
            };
            default -> Integer.compare(this.level, dif.level);
        };
    }
}
