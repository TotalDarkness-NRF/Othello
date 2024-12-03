package util;

import othello.model.Move;
import othello.model.Player;
import othello.model.PlayerRandom;

public class PlayerRandomStrategy implements PlayerStrategy{
    private final Player player;

    public PlayerRandomStrategy(PlayerRandom player) {
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