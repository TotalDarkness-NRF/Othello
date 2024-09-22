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
public class PlayerGreedy extends Player {

	/**
	 * Gets a move by considering all possible moves that the player.
	 * Picks the moves that will increase the number tokens the most.
	 * If two moves have the same max, the move with the smallest row is chosen.
	 * If two moves also have the same row, the move with the smallest col is chosen.
	 *
	 * @return the move that will increase the number of tokens the most.
	 */
	public Move getMove() {
		if (this.othello.getWhosTurn() != player) return null;
		char hasMove = othello.getBoard().hasMove();
		if (hasMove == OthelloBoard.EMPTY || hasMove == OthelloBoard.otherPlayer(player)) {
			return null;
		}
		int count, maxPieces = 0;
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
						}
						maxPieces = count;
						move = new Move(row, col);
					}
				}
			}
		}
		return maxPieces == 0 ? null : move;
	}

	/**
	 * Gets the count of tokens that will be added to the player
	 * if the player makes a specified move in row, col and in direction
	 * drow, dcol.
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return the number of tokens that will be added as a result of this move.
	 */
	private int getMoveCounts(int row, int col, int drow, int dcol) {
		if (!othello.getBoard().validDirection(drow, dcol)) return 0;
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

	/**
	 * Gets the max number of tokens that will be added from this move
	 * at the specified row and col.
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return the max number of tokens that will be added as a result of this move.
	 */
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