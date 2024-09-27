package ca.yorku.eecs3311.a1;

/**
 * A generic Player class to be used by OthelloController
 * in order to construct Othello games against various players
 * using different strategies.
 */
public abstract class Player {
    protected Othello othello;
    protected char player;

    protected void setOthello(Othello othello) {
        this.othello = othello;
    }

    protected void setPlayer(char player) {
        this.player = player;
    }

    /**
     * Gets the players next move
     * @return players next move if it has one
     */
    abstract public Move getMove();
}
