package pl.mbudzinski.tictactoe.reader

import java.util.function.Predicate

class ScannerReader {

    private Scanner scanner

    ScannerReader(Scanner scanner) {
        this.scanner = scanner
    }

    int assignValueFromStdInWhile(Predicate<Integer> condition,
                                  String prompt,
                                  String errorPrompt,
                                  int initialValue) {

        while (condition.test(initialValue)) {
            try {
                println(prompt)
                initialValue = scanner.nextInt()
                if (condition.test(initialValue)) {
                    println(errorPrompt)
                } else {
                    break
                }
            } catch (InputMismatchException ex) {
                println(errorPrompt)
                scanner.nextLine()
            }
        }
        initialValue
    }

    void close() {
        scanner.close()
    }

}
