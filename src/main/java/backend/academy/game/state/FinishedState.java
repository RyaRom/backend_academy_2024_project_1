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
        if (gameContext.terminate()) {
            return;
        }
        var screen = isVictory ? VICTORY_SCREEN : DEATH_SCREEN;
        gameContext.outputWriter().printf((screen) + "%n", gameContext.word());

        int menuChose = readCommand(
            gameContext.inputReader(),
            gameContext.outputWriter(),
            1, 2);
        switch (menuChose) {
            case 1 -> nextState(gameContext);
            case 2 -> gameContext.finish();
            default -> throw new IllegalStateException("Unexpected value: " + menuChose);
        }
    }

    @Override
    public void nextState(GameContext gameContext) {
        final var input = gameContext.inputReader();
        final var output = gameContext.outputWriter();
        GameContext newGameContext = new GameContext();
        newGameContext.init(input, output);
    }
}
