package ai;

import buttons.MatrixButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static game.Controller.buttonMatrixSize;

public class WinChecker {
    MatrixButton[][] buttonMatrix;

    public WinChecker(MatrixButton[][] buttonMatrix) {
        this.buttonMatrix = buttonMatrix;
    }

    public boolean checkWin(MatrixButton clickedButton) {
        String playerSymbol = clickedButton.getText();
        List<MatrixButton> rowLineButtons = getRowLineButtons(clickedButton);
        List<MatrixButton> columnLineButtons = getColumnLineButtons(clickedButton);
        List<MatrixButton> backslashButtons = getBackslashButtons(clickedButton);
        List<MatrixButton> frontslashButtons = getFrontSlashButtons(clickedButton);
        List<List<MatrixButton>> buttonsLists = Arrays.asList(rowLineButtons, columnLineButtons, backslashButtons, frontslashButtons);

        return validateButtonsLists(buttonsLists, playerSymbol);
    }

    private boolean validateButtonsLists(List<List<MatrixButton>> buttonsLists, String playerSymbol) {
        return buttonsLists.stream().filter(buttons -> validateList(buttons, playerSymbol)).findAny().isPresent();
    }

    private boolean validateList(List<MatrixButton> buttons, String playerSymbol) {
        int score = 0;
        for (MatrixButton button : buttons) {
            if (playerSymbol.equals(button.getText())) {
                score++;
            } else {
                score = 0;
            }
            if (score == 5) {
                int winningButtonIndex = buttons.indexOf(button);
                setButtonsColor(buttons, winningButtonIndex, playerSymbol);
                return true;
            }
        }
        return false;
    }

    private void setButtonsColor(List<MatrixButton> buttons, int winningButtonIndex, String playerSymbol) {
        List<MatrixButton> winningButtons = getFirstWinningButtons(buttons, winningButtonIndex);
        addAdditionalButtons(buttons, winningButtonIndex, playerSymbol, winningButtons);
        winningButtons.forEach(button -> button.setStyle("-fx-background-color: #00ff00"));

    }

    private void addAdditionalButtons(List<MatrixButton> buttons, int winningButtonIndex, String playerSymbol, List<MatrixButton> winningButtons) {
        while (buttons.size() > winningButtonIndex + 1) {
            if (buttons.get(winningButtonIndex + 1).getText().equals(playerSymbol)) {
                winningButtons.add(buttons.get(winningButtonIndex + 1));
                winningButtonIndex++;
            } else {
                break;
            }
        }
    }

    private List<MatrixButton> getFirstWinningButtons(List<MatrixButton> buttons, int winningButtonIndex) {
        return buttons.stream()
                .filter(button -> buttons.indexOf(button) <= winningButtonIndex && buttons.indexOf(button) > winningButtonIndex - 5)
                .collect(Collectors.toList());
    }

