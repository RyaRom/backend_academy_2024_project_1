package backend.academy.repo;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.exception.NoWordsWithParametersException;
import java.util.List;
import java.util.Set;

public interface WordRepository {
    List<Difficulty> getDifficulties() throws NoWordsWithParametersException;

    Difficulty getRandomDifficulty() throws NoWordsWithParametersException;

    List<String> getThemes() throws NoWordsWithParametersException;

    String getRandomTheme() throws NoWordsWithParametersException;

    Set<Word> getWordsByTheme(String theme);

    Word getRandomWordByThemeAndDifficulty(String theme, Difficulty difficulty) throws NoWordsWithParametersException;

    void addWords(List<Word> newWords) throws NoWordsWithParametersException;
}
