package backend.academy.game;

import backend.academy.data.Difficulty;
import backend.academy.data.Word;
import backend.academy.game.state.GameState;
import backend.academy.game.state.PreparationState;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.globalDifficulties;
import static backend.academy.config.GameConfig.globalThemes;
import static backend.academy.utils.GameUtils.pickRandomObject;

@Getter
@Setter
@NoArgsConstructor
@Log4j2
public class GameContext {
    private boolean terminate = false;

    private GameState state = new PreparationState();

    private Difficulty difficulty = pickRandomObject(globalDifficulties);

    private String theme = pickRandomObject(globalThemes);

    private Word word;

    private BufferedReader inputReader;

    private PrintStream outputWriter;

    public void init(BufferedReader inputReader, PrintStream outputWriter) {
        if (globalThemes.isEmpty() || globalDifficulties.isEmpty()) {
            throw new IllegalStateException("No themes or difficulties found in wordlist");
        }

        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        state.gameCycle(this);
    }

    public void finish() {
        try {
            inputReader.close();
            outputWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        terminate = true;
    }
}
