package backend.academy.game;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.data.enums.WordTheme;
import backend.academy.game.state.GameState;
import backend.academy.game.state.PreparationState;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import static backend.academy.config.GameConfig.THEMES;
import static backend.academy.utils.GameUtils.pickRandomObject;

@Getter
@Setter
@NoArgsConstructor
@Log4j2
public class GameContext {
    private boolean terminate = false;

    private GameState state = new PreparationState();

    private GameDifficulty difficulty = pickRandomObject(GameDifficulty.values());

    private WordTheme theme = pickRandomObject(THEMES);

    private Word word;

    private BufferedReader inputReader;

    private PrintStream outputWriter;

    public void init(BufferedReader inputReader, PrintStream outputWriter) {
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
