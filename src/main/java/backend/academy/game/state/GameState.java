package backend.academy.game.state;

import backend.academy.game.GameContext;

public abstract class GameState {

    abstract public void gameCycle(GameContext gameContext);

    abstract public void nextState(GameContext gameContext);
}
