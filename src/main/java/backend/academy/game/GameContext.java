package backend.academy.game;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.state.GameState;
import backend.academy.game.state.PreparationState;
import java.io.BufferedReader;
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
    private GameState state;

    private GameDifficulty difficulty;

    private String theme;

    private Word word;

    private BufferedReader inputReader;

    private PrintStream outputWriter;

    public void init(BufferedReader inputReader, PrintStream outputWriter) {

        this.inputReader = inputReader;
        this.outputWriter = outputWriter;

        state = new PreparationState();
        difficulty = GameDifficulty.EMPTY;
        theme = "";

        state.gameCycle(this);
    }
}
