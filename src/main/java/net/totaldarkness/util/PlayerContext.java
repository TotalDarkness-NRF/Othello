package net.totaldarkness.util;

import net.totaldarkness.othello.model.Move;
import net.totaldarkness.othello.model.Player;

/**
 * A Player Context for the strategy design pattern to
 * keep the context of what Player Strategy is being used.
 */
public class PlayerContext {
    private PlayerStrategy strategy;

    // Set the default strategy (can be changed at runtime)
    public PlayerContext(PlayerStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PlayerStrategy strategy) {
        this.strategy = strategy;
    }

    public Move getMove() {
        return strategy.getMove();
    }

    public Player getPlayer() {
        return strategy.getPlayer();
    }
}