package pl.mbudzinski.tictactoe.reader

class ScannerReader {

    private Scanner scanner

    ScannerReader(Scanner scanner) {
        this.scanner = scanner
    }

    /** This method should be (in order to handle InputMismatchException when using System.in) used in a while loop.
     *  In case of InputMismatchException the errorPrompt is displayed and fallback value is returned
     *  @return An int via the scanner's input stream. In case of InputMismatchException fallback value is returned.
    * */
    int readInt(String prompt,
                String errorPrompt,
                int valueToReturnInCaseOfException) {

        System.out.println(prompt)
        try {
            return scanner.nextInt()
        } catch (InputMismatchException ex) {
            System.out.println(errorPrompt)
            scanner.nextLine()
            // return the fallback value to make the compiler happy
            valueToReturnInCaseOfException
        }
    }

    void close() {
        scanner.close()
    }

}
