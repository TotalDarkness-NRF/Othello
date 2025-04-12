package net.totaldarkness.util;

import net.totaldarkness.othello.model.*;

/**
 * Implements the strategy design pattern for Player Random
 */
public class PlayerRandomStrategy implements PlayerStrategy{
    private final Player player;

    public PlayerRandomStrategy(Othello othello, char player) {
        this.player = new PlayerRandom(othello, player);
    }

    @Override
    public Move getMove() {
        return player.getMove();
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}