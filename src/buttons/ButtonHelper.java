package buttons;

import javafx.scene.control.Button;

public final class ButtonHelper {
    public static void setButtonDefaultSize(Button button) {
        int buttonSize = 40;
        button.setMinSize(buttonSize*3, buttonSize);
        button.setPrefSize(buttonSize*3, buttonSize);
        button.setMaxSize(buttonSize*3, buttonSize);
        button.setStyle(String.format("-fx-font-size: %dpx;", (int) (0.45 * buttonSize)));
    }
}
