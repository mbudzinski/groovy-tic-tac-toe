package pl.mbudzinski.tictactoe.board

class QuadraticBoardFactory {

    def getEmptyBoard(int boardSize) {
        if (boardSize < 3) {
            throw new IllegalArgumentException("Board size cannot be less than 3!")
        }
        if (boardSize > 10) {
            throw new IllegalArgumentException("Board size cannot be larger than 10!")
        }
        def board = [[]]
        board.remove(0)
        def boardRange = 1..(boardSize)

        boardRange.each { it -> board.add(createEmptyRow(boardSize))}

        board
    }

    private createEmptyRow(int boardSize) {
        def row = []
        def rowRange = 1..(boardSize)

        rowRange.each { it -> row.add(' ') }

        row
    }
}
