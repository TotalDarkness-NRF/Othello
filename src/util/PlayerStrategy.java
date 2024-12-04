package util;

import othello.model.Move;
import othello.model.Player;

/**
 * A PlayerStrategy interface representing a Strategy for a player to
 * get a players next move and the type of player.
 */
public interface PlayerStrategy {
    Move getMove();
    Player getPlayer();
}