package util;

import othello.model.Move;
import othello.model.Othello;

import java.io.Serializable;

public class OthelloMoveCommand implements Command, Serializable {
    final Move move;
    Othello othello;
    Othello oldOthello;

    public OthelloMoveCommand(Move move, Othello othello) {
        this.move = move;
        this.othello = othello;
    }

    @Override
    public void execute() {
        if (move == null) return;
        oldOthello = othello.copy();
        othello.move(move.getRow(), move.getCol());
    }

    @Override
    public void undo() {
        if (oldOthello == null) return;
        othello = oldOthello;
    }
}