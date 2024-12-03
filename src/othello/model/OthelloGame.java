package othello.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class OthelloGame implements Serializable {
    private final Othello othello;
    private final Player player1, player2;
    private final List<Othello> gameHistory = new ArrayList<>();

    public OthelloGame(Othello othello, Player player1, Player player2) {
        this.othello = othello;
        this.player1 = player1;
        this.player2 = player2;
        gameHistory.add(othello.copy());
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

    public void move(int row, int col) {
        if (othello.copy().move(row, col)) {
            gameHistory.add(othello.copy());
            othello.move(row, col);
        }
    }
}