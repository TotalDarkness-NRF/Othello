package util;

import othello.model.OthelloBoard;

/**
 * Implements the visitor design pattern for OthelloBoards to
 * get the number of pieces for each player on the board.
 */
public class OthelloBoardCountVisitor implements Visitor {
    int player1Count, player2Count;
    @Override
    public void visit(OthelloBoard othelloBoard) {
        player1Count = othelloBoard.getCount(OthelloBoard.P1);
        player2Count = othelloBoard.getCount(OthelloBoard.P2);
    }

    public int getPlayer1Count() {
        return player1Count;
    }

    public int getPlayer2Count() {
        return player2Count;
    }
}