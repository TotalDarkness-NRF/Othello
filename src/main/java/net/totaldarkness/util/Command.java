package net.totaldarkness.util;

/**
 * A Command interface representing a command that can be executed and undone.
 */
public interface Command {
    void execute();
    void undo();
}