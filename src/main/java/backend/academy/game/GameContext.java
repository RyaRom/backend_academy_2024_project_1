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

@Getter
@Setter
@NoArgsConstructor
@Log4j2
public class GameContext {
    private boolean terminate = false;

    private GameState state = new PreparationState();

    private Difficulty difficulty;

    private String theme;

    private Word word;

    private BufferedReader inputReader;

    private PrintStream outputWriter;

    public void init(BufferedReader inputReader, PrintStream outputWriter) {
        this.inputReader = inputReader;
        this.outputWriter = outputWriter;
        if (state instanceof PreparationState) {
            ((PreparationState) state).loadContext(this);
        } else {
            throw new IllegalStateException();
        }
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
