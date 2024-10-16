package ca.yorku.eecs3311.a1;

import java.util.ArrayList;
import java.util.List;
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
	 * Create an unset Random Player to be set later.
	 */
	public PlayerRandom() {
	}

	/**
	 * Create a Random Player with othello and player information.
	 * @param othello the othello game
	 * @param player the player character
	 */
	public PlayerRandom(Othello othello, char player) {
		super(othello, player);
	}

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
		List<Move> moves = getMoves();
		if (moves.isEmpty()) return null;
		return moves.get(rand.nextInt(moves.size()));
	}

	/**
	 * Gets a list of possible moves that the player can make.
	 *
	 * @return list of possible moves.
	 */
	public List<Move> getMoves() {
		List<Move> moves = new ArrayList<>();
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

