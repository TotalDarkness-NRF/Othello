package net.totaldarkness.othello.model;

/**
 * An OthelloController class that provides basic
 * functionality to play an Othello game against various
 * player strategies.
 */
public class OthelloController {
    protected final Othello othello;
    protected final Player player1, player2;

    /**
     * Constructs an OthelloController using the provided Othello
     * game and the provided players, who may use different strategies.
     * @param othello the Othello game.
     * @param player1 the first player (Black or X)
     * @param player2 the second player (White or O)
     */
    public OthelloController(Othello othello, Player player1, Player player2) {
        this.othello = othello;
        player1.setOthello(this.othello);
        player1.setPlayer(OthelloBoard.P1);
        player2.setOthello(this.othello);
        player2.setPlayer(OthelloBoard.P2);
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Play the Othello game. Report the game if required.
     * @param report if the game should be reported
     */
    public final void play(boolean report) {
        while (!othello.isGameOver()) {
            if (report) OthelloReporter.report(othello);
            Move move = getNextMove();
            if (report) OthelloReporter.reportMove(othello, move);
            othello.move(move.getRow(), move.getCol());
        }
        if (report) OthelloReporter.reportFinal(othello);
    }

    /**
     * Play the othello game. Reports the game by default.
     */
    public void play() {
        play(true);
    }

    /**
     *
     * @return the next players move
     */
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
