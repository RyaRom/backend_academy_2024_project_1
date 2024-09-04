package backend.academy.game;

import backend.academy.data.Word;
import backend.academy.data.enums.GameDifficulty;
import backend.academy.game.state.GameState;
import backend.academy.game.state.PreparationState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class GameContext {
    private GameState state;

    private GameDifficulty difficulty;

    private String theme;

    private Word word;

    public void init() {
        state = new PreparationState();
        state.gameCycle(this);
        start();
    }

    public void start() {
        state.gameCycle(this);
    }
}
