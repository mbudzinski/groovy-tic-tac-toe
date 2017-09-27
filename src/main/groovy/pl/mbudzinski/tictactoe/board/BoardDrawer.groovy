package pl.mbudzinski.tictactoe.board


class BoardDrawer {

    void printBoard(board) {
        board.eachWithIndex { row, index ->
            print(index + ' ')
        }
        println ' '
        board.eachWithIndex { row, index ->
            print row.join('|')
            print(' ' + index)
            println ' '
            if (index < board.size() - 1) {
                row.each({ column -> print('--') })
                println ' '
            }
        }
    }


}
