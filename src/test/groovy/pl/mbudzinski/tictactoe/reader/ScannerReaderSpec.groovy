package pl.mbudzinski.tictactoe.reader

import spock.lang.Specification

class ScannerReaderSpec extends Specification {


    def 'Scanner reader passes the value returned by scanner' () {
        given:
            String scannerData = '4'
            InputStream inputStream = new ByteArrayInputStream(scannerData.getBytes())
            System.setIn(inputStream)
            Scanner mockedScanner = [System.in]
            ScannerReader scannerReader = [mockedScanner]
            int fallbackValueToReturn = 2
        when:
            def result = scannerReader.readInt(irrelevantPrompt, irrelevantErrorMessage, fallbackValueToReturn)
        then:
            String.valueOf(result) == scannerData
            String.valueOf(fallbackValueToReturn) != scannerData
    }

    def 'In case of InputMismatchException, the fallback value is returned' () {
        given:
            String scannerDataWithMismatchedInput = 'someText'
            InputStream irrelevantInputStream = new ByteArrayInputStream(scannerDataWithMismatchedInput.getBytes())
            System.setIn(irrelevantInputStream)
            Scanner mockedScanner = [System.in]
            ScannerReader scannerReader = [mockedScanner]
            int fallbackValueToReturn = 1
        when:
            def result = scannerReader.readInt(irrelevantPrompt, irrelevantErrorMessage, fallbackValueToReturn)
        then:
            result == fallbackValueToReturn
        cleanup:
            System.setIn(stdin)
            scannerReader.close()
    }

    InputStream stdin = System.in
    def irrelevantPrompt = 'Please enter a value'
    def irrelevantErrorMessage = 'Please enter a valid value'

}
