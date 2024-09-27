package othello.model;

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

	/**
	 * Gets a random move from a list of possible moves the player can make.
	 *
	 * @return a random move from any possible moves the player can make.
	 */
	public Move getMove() {
		if (othello.getWhosTurn() != player) return null;
		char hasMove = othello.getBoard().hasMove();
		if (hasMove == OthelloBoard.EMPTY || hasMove == OthelloBoard.otherPlayer(player)) {
			return null;
		}
		ArrayList<Move> moves = getMoves();
		if (moves.isEmpty()) return null;
		return moves.get(rand.nextInt(moves.size()));
	}

	/**
	 * Gets a list of possible moves that the player can make.
	 *
	 * @return list of possible moves.
	 */
	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<>();
		for (int row = 0; row < othello.getBoard().getDimension(); row++) {
			for (int col = 0; col < othello.getBoard().getDimension(); col++) {
				char hasMove = othello.getBoard().hasMove(row, col);
				if (hasMove == OthelloBoard.BOTH || hasMove == player) {
					moves.add(new Move(row, col));
				}
			}
		}
		return moves;
	}
}

