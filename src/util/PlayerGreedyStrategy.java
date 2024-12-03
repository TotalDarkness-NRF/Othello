package util;

import othello.model.Move;
import othello.model.Player;
import othello.model.PlayerGreedy;

public class PlayerGreedyStrategy implements PlayerStrategy {
    private final Player player;

    public PlayerGreedyStrategy(PlayerGreedy player) {
        this.player = player;
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