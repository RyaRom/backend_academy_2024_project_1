package backend.academy;

import backend.academy.game.GameContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) {
        GameContext gameContext = new GameContext();
        gameContext.init(new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)), System.out);
    }
}
