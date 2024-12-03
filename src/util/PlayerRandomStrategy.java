package util;

import othello.model.*;

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