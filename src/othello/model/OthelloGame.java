package othello.model;

import java.io.Serializable;

public final class OthelloGame implements Serializable {
    private final Othello othello;
    private final Player player1, player2;

    public OthelloGame(Othello othello, Player player1, Player player2) {
        this.othello = othello;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Othello getOthello() {
        return othello;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}