package pl.mbudzinski.tictactoe.reader

import java.util.function.Predicate

import spock.lang.Specification

class ScannerReaderSpec extends Specification {


    def 'Scanner reader passes the value returned by scanner when loop condition is initially fulfilled' () {
        given:
            int valueToReturnByScanner = 5
            int initialValue = 2
            Predicate<Integer> initiallyFulfilledPredicate = { it -> it < 5}
            scanner.nextInt() >> valueToReturnByScanner
            ScannerReader scannerReader = [scanner]
        when:
            def result = scannerReader.assignValueFromStdInWhile(initiallyFulfilledPredicate, irrelevantPrompt, irrelevantErrorMessage, initialValue)
        then:
            result == valueToReturnByScanner
    }

    Scanner scanner = GroovyMock(Scanner)
    def irrelevantPrompt = 'Please enter a value'
    def irrelevantErrorMessage = 'Invalid value!'

}
