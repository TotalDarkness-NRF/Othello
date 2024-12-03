package util;

import othello.model.Othello;

import java.io.Serializable;
import java.util.Stack;

public class OthelloMoveCommandManager implements Serializable {
    private Othello othello;
    private final Stack<OthelloMoveCommand> undoStack = new Stack<>();
    private final Stack<OthelloMoveCommand> redoStack = new Stack<>();

    public void executeCommand(OthelloMoveCommand command) {
        command.execute();
        undoStack.push(command);
        othello = command.othello.copy();
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.isEmpty()) return;
        OthelloMoveCommand command = undoStack.pop();
        command.undo();
        othello = command.othello.copy();
        redoStack.push(command);
    }

    public void redo() {
        if (redoStack.isEmpty()) return;
        OthelloMoveCommand command = redoStack.pop();
        command.execute();
        othello = command.othello.copy();
        undoStack.push(command);
    }

    public Othello getOthello() {
        return othello;
    }
}