    private List<MatrixButton> getFrontSlashButtons(MatrixButton clickedButton) {
        List<MatrixButton> buttons = new ArrayList<>();
        int columnIndex = clickedButton.getColumn();
        int rowIndex = clickedButton.getRow();

        if ((columnIndex + 4 < buttonMatrixSize) && (rowIndex - 4 >= 0)) {
            buttons.add(buttonMatrix[columnIndex + 4][rowIndex - 4]);
        }
        if ((columnIndex + 3 < buttonMatrixSize) && (rowIndex - 3 >= 0)) {
            buttons.add(buttonMatrix[columnIndex + 3][rowIndex - 3]);
        }
        if ((columnIndex + 2 < buttonMatrixSize) && (rowIndex - 2 >= 0)) {
            buttons.add(buttonMatrix[columnIndex + 2][rowIndex - 2]);
        }
        if ((columnIndex + 1 < buttonMatrixSize) && (rowIndex - 1 >= 0)) {
            buttons.add(buttonMatrix[columnIndex + 1][rowIndex - 1]);
        }
        buttons.add(clickedButton);
        if ((columnIndex - 4 >= 0) && (rowIndex + 4 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex - 4][rowIndex + 4]);
        }
        if ((columnIndex - 3 >= 0) && (rowIndex + 3 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex - 3][rowIndex + 3]);
        }
        if ((columnIndex - 2 >= 0) && (rowIndex + 2 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex - 2][rowIndex + 2]);
        }
        if ((columnIndex - 1 >= 0) && (rowIndex + 1 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex - 1][rowIndex + 1]);
        }

        return buttons;
    }

    private List<MatrixButton> getBackslashButtons(MatrixButton clickedButton) {
        List<MatrixButton> buttons = new ArrayList<>();
        int columnIndex = clickedButton.getColumn();
        int rowIndex = clickedButton.getRow();


        if ((columnIndex + 4 < buttonMatrixSize) && (rowIndex + 4 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex + 4][rowIndex + 4]);
        }
        if ((columnIndex + 3 < buttonMatrixSize) && (rowIndex + 3 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex + 3][rowIndex + 3]);
        }
        if ((columnIndex + 2 < buttonMatrixSize) && (rowIndex + 2 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex + 2][rowIndex + 2]);
        }
        if ((columnIndex + 1 < buttonMatrixSize) && (rowIndex + 1 < buttonMatrixSize)) {
            buttons.add(buttonMatrix[columnIndex + 1][rowIndex + 1]);
        }
        buttons.add(clickedButton);
        if ((columnIndex - 1 >= 0) && (rowIndex - 1 >= 0)) {
            buttons.add(buttonMatrix[columnIndex - 1][rowIndex - 1]);
        }
        if ((columnIndex - 2 >= 0) && (rowIndex - 2 >= 0)) {
            buttons.add(buttonMatrix[columnIndex - 2][rowIndex - 2]);
        }
        if ((columnIndex - 3 >= 0) && (rowIndex - 3 >= 0)) {
            buttons.add(buttonMatrix[columnIndex - 3][rowIndex - 3]);
        }
        if ((columnIndex - 4 >= 0) && (rowIndex - 4 >= 0)) {
            buttons.add(buttonMatrix[columnIndex - 4][rowIndex - 4]);
        }

        return buttons;
    }


    private List<MatrixButton> getRowLineButtons(MatrixButton clickedButton) {
        List<MatrixButton> buttons = new ArrayList<>();
        int columnIndex = clickedButton.getColumn();
        int rowIndex = clickedButton.getRow();
        if (columnIndex - 4 >= 0) {
            buttons.add(buttonMatrix[columnIndex - 4][rowIndex]);
        }
        if (columnIndex - 3 >= 0) {
            buttons.add(buttonMatrix[columnIndex - 3][rowIndex]);
        }
        if (columnIndex - 2 >= 0) {
            buttons.add(buttonMatrix[columnIndex - 2][rowIndex]);
        }
        if (columnIndex - 1 >= 0) {
            buttons.add(buttonMatrix[columnIndex - 1][rowIndex]);
        }
        buttons.add(clickedButton);
        if (columnIndex + 1 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex + 1][rowIndex]);
        }
        if (columnIndex + 2 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex + 2][rowIndex]);
        }
        if (columnIndex + 3 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex + 3][rowIndex]);
        }
        if (columnIndex + 4 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex + 4][rowIndex]);
        }
        return buttons;
    }

    private List<MatrixButton> getColumnLineButtons(MatrixButton clickedButton) {
        List<MatrixButton> buttons = new ArrayList<>();

        int rowIndex = clickedButton.getRow();
        int columnIndex = clickedButton.getColumn();
        if (rowIndex - 4 >= 0) {
            buttons.add(buttonMatrix[columnIndex][rowIndex - 4]);
        }
        if (rowIndex - 3 >= 0) {
            buttons.add(buttonMatrix[columnIndex][rowIndex - 3]);
        }
        if (rowIndex - 2 >= 0) {
            buttons.add(buttonMatrix[columnIndex][rowIndex - 2]);
        }
        if (rowIndex - 1 >= 0) {
            buttons.add(buttonMatrix[columnIndex][rowIndex - 1]);
        }
        buttons.add(clickedButton);
        if (rowIndex + 1 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex][rowIndex + 1]);
        }
        if (rowIndex + 2 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex][rowIndex + 2]);
        }
        if (rowIndex + 3 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex][rowIndex + 3]);
        }
        if (rowIndex + 4 < buttonMatrixSize) {
            buttons.add(buttonMatrix[columnIndex][rowIndex + 4]);
        }
        return buttons;
    }
}
