package buttons;

import javafx.scene.control.Button;

import static buttons.ResetButton.resetGame;
import static game.Controller.setStartGrid;

public class BackButton extends Button {
    public BackButton() {
        super("Back");
        setOnAction((e) -> {
            resetGame();
            setStartGrid();
        });
    }

}
