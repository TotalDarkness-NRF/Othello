package util;

import othello.model.Move;
import othello.model.Player;

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