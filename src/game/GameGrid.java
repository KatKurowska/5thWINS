package game;

import ai.WinChecker;
import buttons.BackButton;
import buttons.MatrixButton;
import buttons.ResetButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static game.Controller.buttonMatrixSize;
import static utils.GridHelper.getBackgroundGrid;

public class GameGrid {
    int boardMoved = 12;

    public GridPane getGameGrid() {
        GridPane grid = getBackgroundGrid();
        modifyGridLayout(grid);
        return grid;
    }

    private void modifyGridLayout(GridPane grid) {
        grid.addRow(1, new Text(""));
        grid.addRow(2, new Text(""));

        //reset
        addResetButton(grid);
        addBackButton(grid);

        //player label
        PlayerLabelController playerLabelController = new PlayerLabelController();
        GameScope.currentPlayerLabel = playerLabelController;
        grid.addRow(4, new Text(""));
        grid.add(playerLabelController.getPlayerLabel(), 10, 5, buttonMatrixSize, 2);

        //empty rows
        grid.addRow(6, new Text(""));
        grid.addRow(7, new Text(""));


        //button matrix
        MatrixButton[][] buttonMatrix = getButtonMatrix(buttonMatrixSize);
        GameScope.buttonMatrix = buttonMatrix;
        GameScope.winChecker = new WinChecker(buttonMatrix);
        addButtonsToGrid(grid, buttonMatrix);
        grid.setAlignment(Pos.CENTER);
    }

    private void addBackButton(GridPane grid) {
        Button back = new BackButton();
        grid.add(back, 19, 3, buttonMatrixSize, 1);
        grid.setAlignment(Pos.CENTER);

    }

    private void addResetButton(GridPane grid) {
        Button reset = new ResetButton();
        grid.add(reset, 1, 3, buttonMatrixSize, 1);
        grid.setAlignment(Pos.CENTER);
    }

    private MatrixButton[][] getButtonMatrix(int size) {
        MatrixButton[][] buttonMatrix = new MatrixButton[size][size];
        for (int column = 0; column < buttonMatrix.length; column++) {
            for (int row = 0; row < buttonMatrix[column].length; row++) {
                buttonMatrix[column][row] = new MatrixButton("", column, row);
            }
        }
        return buttonMatrix;
    }

    private void addButtonsToGrid(GridPane grid, Button[][] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                grid.add(buttons[i][j], i, j + boardMoved);
            }
        }
    }
}
