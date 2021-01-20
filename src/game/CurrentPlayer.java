package game;

import static game.Player.CIRCLE;
import static game.Player.CROSS;

public class CurrentPlayer {
    private static Player currentPlayer = CROSS;
    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void updatePlayer() {
        if (CIRCLE.equals(currentPlayer)) {
            currentPlayer = CROSS;
        } else {
            currentPlayer = CIRCLE;
        }
    }

}
