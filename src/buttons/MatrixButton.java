package buttons;

import ai.AIMove;
import game.CurrentPlayer;
import game.GameScope;
import game.Player;
import javafx.scene.control.Button;

import static game.Controller.betterPlayer;
import static game.Controller.disableAllButtons;
import static game.GameScope.*;

public class MatrixButton extends Button {
    int row;
    int column;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public MatrixButton(String text) {
        super(text);
        setText(" ");
        int buttonSize = 28;
        setMinSize(buttonSize, buttonSize);
        setPrefSize(buttonSize, buttonSize);
        setMaxSize(buttonSize, buttonSize);

        setStyle(String.format("-fx-font-size: %dpx; \"-fx-font-weight: bold", (int) (0.50 * buttonSize)));
        setOnAction((e) -> changeState());
    }

    public MatrixButton(String text, int column, int row) {
        this(text);
        this.column = column;
        this.row = row;
    }

    private void changeState() {
        if (wasClicked()) {
            return;
        }
        if (!singleplayer) {
            if (Player.CROSS.equals(CurrentPlayer.getCurrentPlayer())) {
                this.setText("X");
            } else {
                this.setText("O");
            }
            CurrentPlayer.updatePlayer();
            currentPlayerLabel.updateLabel();
        } else {
            this.setText("X");
        }

        if (isGameOver(this)) {
            GameScope.currentPlayerLabel.setGameOver();
            disableAllButtons();
        }
        else if (singleplayer) {
            AIMove aiMove = new AIMove();
            MatrixButton aiClickedButton = aiMove.getMove(this);
            aiClickedButton.setText("O");
            if (isGameOver(aiClickedButton)) {
                GameScope.currentPlayerLabel.setComputerWon();
                disableAllButtons();
            }
        }
    }

    private boolean isGameOver(MatrixButton button) {
        boolean isGameFinished = winChecker.checkWin(button);
        if (isGameFinished) {
            betterPlayer = this.getText();
        }
        return isGameFinished;
    }

    private boolean wasClicked() {
        String btnText = getText();
        return "X".equals(btnText)
                || "O".equals(btnText);
    }

}
