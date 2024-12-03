package util;

import othello.model.Move;
import othello.model.Player;

public class PlayerHumanStrategy implements PlayerStrategy {
    private final Player player;

    public PlayerHumanStrategy(Player player) {
        this.player = player;
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