package buttons;

import javafx.scene.control.Button;

public class ExitButton extends Button {
    public ExitButton() {
        super("Exit");
        ButtonHelper.setButtonDefaultSize(this);
        setOnAction((e) -> exitGame());
    }

    private void exitGame() {
        System.exit(0);
    }
}
