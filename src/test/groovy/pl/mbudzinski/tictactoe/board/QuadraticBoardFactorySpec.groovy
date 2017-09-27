package pl.mbudzinski.tictactoe.board

import spock.lang.Specification

class QuadraticBoardFactorySpec extends Specification {

    def 'Returns a quadratic array of given size which represents the game board' () {
        when:
            def board = squareBoardFactory.getEmptyBoard(boardSize)
        then:
            board.size() == boardSize
            board.get(0).size() == boardSize
        where:
            boardSize << [3,5,9,7]
    }

    def 'Throws IllegalArgumentException when the passed board size is too small (smaller than 3)' () {
        when:
            squareBoardFactory.getEmptyBoard(2)
        then:
            thrown(IllegalArgumentException)
    }

    def 'Throws IllegalArgumentException when the passed board size is too large (larger than 10)' () {
        when:
            squareBoardFactory.getEmptyBoard(11)
        then:
            thrown(IllegalArgumentException)
    }

    QuadraticBoardFactory squareBoardFactory = []

}
