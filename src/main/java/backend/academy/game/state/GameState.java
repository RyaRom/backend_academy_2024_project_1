package backend.academy.game.state;

import backend.academy.game.GameContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class GameState {
    protected final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    abstract public void gameCycle(GameContext gameContext);

    abstract public void nextState(GameContext gameContext);
}
