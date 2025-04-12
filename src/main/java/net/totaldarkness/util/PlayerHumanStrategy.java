package net.totaldarkness.util;

import net.totaldarkness.othello.model.Move;
import net.totaldarkness.othello.model.Othello;
import net.totaldarkness.othello.model.Player;
import net.totaldarkness.othello.model.PlayerHuman;

/**
 * Implements the strategy design pattern for Player Human
 */
public class PlayerHumanStrategy implements PlayerStrategy {
    private final Player player;

    public PlayerHumanStrategy(Othello othello, char player) {
        this.player = new PlayerHuman(othello, player);
    }

    @Override
    public Move getMove() {
        return null;
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}