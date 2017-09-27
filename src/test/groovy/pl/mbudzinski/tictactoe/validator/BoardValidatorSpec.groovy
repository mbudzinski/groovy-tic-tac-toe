package pl.mbudzinski.tictactoe.validator

import pl.mbudzinski.tictactoe.coordinate.Coordinate
import spock.lang.Specification

class BoardValidatorSpec extends Specification {

    def 'Returns true when Coordinate is contained in board bounds' () {
        given:
            int rowWithinBoardBounds = 2
            int columnWithinBoardBounds = 0
            Coordinate coordinateWithinBoardBounds = [rowWithinBoardBounds, columnWithinBoardBounds]
            String[][] someBoard = new String[3][3]
        when:
            def isInBounds = boardValidator.isCoordinateInBoardBounds(someBoard, coordinateWithinBoardBounds)
        then:
            isInBounds
    }

    def 'Returns false when Coordinate is not contained in board bounds' () {
        given:
            Coordinate coordinateNotInBoardBounds = [rowNotInBoardBounds, columnNotInBoardBounds]
            String[][] someBoard = new String[3][3]
        when:
            def isInBounds = boardValidator.isCoordinateInBoardBounds(someBoard, coordinateNotInBoardBounds)
        then:
            !isInBounds
        where:
            rowNotInBoardBounds << [3, -1, 100]
            columnNotInBoardBounds << [3, -1, 100]
    }

    def 'Indicates that the cell pointed by the current coordinate is already occupied' () {
        given:
            Coordinate coordinatePointingToOccupiedCell = [occupiedRow, occupiedColumn]
            String[][] someBoard = new String[3][3]
            String cellContentWithSpaces = ' X '
            someBoard[occupiedRow][occupiedColumn] = cellContentWithSpaces
        when:
            def isCellOccupied = boardValidator.isCellAlreadyOccupied(someBoard, coordinatePointingToOccupiedCell)
        then:
            isCellOccupied
        where:
            occupiedRow << [0, 1]
            occupiedColumn << [0, 1]
    }

    def 'Indicates that the cell pointed by the current coordinate is not occupied' () {
        given:
            Coordinate coordinatePointingToFreeCell = [freeRow, freeColumn]
            String[][] someBoard = new String[3][3]
            String emptyCellContent = '  '
            someBoard[freeRow][freeColumn] = emptyCellContent
        when:
            def isCellOccupied = boardValidator.isCellAlreadyOccupied(someBoard, coordinatePointingToFreeCell)

        then:
            !isCellOccupied
        where:
            freeRow << [0, 2, 1, 1]
            freeColumn << [0, 2, 1, 0]
    }


    BoardValidator boardValidator = []

}
