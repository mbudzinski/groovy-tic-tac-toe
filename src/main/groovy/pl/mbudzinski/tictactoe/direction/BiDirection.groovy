package pl.mbudzinski.tictactoe.direction

class BiDirection {

    Direction firstDirection

    Direction secondDirection

    BiDirection(Direction firstDirection, Direction secondDirection) {
        if (firstDirection == secondDirection) {
            throw new IllegalArgumentException("Directions cannot be equal/the same!")
        }
        this.firstDirection = firstDirection
        this.secondDirection = secondDirection
    }

}
