package game;

import buttons.MatrixButton;
import javafx.scene.Scene;
import rules.RuleStage;
import start.StartMenu;

import static game.GameScope.primaryStage;
import static javafx.scene.paint.Color.BLACK;

public class Controller {
    public static int buttonMatrixSize = 22;
    public static Scene gameScene = new Scene(new GameGrid().getGameGrid(), 1600, 900, BLACK);
    public static Scene startScene = new Scene(new StartMenu().getStartGrid(), 1600, 900, BLACK);
    public static Scene rulesScene = new Scene(new RuleStage().getRuleGrid(), 1600, 900, BLACK);
    public static String betterPlayer = "O";


    public static void disableAllButtons() {
        MatrixButton[][] buttonMatrix = GameScope.buttonMatrix;
        for (int column = 0; column < buttonMatrix.length; column++) {
            for (int row = 0; row < buttonMatrix[column].length; row++) {
                buttonMatrix[column][row].setDisable(true);
                buttonMatrix[column][row].setOpacity(1);
            }
        }
    }

    public static void setGameGrid(boolean singlePlayer) {
        GameScope.singleplayer = singlePlayer;
        GameScope.currentPlayerLabel.setInitialPlayerLabelText();
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    public static void setStartGrid() {
        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    public static void setRulesGrid() {
        primaryStage.setScene(rulesScene);
        primaryStage.show();
    }
}
