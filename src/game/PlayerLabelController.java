package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PlayerLabelController {
    private String labelText;
    private Label playerLabel;


    public PlayerLabelController() {
        playerLabel = new Label();
        setInitialPlayerLabelText();
        playerLabel.setFont(new Font("Arial", 16));
        playerLabel.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        playerLabel.setAlignment(Pos.CENTER);
    }

    public void setInitialPlayerLabelText() {
        if (GameScope.singleplayer) {
            labelText = "You play as: X";
        } else {
            labelText = "Now playing: " +convertPlayerToSymbol();
        }
        playerLabel.setText(labelText);
    }

    private String convertPlayerToSymbol() {
        return CurrentPlayer.getCurrentPlayer().equals(Player.CIRCLE) ? "O" : "X";
    }


    public Label getPlayerLabel() {
        return playerLabel;
    }

    public void updateLabel() {
        if (Player.CROSS.equals(CurrentPlayer.getCurrentPlayer())) {
            playerLabel.setText("Now playing: X");
        } else {
            playerLabel.setText("Now playing: O");
        }

    }

    public void setGameOver() {
        playerLabel.setText("GAME OVER: " + Controller.betterPlayer + " WON!!!");
    }

    public void setComputerWon() {
        playerLabel.setText("GAME OVER: YOU LOST");
    }

    public void reset() {
        playerLabel.setText("Now playing: " + convertPlayerToSymbol());
    }

}
