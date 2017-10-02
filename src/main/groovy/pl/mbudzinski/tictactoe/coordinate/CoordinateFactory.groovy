package pl.mbudzinski.tictactoe.coordinate

import pl.mbudzinski.tictactoe.reader.ScannerReader

class CoordinateFactory {

    private ScannerReader systemInReader

    static final String SIGN_PROMPT = 'Where do you want to put your sign? (row, column)'

    static final String EMPTY_PROMPT = ''

    private static final String INVALID_COORDINATES_ERROR = 'Please enter valid coordinates! (row, column)'

    CoordinateFactory(ScannerReader systemInReader) {
        this.systemInReader = systemInReader
    }

    Coordinate getCoordinateFromStdIn() {
        int row = -1
        int column = -1
        while (row < 0 || column < 0) {
            row = systemInReader.readInt(SIGN_PROMPT, INVALID_COORDINATES_ERROR, -1)
            column = systemInReader.readInt(EMPTY_PROMPT, INVALID_COORDINATES_ERROR, -1)
        }

        Coordinate coordinate = [row, column]
    }

}
