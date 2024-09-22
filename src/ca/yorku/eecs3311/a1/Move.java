package ca.yorku.eecs3311.a1;

/**
 * Represents a Move position
 * containing the initial row and column.
 * It is to be used as the starter position
 * for a Move on an Othello Board.
 * @author ilir
 *
 */
public class Move {
	final private int row, col;

	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 *
	 * @return the initial row of the move.
	 */
	public int getRow() {
		return row;
	}

	/**
	 *
	 * @return the initial column of the move.
	 */
	public int getCol() {
		return col;
	}

	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
