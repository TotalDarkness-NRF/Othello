package ca.yorku.eecs3311.a1;

public abstract class OthelloController {
    protected final Othello othello;
    protected final Player player1, player2;

    public OthelloController(Othello othello, Player player1, Player player2) {
        this.othello = othello;
        player1.setOthello(this.othello);
        player2.setOthello(this.othello);
        this.player1 = player1;
        this.player2 = player2;
    }

    public abstract void play();

    public Move getNextMove() {
        Move move = null;
        char whosTurn = othello.getWhosTurn();
        if (whosTurn == OthelloBoard.P1)
            move = player1.getMove();
        if (whosTurn == OthelloBoard.P2)
            move = player2.getMove();
        return move;
    }
}
