package rules;

import buttons.BackButton;
import game.RulesLabel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import static game.Controller.buttonMatrixSize;
import static utils.GridHelper.getBackgroundGrid;

public class RuleStage {
    public RuleStage() {
    }

    public GridPane getRuleGrid() {
        GridPane grid = getBackgroundGrid();
        fillRulesGrid(grid);
        return grid;
    }

    private void fillRulesGrid(GridPane grid) {
        grid.addRow(1, new Text(""));
        grid.addRow(2, new Text(""));
        grid.addRow(3, new Text(""));
        grid.addRow(4, new Text(""));

        Label label = new RulesLabel().getRuleLabel();
        grid.add(label, 0, 5, buttonMatrixSize, 11);
        grid.addRow(16, new Text(""));
        grid.addRow(17, new Text(""));
        Button backButton = new BackButton();
        grid.add(backButton, 1, 18, buttonMatrixSize, 1);
        grid.setAlignment(Pos.CENTER);
    }
}
