package backend.academy.game.state;

import backend.academy.game.GameContext;

public interface GameState {
    void gameCycle(GameContext gameContext);

    void nextState(GameContext gameContext);
}
