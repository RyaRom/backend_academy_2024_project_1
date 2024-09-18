package backend.academy.data;

public record Difficulty(String name, int level) {
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
}
