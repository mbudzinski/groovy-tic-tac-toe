package pl.mbudzinski.tictactoe.validator

import pl.mbudzinski.tictactoe.coordinate.Coordinate

class BoardValidator {


    boolean isCoordinateInBoardBounds(String[][] list, Coordinate coordinate) {
        if (coordinate.row < 0 || (coordinate.row > list.length - 1)) {
            return false
        }
        if (coordinate.column < 0 || (coordinate.column > list[0].length - 1)) {
            return false
        }
        true
    }

    boolean isCellAlreadyOccupied(String[][] board, Coordinate coordinate) {
        !board[coordinate.getRow()][coordinate.getColumn()].trim().isEmpty()
    }

}
