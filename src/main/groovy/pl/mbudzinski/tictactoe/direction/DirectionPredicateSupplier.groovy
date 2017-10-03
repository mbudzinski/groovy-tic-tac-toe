package pl.mbudzinski.tictactoe.direction

import pl.mbudzinski.tictactoe.player.Player
import pl.mbudzinski.tictactoe.coordinate.Coordinate

import java.util.function.Predicate

class DirectionPredicateSupplier {

    Predicate<Integer> getEqualityPredicateForDirection(Direction direction,
                                                          String[][] board,
                                                          Coordinate coordinate,
                                                          Player activePlayer) {
        switch (direction){
            case Direction.UP:
                return { board[coordinate.row - it][coordinate.column] == activePlayer.sign }
            case Direction.DOWN:
                return { board[coordinate.row + it][coordinate.column] == activePlayer.sign }
            case Direction.LEFT:
                return { board[coordinate.row][coordinate.column - it] == activePlayer.sign }
            case Direction.RIGHT:
                return { board[coordinate.row][coordinate.column + it] == activePlayer.sign }
            case Direction.UP_LEFT:
                return { board[coordinate.row - it][coordinate.column - it] == activePlayer.sign }
            case Direction.UP_RIGHT:
                return { board[coordinate.row - it][coordinate.column + it] == activePlayer.sign }
            case Direction.DOWN_LEFT:
                return { board[coordinate.row + it][coordinate.column - it] == activePlayer.sign }
            case Direction.DOWN_RIGHT:
                return { board[coordinate.row + it][coordinate.column + it] == activePlayer.sign }
            default:
                throw new IllegalArgumentException('Direction not yet implemented!')
        }
    }

    Predicate<Integer> getBoundedPredicateForDirection(Direction direction,
                                                          Coordinate coordinate,
                                                          int upperBound) {

        int lowerBound = 0
        switch (direction) {
            case Direction.UP:
                return { coordinate.row - it >= lowerBound }
            case Direction.DOWN:
                return { coordinate.row + it < upperBound }
            case Direction.LEFT:
                return { coordinate.column - it >= lowerBound }
            case Direction.RIGHT:
                return { coordinate.column + it < upperBound }
            case Direction.UP_LEFT:
                return { (coordinate.row - it >= lowerBound) && (coordinate.column - it >= lowerBound) }
            case Direction.UP_RIGHT:
                return { (coordinate.row - it >= lowerBound) && (coordinate.column + it < upperBound) }
            case Direction.DOWN_LEFT:
                return { (coordinate.row + it < upperBound) && (coordinate.column - it >= lowerBound) }
            case Direction.DOWN_RIGHT:
                return { (coordinate.row + it < upperBound) && (coordinate.column + it < upperBound) }
            default:
                throw new IllegalArgumentException('Direction not yet implemented!')
        }
    }

}
