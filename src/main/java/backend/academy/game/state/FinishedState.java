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
public class FinishedState extends GameState {
    private final boolean isVictory;

    @Override
    public void gameCycle(GameContext gameContext) {
        clearScreen();
        if (isVictory) {
            System.out.println(VICTORY_SCREEN);
        } else {
            System.out.println(DEATH_SCREEN);
        }

        int menuChose = readCommand(inputReader, 1, 2);
        switch (menuChose) {
            case 1 -> nextState(gameContext);
            case 2 -> System.exit(0);
        }
    }

    @Override
    public void nextState(GameContext gameContext) {
        gameContext.init();
    }
}
