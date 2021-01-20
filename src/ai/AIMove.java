package ai;

import buttons.MatrixButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static game.GameScope.buttonMatrix;
import static java.util.Arrays.asList;
import static java.util.concurrent.ThreadLocalRandom.current;

public class AIMove {
    private MoveFinder moveFinder = new MoveFinder();

    public MatrixButton getMove(MatrixButton playerPlayedButton) {
        //AI thinking - finding/blocking 5th in line and then the same for 4th, 3rd etc
        return getMoveOpt(getOppositePlayerSymbol(playerPlayedButton.getText()), 4)
                .orElse(getMove(playerPlayedButton, 4)
                        .orElse(getMoveOpt(getOppositePlayerSymbol(playerPlayedButton.getText()), 3)
                                .orElse(getMove(playerPlayedButton, 3)
                                        .orElse(getMoveOpt(getOppositePlayerSymbol(playerPlayedButton.getText()), 2)
                                                .orElse(getMove(playerPlayedButton, 2)
                                                        .orElse(getRandomMoveFromList(getClosestPossibleButtons(playerPlayedButton))
                                                                .orElse(getRandomMove())))))));

    }

    private Optional<MatrixButton> getMove(MatrixButton playerPlayedButton, int alreadyInLine) {
        int row = playerPlayedButton.getRow();
        int column = playerPlayedButton.getColumn();
        String symbol = playerPlayedButton.getText();
        return moveFinder.getMove(alreadyInLine, column, row, symbol);
    }

    public Optional<MatrixButton> getMoveOpt(String symbol, int alreadyInLine) {
        Optional<Integer> firstColumnToFinish = getAllColumnsContaining(symbol).stream().filter(column -> moveFinder.getMove(alreadyInLine, column, 0, symbol).isPresent()).findFirst();
        if (firstColumnToFinish.isPresent()) {
            return moveFinder.getMove(alreadyInLine, firstColumnToFinish.get(), 0, symbol);
        }
        return Optional.empty();
    }

    private String getOppositePlayerSymbol(String text) {
        if ("X".equals(text)) {
            return "O";
        } else {
            return "X";
        }
    }

    private List<MatrixButton> getClosestPossibleButtons(MatrixButton centerButton) {
        int column = centerButton.getColumn();
        int row = centerButton.getRow();
        return getAdjacent(column, row).stream().filter(button -> button.getText().equals(" ")).collect(Collectors.toList());
    }

    private Optional<MatrixButton> getRandomMoveFromList(List<MatrixButton> possibleMoves) {
        if (possibleMoves == null || possibleMoves.size() == 0) {
            return Optional.empty();
        }
        int randomIndex = current().nextInt(0, possibleMoves.size());
        return Optional.ofNullable(possibleMoves.get(randomIndex));
    }


    private List<MatrixButton> getAdjacent(int column, int row) {
        List<MatrixButton> adjacentButtons = new ArrayList<>();
        if (validateCoordinates(column + 1, row + 1)) {
            adjacentButtons.add(buttonMatrix[column + 1][row + 1]);
        }
        if (validateCoordinates(column + 1, row)) {
            adjacentButtons.add(buttonMatrix[column + 1][row]);
        }
        if (validateCoordinates(column + 1, row - 1)) {
            adjacentButtons.add(buttonMatrix[column + 1][row - 1]);
        }

        if (validateCoordinates(column - 1, row - 1)) {
            adjacentButtons.add(buttonMatrix[column - 1][row - 1]);
        }
        if (validateCoordinates(column - 1, row + 1)) {
            adjacentButtons.add(buttonMatrix[column - 1][row + 1]);
        }
        if (validateCoordinates(column - 1, row)) {
            adjacentButtons.add(buttonMatrix[column - 1][row]);
        }
        if (validateCoordinates(column, row + 1)) {
            adjacentButtons.add(buttonMatrix[column][row + 1]);
        }
        if (validateCoordinates(column, row - 1)) {
            adjacentButtons.add(buttonMatrix[column][row - 1]);
        }
        return adjacentButtons;
    }

    private boolean validateCoordinates(int column, int row) {
        return column > 0 && column < buttonMatrix.length - 1 && row > 0 && row < buttonMatrix.length - 1;
    }

    private MatrixButton getRandomMove() {
        List<MatrixButton> possibleMoves = getPossibleMoves();
        int randomIndex = current().nextInt(0, possibleMoves.size());
        return possibleMoves.get(randomIndex);
    }

    private List<MatrixButton> getPossibleMoves() {
        List<MatrixButton> possibleMoves = new ArrayList<>();
        asList(buttonMatrix).stream().forEach(buttons -> addPossibleMove(possibleMoves, buttons));
        return possibleMoves;
    }

    private List<Integer> getAllColumnsContaining(String symbol) {
        List<Integer> columns = new ArrayList<>();
        asList(buttonMatrix).stream().forEach(
                buttonArray -> asList(buttonArray).stream()
                        .filter(button -> button.getText().equals(symbol))
                        .forEach(button -> columns.add(button.getColumn()))
        );
        return columns;
    }

    private void addPossibleMove(List<MatrixButton> possibleMoves, MatrixButton[] buttonMatrixLine) {
        asList(buttonMatrixLine).stream().filter(this::isMovePossible).forEach(button -> possibleMoves.add(button));
    }

    private boolean isMovePossible(MatrixButton button) {
        return " ".equals(button.getText());
    }
}
