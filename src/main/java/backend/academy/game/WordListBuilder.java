package backend.academy.game;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.utils.FileParser;
import backend.academy.utils.GameUtils;
import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import static backend.academy.config.GameConfig.CUSTOM_WORD_FILE_LOCATION;
import static backend.academy.config.GameConfig.STAGES;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GameUtils.readWord;

@Getter
@Setter
@ToString
@NoArgsConstructor
@SuppressWarnings("MagicNumber")
public final class WordListBuilder {
    private String name = String.valueOf(new SecureRandom().nextInt(10000));

    private List<Word> words = new ArrayList<>();

    public void createWord(GameContext gameContext) {
        gameContext.outputWriter().println("Enter word content:");
        String content = readWord(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            GameUtils.ReadWordMode.WORD);
        gameContext.outputWriter().println("Enter word theme:");
        String theme = readWord(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            GameUtils.ReadWordMode.DESCRIPTION);
        gameContext.outputWriter().println("Enter word hint:");
        String hint = readWord(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            GameUtils.ReadWordMode.HINT);
        gameContext.outputWriter().println("Enter word difficulty name:");
        String difficultyName = readWord(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            GameUtils.ReadWordMode.DESCRIPTION);
        gameContext.outputWriter().printf("Enter word difficulty level (0-%s):", STAGES);
        int difficultyLevel = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            0, STAGES);

        Difficulty newDif = Difficulty.builder()
            .name(difficultyName)
            .level(difficultyLevel)
            .build();
        Word newWord = Word.builder()
            .content(content)
            .theme(theme)
            .hint(hint)
            .difficulty(newDif)
            .build();
        words.add(newWord);
    }

    public void changeName(GameContext gameContext) {
        gameContext.outputWriter().println("Enter new file name:");
        name = readWord(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            GameUtils.ReadWordMode.WORD);
    }

    public void save() {
        if (words.isEmpty()) {
            return;
        }

        File newJson = new File(
            CUSTOM_WORD_FILE_LOCATION
                + name + "_wordlist.json"
        );
        FileParser.saveWordlistToFile(words, newJson);
    }
}
