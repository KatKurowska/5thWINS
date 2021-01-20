package ai;

import buttons.MatrixButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static game.GameScope.buttonMatrix;

public class MoveFinder {

    protected boolean checkIfContains(List<MatrixButton> buttons, String symbol, int howMany) {
        return buttons.stream().filter(button -> button.getText().equals(symbol)).count() == howMany;
    }

    protected List<MatrixButton> getAllButtonsInBackslash(int column, int row) {
        if (column > row) {
            int firstColumn = column - row;
            return getAllButtonsFromBackSlashDiagonal(firstColumn, 0);
        } else {
            int firstRow = row - column;
            return getAllButtonsFromBackSlashDiagonal(0, firstRow);
        }
    }

    protected List<MatrixButton> getAllButtonsFromFrontslash(int firstColumn, int firstRow) {
        List<MatrixButton> buttons = new ArrayList<>();
        MatrixButton firstInFrontslash = getFirstInFrontSlash(firstColumn, firstRow);
        int currentColumn = firstInFrontslash.getColumn();
        int currentRow = firstInFrontslash.getRow();
        while (currentColumn < buttonMatrix.length && currentRow >= 0) {
            buttons.add(buttonMatrix[currentColumn][currentRow]);
            currentColumn++;
            currentRow--;
        }
        return buttons;
    }

    protected MatrixButton getFirstInFrontSlash(int column, int row) {
        while (row < buttonMatrix.length - 1 && column > 0) {
            row++;
            column--;
        }
        return buttonMatrix[column][row];
    }

    protected List<MatrixButton> getAllButtonsFromBackSlashDiagonal(int firstColumn, int firstRow) {
        List<MatrixButton> buttons = new ArrayList<>();
        MatrixButton firstinBackslesh = getFirstInBackslesh(firstColumn, firstRow);
        int currentColumn = firstColumn;
        int currentRow = firstRow;
        while (currentColumn < buttonMatrix.length && currentRow < buttonMatrix[currentColumn].length) {
            buttons.add(buttonMatrix[currentColumn][currentRow]);
            currentColumn++;
            currentRow++;
        }
        return buttons;
    }

    protected MatrixButton getFirstInBackslesh(int column, int row) {
        while (row > 0 && column > 0) {
            row--;
            column--;
        }
        return buttonMatrix[column][row];
    }

    protected boolean oneIsClickable(List<MatrixButton> fiveToCheck) {
        return fiveToCheck.stream().filter(button -> button.getText().equals(" ")).findAny().isPresent();
    }

    protected MatrixButton getClickableButton(List<MatrixButton> fiveToCheck) {
        return fiveToCheck.stream().filter(button -> button.getText().equals(" ")).findFirst().orElseThrow();
    }


    protected List<MatrixButton> getAllButtonsInColumn(int column) {
        return Arrays.asList(buttonMatrix[column]);
    }

    protected List<MatrixButton> getAllButtonsInRow(int row) {
        List<MatrixButton> buttons = new ArrayList<>();
        for (int i = 0; i < buttonMatrix.length; i++) {
            buttons.add(buttonMatrix[i][row]);
        }
        return buttons;
    }

    protected Optional<MatrixButton> findMoveInLine(List<MatrixButton> allButtonsInLine, String symbol, int alreadyInLine) {
        if (checkIfContains(allButtonsInLine, symbol, alreadyInLine)) {
            for (int i = 0; i < allButtonsInLine.size() - alreadyInLine - 1; i++) {
                List<MatrixButton> threeToCheck = allButtonsInLine.subList(i, i + alreadyInLine + 1);
                if (checkIfContains(threeToCheck, symbol, alreadyInLine) && oneIsClickable(threeToCheck)) {
                    return Optional.of(getClickableButton(threeToCheck));
                }
            }
        }
        return Optional.empty();
    }

    protected Optional<MatrixButton> getMove(int alreadyInLine, int column, int row, String symbol){
        Optional<MatrixButton> moveButtonOpt = findInRowMove(alreadyInLine, row, symbol);
        if (moveButtonOpt.isPresent()) {
            return moveButtonOpt;
        }
        moveButtonOpt = findMoveInColumn(alreadyInLine,column, symbol);

        if (moveButtonOpt.isPresent()) {
            return moveButtonOpt;
        }
        moveButtonOpt = findMoveInBackslash(alreadyInLine,column, row, symbol);
        if (moveButtonOpt.isPresent()) {
            return moveButtonOpt;
        }
        moveButtonOpt = findMoveInFrontslash(alreadyInLine,column, row, symbol);
        if (moveButtonOpt.isPresent()) {
            return moveButtonOpt;
        }
        return Optional.empty();
    }

    private Optional<MatrixButton> findMoveInColumn(int alreadyInLine, int column, String symbol) {
        List<MatrixButton> allButtonsInRow = getAllButtonsInColumn(column);
        return findMoveInLine(allButtonsInRow, symbol, alreadyInLine);
    }

    private Optional<MatrixButton> findInRowMove(int alreadyInLine, int row, String symbol) {
        List<MatrixButton> allButtonsInRow = getAllButtonsInRow(row);
        return findMoveInLine(allButtonsInRow, symbol, alreadyInLine);
    }

    private Optional<MatrixButton> findMoveInBackslash(int alreadyInLine, int column, int row, String symbol) {
        List<MatrixButton> allButtonsInRow = getAllButtonsInBackslash(column, row);
        return findMoveInLine(allButtonsInRow, symbol, alreadyInLine);
    }

    private Optional<MatrixButton> findMoveInFrontslash(int alreadyInLine, int column, int row, String symbol) {
        List<MatrixButton> allButtonsInRow = getAllButtonsFromFrontslash(column, row);
        return findMoveInLine(allButtonsInRow, symbol, alreadyInLine);
    }
}
