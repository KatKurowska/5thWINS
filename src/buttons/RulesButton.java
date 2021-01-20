package buttons;

import javafx.scene.control.Button;

import static game.Controller.setRulesGrid;

public class RulesButton extends Button {
    public RulesButton() {
        super("Rules");
        ButtonHelper.setButtonDefaultSize(this);
        setOnAction((e) -> setRulesGrid());
    }

}
