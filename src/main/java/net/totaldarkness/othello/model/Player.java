package net.totaldarkness.othello.model;

import java.io.Serializable;

/**
 * A generic Player class to be used by OthelloController
 * in order to construct Othello games against various players
 * using different strategies.
 */
public abstract class Player implements Serializable {
	protected Othello othello;
	protected char player;

    /**
     * Create an unset player that is to be set later.
     */
    public Player() {
    }

    /**
     * Create a set player with othello and player information.
     * @param othello the othello game
     * @param player the player character
     */
    public Player(Othello othello, char player) {
        this.othello = othello;
        this.player = player;
    }

    /**
     * Set the othello board for the player.
     * @param othello the othello game
     */
    protected void setOthello(Othello othello) {
        this.othello = othello;
    }

    /**
     * Set the player character for the player.
     * @param player the player character
     */
    protected void setPlayer(char player) {
        this.player = player;
    }

    /**
     * Gets the players next move
     * @return players next move if it has one
     */
    abstract public Move getMove();
}
