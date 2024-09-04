package backend.academy;

import backend.academy.game.GameContext;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        GameContext gameContext = new GameContext();
        gameContext.init();
    }
}
