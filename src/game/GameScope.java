package game;

import ai.WinChecker;
import buttons.MatrixButton;
import javafx.stage.Stage;

public class GameScope {
    public static PlayerLabelController currentPlayerLabel;
    public static WinChecker winChecker;
    public static MatrixButton[][] buttonMatrix;
    public static Stage primaryStage;
    public static boolean singleplayer;
}
