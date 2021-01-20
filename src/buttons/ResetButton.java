package buttons;

import game.CurrentPlayer;
import game.GameScope;
import javafx.scene.control.Button;

import java.util.Arrays;

import static game.GameScope.buttonMatrix;

public class ResetButton extends Button {
    public ResetButton() {
        super("Reset");
        setOnAction((e) -> resetGame());
    }

    public static void resetGame() {
        if (buttonMatrix != null) {
            Arrays.stream(buttonMatrix).forEach(buttons -> resetButton(buttons));
        }
        CurrentPlayer.updatePlayer();
        GameScope.currentPlayerLabel.reset();
    }

    private static void resetButton(MatrixButton[] buttons) {
        Arrays.stream(buttons).forEach(button -> resetButton(button));
    }

    private static void resetButton(MatrixButton button) {
        button.setDisable(false);
        button.setStyle("");
        button.setText(" ");
    }
}
