package buttons;

import javafx.scene.control.Button;

import static game.Controller.setGameGrid;

public class SinglePlayerButton extends Button {
    public SinglePlayerButton() {
        super("Solo Play");
        ButtonHelper.setButtonDefaultSize(this);
        setOnAction((e) -> setGameGrid(true));
    }
}
