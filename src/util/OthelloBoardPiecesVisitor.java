package util;

import othello.model.Move;
import othello.model.OthelloBoard;

import java.util.HashMap;

/**
 * Implements the visitor design pattern for OthelloBoards to
 * get the pieces on the othello board.
 */
public class OthelloBoardPiecesVisitor implements Visitor {
    private final HashMap<Move, Character> pieces = new HashMap<>();

    @Override
    public void visit(OthelloBoard othelloBoard) {
        for (int row = 0; row < othelloBoard.getDimension(); row++) {
            for (int col = 0; col < othelloBoard.getDimension(); col++) {
                char piece = othelloBoard.get(row, col);
                if (piece == OthelloBoard.EMPTY) continue;
                pieces.put(new Move(row, col), piece);
            }
        }
    }

    public HashMap<Move, Character> getPieces() {
        return pieces;
    }
}