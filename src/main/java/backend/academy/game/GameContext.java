package backend.academy.game;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.state.GameState;
import backend.academy.game.state.PreparationState;
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

    public void init() {
        state = new PreparationState();
        difficulty = GameDifficulty.EMPTY;
        theme = "";

        state.gameCycle(this);
        start();
    }

    public void start() {
        log.info("Game is started");
        state.gameCycle(this);
    }

    public void finish() {
        log.info("Game is finished");
        state.gameCycle(this);
    }
}
