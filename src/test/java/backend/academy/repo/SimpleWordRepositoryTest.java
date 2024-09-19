package backend.academy.repo;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.exception.NoWordsWithParametersException;
import java.util.List;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleWordRepositoryTest {

    private List<Word> words;

    private WordRepository repository;

    @BeforeEach
    void setUp() throws NoWordsWithParametersException {
        words = Instancio.createList(Word.class);
        repository = new SimpleWordRepository(words);
    }

    @Test
    void getDifficulties() throws NoWordsWithParametersException {
        var diffs = repository.getDifficulties();
        assertNotNull(diffs);
        assertFalse(diffs.isEmpty());
    }

    @Test
    void getRandomDifficulty() throws NoWordsWithParametersException {
        Difficulty randomDifficulty = repository.getRandomDifficulty();
        assertNotNull(randomDifficulty);
        assertTrue(repository.getDifficulties().contains(randomDifficulty));
    }

    @Test
    void getThemes() throws NoWordsWithParametersException {
        var themes = repository.getThemes();
        assertNotNull(themes);
        assertFalse(themes.isEmpty());
        for (String theme : themes) {
            assertTrue(words.stream().anyMatch(word -> word.theme().equalsIgnoreCase(theme)));
        }
    }

    @Test
    void getRandomTheme() throws NoWordsWithParametersException {
        String randomTheme = repository.getRandomTheme();
        assertNotNull(randomTheme);
        assertTrue(repository.getThemes().contains(randomTheme));
        for (char c : randomTheme.toCharArray()) {
            assertTrue(Character.isUpperCase(c));
        }
    }

    @Test
    void getWordsByTheme() {
        String theme = words.getFirst().theme();
        var wordsByTheme = repository.getWordsByTheme(theme);

        assertNotNull(wordsByTheme);
        assertFalse(wordsByTheme.isEmpty());
        wordsByTheme.forEach(word -> assertEquals(theme, word.theme()));
    }

    @Test
    void getRandomWordByThemeAndDifficulty() throws NoWordsWithParametersException {
        String theme = words.getFirst().theme();
        Difficulty difficulty = words.getFirst().difficulty();
        Word word = repository.getRandomWordByThemeAndDifficulty(theme, difficulty);

        assertNotNull(word);
        assertEquals(word.theme(), theme);
        assertEquals(word.difficulty(), difficulty);
    }

    @Test
    void addWords() throws NoWordsWithParametersException {
        String newTheme = "newTheme";
        var oldDiff = repository.getDifficulties().getFirst();
        var newDiff = new Difficulty("newDifficulty", 1);
        Word wordOldDiff = new Word("newWord1", newTheme, "", oldDiff);
        Word wordNewDif = new Word("newWord2", newTheme, "", newDiff);
        List<Word> newWords = List.of(wordOldDiff, wordNewDif);

        repository.addWords(newWords);
        var diffs = repository.getDifficulties();
        var themes = repository.getThemes();
        var wordsByNewTheme = repository.getWordsByTheme(newTheme);

        assertTrue(diffs.contains(newDiff));
        assertTrue(themes.contains(newTheme.toUpperCase()));
        assertTrue(wordsByNewTheme.contains(wordOldDiff));
        assertTrue(wordsByNewTheme.contains(wordNewDif));
    }

    @Test
    void addWordsEdgeCases() throws NoWordsWithParametersException {
        String oldTheme = repository.getRandomTheme();
        String newTheme = "newTheme";
        var newDiff = new Difficulty("newDifficulty", -2);
        var newDiffSameName = new Difficulty("newDifficulty", 0);

        Word wordNewDif = new Word("newWord1", oldTheme, "", newDiff);
        repository.addWords(List.of(wordNewDif));
        Word wordNewDifSameName = new Word("newWord2", newTheme, "", newDiffSameName);
        repository.addWords(List.of(wordNewDifSameName));

        var diffs = repository.getDifficulties();
        Word wordNewDifAfterwards = repository.getWordsByTheme(oldTheme)
            .stream()
            .filter(word -> word.content().equals("newWord1"))
            .toList().getFirst();
        Word wordNewDifSameNameAfterwards = repository.getWordsByTheme(newTheme)
            .stream().toList().getFirst();
        Difficulty newDiffAfterwards = diffs.stream()
            .filter(diff -> diff.name().equals("newDifficulty")).toList().getFirst();

        assertTrue(diffs.contains(newDiff));
        assertEquals(2, wordNewDifAfterwards.difficulty().level());
        assertEquals(2, wordNewDifSameNameAfterwards.difficulty().level());
        assertEquals(2, newDiffAfterwards.level());
        assertEquals(newDiffAfterwards, wordNewDifAfterwards.difficulty());
        assertEquals(newDiffAfterwards, wordNewDifSameNameAfterwards.difficulty());
    }

}
