package backend.academy.game.state;

import backend.academy.game.GameContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GraphicUtils.DEATH_SCREEN;
import static backend.academy.utils.GraphicUtils.VICTORY_SCREEN;

@RequiredArgsConstructor
@Getter
@Setter
public class FinishedState extends GameState {
    private final boolean isVictory;

    @Override
    public void gameCycle(GameContext gameContext) {
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        if (isVictory) {
            gameContext.outputWriter().println(VICTORY_SCREEN);
        } else {
            gameContext.outputWriter().println(DEATH_SCREEN);
        }

        int menuChose = readCommand(gameContext.inputReader(), gameContext.outputWriter(), 1, 2);
        switch (menuChose) {
            case 1 -> nextState(gameContext);
            case 2 -> {
                gameContext.outputWriter().close();
                Thread.currentThread().interrupt();
            }
            default -> gameCycle(gameContext);
        }
    }

    @Override
    public void nextState(GameContext gameContext) {
        gameContext.init(gameContext.inputReader(), gameContext.outputWriter());
    }
}
