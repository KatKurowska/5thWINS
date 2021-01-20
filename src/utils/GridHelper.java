package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static game.Controller.buttonMatrixSize;
import static javafx.scene.layout.BackgroundPosition.CENTER;
import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;
import static javafx.scene.layout.BackgroundRepeat.REPEAT;

public final class GridHelper {
    private GridHelper() {
    }

    public static GridPane getBackgroundGrid() {
        GridPane grid = new GridPane();
        Image bgImage = new Image("file:resource/bg.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(bgImage, REPEAT, NO_REPEAT, CENTER, backgroundSize);
        grid.setBackground(new Background(backgroundImage));

        Label titleLabel = getTitleLabel();
        titleLabel.setAlignment(Pos.CENTER);
        grid.add(titleLabel, 10, 0, buttonMatrixSize, 2);

        return grid;
    }

    private static Label getTitleLabel() {
        Label label = new Label("The 5th Wins!");
        label.setFont(new Font("Arial", 22));
        label.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        label.setAlignment(Pos.CENTER);
        return label;
    }
}
