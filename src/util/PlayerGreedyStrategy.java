package util;

import othello.model.Move;
import othello.model.Othello;
import othello.model.Player;
import othello.model.PlayerGreedy;

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