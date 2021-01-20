package start;

import buttons.ExitButton;
import buttons.RulesButton;
import buttons.SinglePlayerButton;
import buttons.StartButton;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static game.Controller.buttonMatrixSize;
import static javafx.geometry.Pos.CENTER;
import static utils.GridHelper.getBackgroundGrid;

public class StartMenu {
    public GridPane getStartGrid() {
        GridPane grid = getBackgroundGrid();
        fillStartGrid(grid);
        return grid;
    }

    private void fillStartGrid(GridPane grid) {
        int i = 1;
        grid.addRow(++i, new Text(""));
        grid.addRow(++i, new Text(""));
        grid.addRow(++i, new Text(""));
        Button startButton = new StartButton();
        startButton.setAlignment(CENTER);

        grid.add(startButton, 10, ++i, buttonMatrixSize, 1);
        grid.addRow(++i, new Text(""));
        grid.addRow(++i, new Text(""));
        Button singlePlayerButton = new SinglePlayerButton();
        singlePlayerButton.setAlignment(CENTER);

        grid.add(singlePlayerButton, 10, ++i, buttonMatrixSize, 1);

        grid.addRow(++i, new Text(""));
        grid.addRow(++i, new Text(""));
        Button rulesButton = new RulesButton();
        rulesButton.setAlignment(CENTER);
        grid.add(rulesButton, 10, ++i, buttonMatrixSize, 1);

        grid.addRow(++i, new Text(""));
        grid.addRow(++i, new Text(""));
        Button exitButton = new ExitButton();
        exitButton.setAlignment(CENTER);
        grid.add(exitButton, 10, ++i, buttonMatrixSize, 1);

        grid.setAlignment(CENTER);
    }


}
