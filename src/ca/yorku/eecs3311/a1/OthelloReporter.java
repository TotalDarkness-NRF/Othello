package ca.yorku.eecs3311.a1;

/**
 * A class to be used to report events of an Othello game.
 * Uses a standard format for each so that different games
 * have a constant and reliable report of the game.
 */
public final class OthelloReporter {

    /**
     * Reports whose turn it is and what move they made.
     * @param othello the othello game to report.
     * @param move the move that they made.
     */
    public static void reportMove(Othello othello, Move move) {
        String report = othello.getWhosTurn() + " makes move " + move + "\n";
        System.out.println(report);
    }

    /**
     * Reports the current board, the number of pieces on the board
     * for each player, and who moves next.
     * @param othello the othello game to report.
     */
    public static void report(Othello othello) {
        String report = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  "
                + othello.getWhosTurn() + " moves next";
        System.out.println(report);
    }

    /**
     * Reports the current board, the number of pieces on the board
     * for each player, and who won the game.
     * @param othello the othello game to report.
     */
    public static void reportFinal(Othello othello) {
        String report = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2)
                + "  " + othello.getWinner() + " won\n";
        System.out.println(report);
    }
}
