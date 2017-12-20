package pl.mbudzinski.tictactoe.evaluation

import pl.mbudzinski.tictactoe.player.Player
import pl.mbudzinski.tictactoe.coordinate.Coordinate
import pl.mbudzinski.tictactoe.direction.BiDirection
import pl.mbudzinski.tictactoe.direction.Direction
import pl.mbudzinski.tictactoe.direction.DirectionPredicateSupplier

import java.util.function.Predicate

class WinConditionEvaluator {

    private int winThreshold
    private int currentNumberOfTurns = 0
    private int maxNumberOfTurns
    private String[][] board
    private DirectionPredicateSupplier directionPredicateSupplier
    private boolean somePlayerWon = false

    WinConditionEvaluator(String[][] board, int winThreshold, DirectionPredicateSupplier directionPredicateSupplier) {
        if (winThreshold < 2) throw new IllegalArgumentException('Win threshold must be greater than 1')
        if (winThreshold > board.length) {
            throw new IllegalArgumentException('The board is too small!')
        }
        this.board = board
        this.winThreshold = winThreshold
        this.directionPredicateSupplier = directionPredicateSupplier
        this.maxNumberOfTurns = board.length * board[0].length
    }

    boolean isDraw() {
        if (!somePlayerWon){
            if (currentNumberOfTurns >= maxNumberOfTurns) {
                return true
            }
        }
        false
    }

    boolean hasPlayerWon(Player activePlayer) {
        ++currentNumberOfTurns
        board.eachWithIndex {row, int rowIndex ->
            row.eachWithIndex {column, int columnIndex ->
                if (activePlayer.sign == column) {
                    Coordinate coordinate = [rowIndex, columnIndex]
                    if (checkCellForWin(activePlayer, coordinate)) {
                        somePlayerWon = true
                    }
                }
            }
        }
        somePlayerWon
    }

    private boolean checkCellForWin(Player activePlayer, Coordinate coordinate) {
        int startingCounter = 1
        BiDirection upDown = [Direction.UP, Direction.DOWN]

        if (performBiDirectionalSearch(upDown, coordinate, activePlayer, startingCounter)) {
            return true
        }

        BiDirection leftRight = [Direction.LEFT, Direction.RIGHT]

        if (performBiDirectionalSearch(leftRight, coordinate, activePlayer, startingCounter)) {
            return true
        }

        BiDirection upLeftDownRight = [Direction.UP_LEFT, Direction.DOWN_RIGHT]

        if (performBiDirectionalSearch(upLeftDownRight, coordinate, activePlayer, startingCounter)) {
            return true
        }

        BiDirection upRightDownLeft = [Direction.UP_RIGHT, Direction.DOWN_LEFT]

        performBiDirectionalSearch(upRightDownLeft, coordinate, activePlayer, startingCounter)
    }

    private boolean performBiDirectionalSearch(BiDirection biDirection,
                                               Coordinate coordinateToSearchFrom,
                                               Player activePlayer,
                                               int counter) {

        counter = performSearchInDirection(biDirection.firstDirection, coordinateToSearchFrom, activePlayer, counter)
        if (counter >= winThreshold) {
            return true
        }
        counter = performSearchInDirection(biDirection.secondDirection, coordinateToSearchFrom, activePlayer, counter)

        counter >= winThreshold
    }

    private int performSearchInDirection(Direction direction,
                                         Coordinate coordinateToSearchFrom,
                                         Player activePlayer,
                                         int counter) {
        int counterCopy = counter
        for (index in 1..winThreshold) {
            if (directionPredicateSupplier.getBoundedPredicateForDirection(
                    direction,
                    coordinateToSearchFrom,
                    board.length
            ).test(index)) {

                Predicate<Integer> isSignEqual = directionPredicateSupplier.getEqualityPredicateForDirection(
                                direction,
                                board,
                                coordinateToSearchFrom,
                                activePlayer)

                if (isSignEqual.test(index)) {
                    ++counterCopy
                } else {
                    return counterCopy
                }
            }
        }
        counterCopy
    }

    int getMaxNumberOfTurns() {
        maxNumberOfTurns
    }

}
