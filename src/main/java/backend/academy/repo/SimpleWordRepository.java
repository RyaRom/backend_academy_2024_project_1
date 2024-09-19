package backend.academy.repo;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.exception.NoWordsWithParametersException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import static backend.academy.utils.GameUtils.pickRandomObject;

public class SimpleWordRepository implements WordRepository {
    private final Set<Word> words;

    private final Map<String, Set<Word>> wordsByTheme = new HashMap<>();

    private List<String> themes;

    private List<Difficulty> difficulties;

    public SimpleWordRepository(List<Word> words) {
        removeDifficultiesDuplicationsFromWords(words,
            words.stream()
                .map(Word::difficulty)
                .distinct()
                .toList());
        this.words = new HashSet<>(words);
        updateThemesAndDifficulties();
    }

    private void updateThemesAndDifficulties() {
        wordsByTheme.clear();

        themes = words.stream()
            .map(Word::theme)
            .map(String::toUpperCase)
            .distinct()
            .toList();

        difficulties = words.stream()
            .map(Word::difficulty)
            .distinct()
            .sorted(Difficulty::compareTo)
            .toList();
    }

    private void validateThemesAndDifficulties() throws NoWordsWithParametersException {
        if (themes.isEmpty() || difficulties.isEmpty()) {
            throw new NoWordsWithParametersException();
        }
    }

    @Override
    public List<Difficulty> getDifficulties() throws NoWordsWithParametersException {
        validateThemesAndDifficulties();
        return difficulties;
    }

    @Override
    public Difficulty getRandomDifficulty() throws NoWordsWithParametersException {
        validateThemesAndDifficulties();
        return pickRandomObject(difficulties);
    }

    @Override
    public List<String> getThemes() throws NoWordsWithParametersException {
        validateThemesAndDifficulties();
        return themes;
    }

    @Override
    public String getRandomTheme() throws NoWordsWithParametersException {
        validateThemesAndDifficulties();
        return pickRandomObject(themes);
    }

    @Override
    public Set<Word> getWordsByTheme(String theme) {
        if (wordsByTheme.containsKey(theme)) {
            return wordsByTheme.get(theme);
        }
        var filtered = words.stream()
            .filter(word -> word.theme().equalsIgnoreCase(theme))
            .collect(Collectors.toSet());
        wordsByTheme.put(theme, filtered);
        return filtered;
    }

    @Override
    public Word getRandomWordByThemeAndDifficulty(String theme, Difficulty difficulty)
        throws NoWordsWithParametersException {
        try {
            return pickRandomObject(
                getWordsByTheme(theme)
                    .stream()
                    .filter(word -> word.difficulty().equals(difficulty))
                    .toList()
            );
        } catch (IllegalArgumentException e) {
            throw new NoWordsWithParametersException();
        }
    }

    @Override
    public void addWords(List<Word> newWords) throws NoWordsWithParametersException {
        var dif = getDifficulties();
        dif.addAll(newWords.stream()
            .map(Word::difficulty)
            .distinct()
            .sorted(Difficulty::compareTo)
            .toList());
        removeDifficultiesDuplicationsFromWords(newWords, dif);

        words.addAll(newWords);
        updateThemesAndDifficulties();
        validateThemesAndDifficulties();
    }

    private void removeDifficultiesDuplicationsFromWords(List<Word> words, List<Difficulty> difficulties) {
        Map<String, Integer> difficultySet = difficulties
            .stream()
            .collect(Collectors.toMap(Difficulty::name, Difficulty::level));

        //replace all themes with existing names to old ones
        for (int i = 0; i < words.size(); i++) {
            Word word = words.get(i);
            var difficulty = word.difficulty();
            int previousLevel = difficultySet.getOrDefault(difficulty.name(), difficulty.level());
            if (previousLevel != difficulty.level()) {
                var newDif = new Difficulty(difficulty.name(), previousLevel);
                words.set(i, new Word(
                    word.content(),
                    word.theme(),
                    word.hint(),
                    newDif));
            } else {
                difficultySet.put(difficulty.name(), difficulty.level());
            }
        }
    }
}
