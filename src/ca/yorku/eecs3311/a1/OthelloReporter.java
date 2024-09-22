package ca.yorku.eecs3311.a1;

/**
 * A class to be used to report events of an Othello game.
 * Uses a standard format for each so that different games
 * have a constant and reliable report of the game.
 */
public class OthelloReporter {
    private final Othello othello;

    /**
     * Constructs an OthelloReporter using provided othello game
     * @param othello the Othello game that will be reported
     */
    public OthelloReporter(Othello othello) {
        this.othello = othello;
    }

    /**
     * Reports whos turn it is and what move they made.
     * @param whosTurn which players turn it is.
     * @param move the move that they made.
     */
    public void reportMove(char whosTurn, Move move) {
        System.out.println(whosTurn + " makes move " + move + "\n");
    }

    /**
     * Reports the current board, the number of pieces on the board
     * for each player, and who moves next.
     */
    public void report() {
        String s = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  "
                + othello.getWhosTurn() + " moves next";
        System.out.println(s);
    }

    /**
     * Reports the current board, the number of pieces on the board
     * for each player, and who won the game.
     */
    public void reportFinal() {
        String s = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2)
                + "  " + othello.getWinner() + " won\n";
        System.out.println(s);
    }
}
