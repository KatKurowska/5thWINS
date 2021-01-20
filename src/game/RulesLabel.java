package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class RulesLabel {
    private String labelText;
    private Label rulesLabel;


    public RulesLabel() {
        labelText = " TO WIN THE GAME PLACE FIVE X SYMBOLS IN ROW (line or diagonally). \n\n\n"
                + " YOUR OPPONENT IS GOING TO STOP YOU AND PUT FIVE O SYMBOLS INSTEAD. \n\n "
                + " YOU CAN ALSO PLAY WITH ANOTHER PERSON USING HOT-SEAT MODE. \n\n\n";



        rulesLabel = new Label();
        rulesLabel.setText(labelText);
        rulesLabel.setAlignment(Pos.CENTER);
        rulesLabel.setStyle("-fx-font-size: 15;");
        rulesLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Label getRuleLabel() {
        return rulesLabel;
    }
}
