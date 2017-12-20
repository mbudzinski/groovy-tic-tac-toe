package pl.mbudzinski.tictactoe.coordinate

import pl.mbudzinski.tictactoe.reader.ScannerReader

class CoordinateFactory {

    private ScannerReader systemInReader

    static final String ROW_PROMPT = 'Where do you want to put your sign? (row)'
    static final String COLUMN_PROMPT = 'Where do you want to put your sign? (column)'
    private static final String INVALID_ROW_ERROR = 'Please enter a valid row number!'
    private static final String INVALID_COLUMN_ERROR = 'Please enter a valid column number!'

    CoordinateFactory(ScannerReader systemInReader) {
        this.systemInReader = systemInReader
    }

    Coordinate getCoordinateFromStdIn() {
        int row = -1
        row = systemInReader.assignValueFromStdInWhile({ it < 0 }, ROW_PROMPT, INVALID_ROW_ERROR, row)

        int column = -1
        column = systemInReader.assignValueFromStdInWhile({ it < 0 }, COLUMN_PROMPT, INVALID_COLUMN_ERROR, column)

        Coordinate coordinate = [row, column]
    }
}
