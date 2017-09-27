package pl.mbudzinski.tictactoe.direction

import pl.mbudzinski.tictactoe.player.Player
import pl.mbudzinski.tictactoe.coordinate.Coordinate

import java.util.function.Predicate

class DirectionPredicateSupplier {

    Predicate<Direction> getEqualityPredicateForDirection(Direction direction,
                                                          String[][] board,
                                                          Coordinate coordinate,
                                                          Player activePlayer,
                                                          int index) {
        switch (direction){
            case Direction.UP:
                return { board[coordinate.row - index][coordinate.column] == activePlayer.sign }
            case Direction.DOWN:
                return { board[coordinate.row + index][coordinate.column] == activePlayer.sign }
            case Direction.LEFT:
                return { board[coordinate.row][coordinate.column - index] == activePlayer.sign }
            case Direction.RIGHT:
                return { board[coordinate.row][coordinate.column + index] == activePlayer.sign }
            case Direction.UP_LEFT:
                return { board[coordinate.row - index][coordinate.column - index] == activePlayer.sign }
            case Direction.UP_RIGHT:
                return { board[coordinate.row - index][coordinate.column + index] == activePlayer.sign }
            case Direction.DOWN_LEFT:
                return { board[coordinate.row + index][coordinate.column - index] == activePlayer.sign }
            case Direction.DOWN_RIGHT:
                return { board[coordinate.row + index][coordinate.column + index] == activePlayer.sign }
            default:
                throw new IllegalArgumentException('Direction not yet implemented!')
        }
    }

    Predicate<Coordinate> getBoundedPredicateForDirection(Direction direction,
                                                          Coordinate coordinate,
                                                          int upperBound,
                                                          int index) {

        int lowerBound = 0
        switch (direction) {
            case Direction.UP:
                return { coordinate.row - index >= lowerBound }
            case Direction.DOWN:
                return { coordinate.row + index < upperBound }
            case Direction.LEFT:
                return { coordinate.column - index >= lowerBound }
            case Direction.RIGHT:
                return { coordinate.column + index < upperBound }
            case Direction.UP_LEFT:
                return { (coordinate.row - index >= lowerBound) && (coordinate.column - index >= lowerBound) }
            case Direction.UP_RIGHT:
                return { (coordinate.row - index >= lowerBound) && (coordinate.column + index < upperBound) }
            case Direction.DOWN_LEFT:
                return { (coordinate.row + index < upperBound) && (coordinate.column - index >= lowerBound) }
            case Direction.DOWN_RIGHT:
                return { (coordinate.row + index < upperBound) && (coordinate.column + index < upperBound) }
            default:
                throw new IllegalArgumentException('Direction not yet implemented!')
        }
    }

}
