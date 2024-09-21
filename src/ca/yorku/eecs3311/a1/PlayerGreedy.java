package ca.yorku.eecs3311.a1;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 * @author ilir
 *
 */

public class PlayerGreedy {

	private Othello othello;
	private char player;

	public PlayerGreedy(Othello othello, char player) {
		this.othello = othello;
		this.player = player;
	}

	public Move getMove() {
		// TODO do we need to check if its my turn?
		if (othello.getWhosTurn() != player) {
			return null;
		}
		char hasMove = othello.getBoard().hasMove();
		// TODO acceptable to send null?
		if (hasMove == OthelloBoard.EMPTY || hasMove == OthelloBoard.otherPlayer(player)) {
			return null;
		}
		int maxPieces = 0;
		int count;
		Move move = null;
		for (int row = 0; row < othello.getBoard().getDimension(); row++) {
			for (int col = 0; col < othello.getBoard().getDimension(); col++) {
				hasMove = othello.getBoard().hasMove(row, col);
				if (hasMove == player || hasMove == OthelloBoard.BOTH) {
					count = getMoveCounts(row, col);
					if (count >= maxPieces) {
						if (move != null && count == maxPieces) {
							if (row > move.getRow()) continue;
							else if (row == move.getRow()) {
								if (col > move.getCol()) continue;
							}
							// TODO code is so ugly need to refactor later
						}
						maxPieces = count;
						move = new Move(row, col);
					}
				}
			}
		}
		return maxPieces == 0 ? null : move;
	}

	private int getMoveCounts(int row, int col, int drow, int dcol) {
		if (drow < -1 || drow > 1) return 0;
		if (dcol < -1 || dcol > 1) return 0;
		if (drow == 0 && dcol == 0) return 0;
		int count = 0;
		char piece;
		do {
			row += drow;
			col += dcol;
			piece = othello.getBoard().get(row, col);
			if (piece == player) {
				return count;
			} else if (piece == OthelloBoard.EMPTY) {
				return 0;
			}
			count++;
		} while (othello.getBoard().validCoordinate(row + drow, col + dcol));
		return 0;
	}

	private int getMoveCounts(int row, int col) {
		int count = 0;
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				count += getMoveCounts(row, col, drow, dcol);
			}
		}
		return count;
	}
}

