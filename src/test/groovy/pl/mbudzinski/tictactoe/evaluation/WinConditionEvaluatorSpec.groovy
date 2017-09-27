package pl.mbudzinski.tictactoe.evaluation

import pl.mbudzinski.tictactoe.direction.DirectionPredicateSupplier
import pl.mbudzinski.tictactoe.player.Player
import spock.lang.Specification

class WinConditionEvaluatorSpec extends Specification {


    def 'Maximum number of turns is equal to quadratic board capacity' () {
        when:
            def maxNumberOfTurns = winConditionEvaluator.maxNumberOfTurns
        then:
            maxNumberOfTurns == someBoard.length * someBoard[0].length
    }

    def 'When current turn number is equal to maximum turn number and no one has won, conclude a draw' () {
        given:
            int maxNumberOfTurnsForCurrentBoard = winConditionEvaluator.maxNumberOfTurns
            Player irrelevantPlayer = Player.CROSS
        when:
            for (int i in 1..maxNumberOfTurnsForCurrentBoard - 1) {
                winConditionEvaluator.hasPlayerWon(irrelevantPlayer)
            }
            def isDraw = winConditionEvaluator.isDraw()
        then:
            isDraw
    }

    int winThreshold = 3
    String[][] someBoard = new String[3][3]

    DirectionPredicateSupplier directionPredicateSupplier = Mock(DirectionPredicateSupplier)
    WinConditionEvaluator winConditionEvaluator = [someBoard, winThreshold, directionPredicateSupplier]

}
