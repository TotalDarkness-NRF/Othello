package util;

import othello.model.Othello;

import java.io.Serializable;
import java.util.Stack;

/**
 * An Othello move command manager that keeps track of OthelloMoveCommands
 * execute commands and to keep track of order of move commands and undo or redo if necessary.
 */
public class OthelloMoveCommandManager implements Serializable {
    private Othello othello;
    private final Stack<OthelloMoveCommand> undoStack = new Stack<>();
    private final Stack<OthelloMoveCommand> redoStack = new Stack<>();

    public OthelloMoveCommandManager(Othello othello) {
        this.othello = othello;
    }

    public void executeCommand(OthelloMoveCommand command) {
        command.execute();
        undoStack.push(command);
        setOthello(command.othello.copy());
        redoStack.clear();
    }

    public void undo() {
        if (undoStack.isEmpty()) return;
        OthelloMoveCommand command = undoStack.pop();
        command.undo();
        setOthello(command.othello.copy());
        redoStack.push(command);
    }

    public void redo() {
        if (redoStack.isEmpty()) return;
        OthelloMoveCommand command = redoStack.pop();
        command.execute();
        setOthello(command.othello.copy());
        undoStack.push(command);
    }

    public Othello getOthello() {
        return othello;
    }

    public void setOthello(Othello othello) {
        this.othello = othello;
    }
}