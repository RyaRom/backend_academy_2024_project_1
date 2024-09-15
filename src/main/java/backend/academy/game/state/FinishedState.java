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
        var screen = isVictory ? VICTORY_SCREEN : DEATH_SCREEN;
        gameContext.outputWriter().println(screen.formatted(gameContext.word()));

        int menuChose = readCommand(gameContext.inputReader(), gameContext.outputWriter(), 1, 2);
        switch (menuChose) {
            case 1 -> nextState(gameContext);
            case 2 -> gameContext.finish();
            default -> gameCycle(gameContext);
        }
    }

    @Override
    public void nextState(GameContext gameContext) {
        final var input = gameContext.inputReader();
        final var output = gameContext.outputWriter();
        final boolean isTestMode = gameContext.testMode();
        GameContext newGameContext = new GameContext();
        newGameContext.testMode(isTestMode);
        newGameContext.init(input, output);
    }
}
