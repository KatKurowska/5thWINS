package buttons;

import javafx.scene.control.Button;

import static game.Controller.setGameGrid;

public class StartButton extends Button {
    public StartButton() {
        super("Start");
        ButtonHelper.setButtonDefaultSize(this);
        setOnAction((e) -> setGameGrid(false));
    }
}
