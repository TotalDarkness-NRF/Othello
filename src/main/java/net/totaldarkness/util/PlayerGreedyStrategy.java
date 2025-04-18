package net.totaldarkness.util;

import net.totaldarkness.othello.model.Move;
import net.totaldarkness.othello.model.Othello;
import net.totaldarkness.othello.model.Player;
import net.totaldarkness.othello.model.PlayerGreedy;

/**
 * Implements the strategy design pattern for Player Greedy
 */
public class PlayerGreedyStrategy implements PlayerStrategy {
    private final Player player;

    public PlayerGreedyStrategy(Othello othello, char player) {
        this.player = new PlayerGreedy(othello, player);
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