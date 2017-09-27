package pl.mbudzinski.tictactoe

import pl.mbudzinski.tictactoe.board.BoardDrawer
import pl.mbudzinski.tictactoe.board.QuadraticBoardFactory
import pl.mbudzinski.tictactoe.coordinate.Coordinate
import pl.mbudzinski.tictactoe.coordinate.CoordinateFactory
import pl.mbudzinski.tictactoe.direction.DirectionPredicateSupplier
import pl.mbudzinski.tictactoe.evaluation.WinConditionEvaluator
import pl.mbudzinski.tictactoe.player.Player
import pl.mbudzinski.tictactoe.reader.ScannerReader
import pl.mbudzinski.tictactoe.validator.BoardValidator

class Main {

    private static QuadraticBoardFactory boardFactory = []
    private static BoardDrawer boardDrawer = []
    private static BoardValidator boardValidator = []
    private static DirectionPredicateSupplier directionPredicateSupplier = []
    private static ScannerReader scannerReader = [new Scanner(System.in)]
    private static CoordinateFactory coordinateFactory = [scannerReader]
    private static WinConditionEvaluator winConditionEvaluator

    static void main(String[] args) {

        int boardSize = getBoardSizeFromStdIn()
        String[][] board = boardFactory.getEmptyBoard(boardSize)

        int winThreshold = getWinThresholdFromStdIn()
        winConditionEvaluator = [board, winThreshold, directionPredicateSupplier]

        Player activePlayer = Player.CIRCLE
        boolean gameEnded = false

        while (!gameEnded) {
            drawBoard(board)
            println "It's ${activePlayer.name()} turn."
            Coordinate coordinate = getCoordinateFromStdIn(board)

            boolean isCellTaken = boardValidator.isCellAlreadyOccupied(board, coordinate)
            while (isCellTaken) {
                isCellTaken = handleAlreadyOccupiedCell(board, coordinate)
            }
            updateBoard(board, coordinate, activePlayer)
            gameEnded = winConditionEvaluator.hasPlayerWon(activePlayer)
            if (winConditionEvaluator.isDraw()) {
                break
            }
            activePlayer = getOppositePlayer(activePlayer)
        }
        drawBoard(board)
        if (winConditionEvaluator.isDraw()) {
            println("Draw!")
        } else {
            activePlayer = getOppositePlayer(activePlayer)
            println("\n${activePlayer.sign} won!")
        }
        scannerReader.close()
    }

    private static void drawBoard(String[][] board) {
        boardDrawer.printBoard(board)
    }

    private static int getBoardSizeFromStdIn() {
        int boardSize = 0
        while (boardSize < 3 || boardSize > 10) {
            boardSize = scannerReader.readInt(
                    'Please enter the board size (3-10)',
                    'Invalid board size!', 0
            )
        }
        boardSize
    }

    private static int getWinThresholdFromStdIn() {
        int winThreshold = 0
        while (winThreshold < 3 || winThreshold > 10) {
            winThreshold = scannerReader.readInt(
                    'Please enter the win threshold (3-10)',
                    'Invalid win threshold size!', 0
            )
        }
        winThreshold
    }

    private static Coordinate getCoordinateFromStdIn(String[][] board){
        Coordinate coordinate = coordinateFactory.getCoordinateFromStdIn()
        while (!boardValidator.isCoordinateInBoardBounds(board, coordinate)) {
            coordinate = coordinateFactory.getCoordinateFromStdIn()
        }
        coordinate
    }

    private static void updateBoard(String[][] board, Coordinate coordinate, Player player) {
        board[coordinate?.row][coordinate?.column] = player.sign
    }

    private static Player getOppositePlayer(Player player) {
        if (player == Player.CIRCLE) {
            Player.CROSS
        } else {
            Player.CIRCLE
        }
    }

    private static boolean handleAlreadyOccupiedCell(String[][] board, Coordinate coordinate) {
        drawBoard(board)
        println "This cell is already taken. Enter the row and column once more"
        coordinate = getCoordinateFromStdIn()
        boardValidator.isCellAlreadyOccupied(board, coordinate)
    }

}
