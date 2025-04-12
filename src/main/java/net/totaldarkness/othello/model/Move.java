package net.totaldarkness.othello.model;

import java.io.Serializable;

/**
 * Represents a Move position
 * containing the initial row and column.
 * It is to be used as the starter position
 * for a Move on an Othello Board.
 * @author ilir
 *
 */
public class Move implements Serializable {
	final private int row, col;

	/**
	 * Constructs a Move position using row and col
	 * as the initial position.
	 * @param row the initial row
	 * @param col the initial col
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
