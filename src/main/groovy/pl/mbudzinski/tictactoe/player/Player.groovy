package pl.mbudzinski.tictactoe.player

enum Player {

    CIRCLE('O','Circle'), CROSS('X', 'Cross')

    String sign

    String name

    Player(String sign, String name) {
        this.sign = sign
        this.name = name
    }

}