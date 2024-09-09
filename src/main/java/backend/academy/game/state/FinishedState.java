package backend.academy.game.state;

import backend.academy.game.GameContext;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import static backend.academy.utils.GameUtils.readCommand;
import static backend.academy.utils.GraphicUtils.DEATH_SCREEN;
import static backend.academy.utils.GraphicUtils.VICTORY_SCREEN;
import static backend.academy.utils.GraphicUtils.clearScreen;

@RequiredArgsConstructor
@Getter
@Setter
public class FinishedState implements GameState {
    private final boolean isVictory;

    @Override
    public void gameCycle(GameContext gameContext) {
        clearScreen(gameContext.outputWriter());
        if (Thread.currentThread().isInterrupted()) {
            return;
        }
        if (isVictory) {
            gameContext.outputWriter().println(VICTORY_SCREEN.formatted(gameContext.word().content()));
        } else {
            gameContext.outputWriter().println(DEATH_SCREEN.formatted(gameContext.word().content()));
        }

        int menuChose = readCommand(gameContext.inputReader(), gameContext.outputWriter(), 1, 2);
        switch (menuChose) {
            case 1 -> nextState(gameContext);
            case 2 -> gameContext.finish();
            default -> gameCycle(gameContext);
        }
    }

    @Override
    public void nextState(GameContext gameContext) {
        gameContext.init(gameContext.inputReader(), gameContext.outputWriter());
    }
}
