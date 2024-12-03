package util;

import othello.model.Move;
import othello.model.Othello;
import othello.model.Player;
import othello.model.PlayerHuman;

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