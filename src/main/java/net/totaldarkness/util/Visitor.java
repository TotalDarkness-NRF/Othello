package net.totaldarkness.util;

import net.totaldarkness.othello.model.OthelloBoard;

/**
 * A Visitor interface representing a visitor for the visitor
 * design pattern.
 */
public interface Visitor {
    void visit(OthelloBoard othelloBoard);
}