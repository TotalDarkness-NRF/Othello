package util;

import othello.model.OthelloBoard;

public interface Visitor {
    void visit(OthelloBoard othelloBoard);
}
