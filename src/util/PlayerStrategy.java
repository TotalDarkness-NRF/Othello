package util;

import othello.model.Move;
import othello.model.Player;

public interface PlayerStrategy {
    Move getMove();
    Player getPlayer();
}
