package ca.yorku.eecs3311.a1;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 * 
 * @author ilir
 *
 */
public class PlayerRandom extends Player {
	private final Random rand = new Random();

	public PlayerRandom(char player) {
		super(player);
	}

	public Move getMove() {
		if (othello.getWhosTurn() != player) {
			return null;
		}
		char hasMove = othello.getBoard().hasMove();
		// TODO acceptable to send null?
		if (hasMove == OthelloBoard.EMPTY || hasMove == OthelloBoard.otherPlayer(player)) {
			return null;
		}
		ArrayList<Move> moves = new ArrayList<>();
		for (int row = 0; row < othello.getBoard().getDimension(); row++) {
			for (int col = 0; col < othello.getBoard().getDimension(); col++) {
				hasMove = othello.getBoard().hasMove(row, col);
				if (hasMove == OthelloBoard.BOTH || hasMove == player) {
					moves.add(new Move(row, col));
				}
			}
		}
		if (moves.isEmpty()) return null;
		return moves.get(rand.nextInt(moves.size()));
	}
}

