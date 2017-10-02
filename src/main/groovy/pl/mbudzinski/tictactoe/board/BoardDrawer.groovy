package pl.mbudzinski.tictactoe.board


class BoardDrawer {

    private static final String WHITESPACE = ' '
    private static final String ROW_SEPARATOR = '--'

    void printBoard(board) {
        printHeader(board)
        printRows(board)
    }

    private void printHeader(board) {
        board.eachWithIndex { row, index ->
            print(index + WHITESPACE)
        }
        println WHITESPACE
    }

    private void printRows(board) {
        board.eachWithIndex { row, index ->
            printRow(board, row, index)
        }
    }

    private void printRow(board, row, index) {
        print row.join('|')
        print(WHITESPACE + index)
        println WHITESPACE
        if (index < board.size() - 1) {
            row.each({ column -> print(ROW_SEPARATOR) })
            println WHITESPACE
        }
    }
}
