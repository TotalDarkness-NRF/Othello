package ca.yorku.eecs3311.a1;

/**
 * Keep track of all of the tokens on the board. This understands some
 * interesting things about an Othello board, what the board looks like at the
 * start of the game, what the players tokens look like ('X' and 'O'), whether
 * given coordinates are on the board, whether either of the players have a move
 * somewhere on the board, what happens when a player makes a move at a specific
 * location (the opposite players tokens are flipped).
 * 
 * Othello makes use of the OthelloBoard.
 * 
 * @author Ilir
 *
 */
public class OthelloBoard {
	
	public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	private int dim = 8;
	private char[][] board;

	public OthelloBoard(int dim) {
		this.dim = dim;
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		int mid = this.dim / 2;
		this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
		this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
	}

	public int getDimension() {
		return this.dim;
	}

	/**
	 * 
	 * @param player either P1 or P2
	 * @return P2 or P1, the opposite of player
	 */
	public static char otherPlayer(char player) {
		if (player == P1) return P2;
		if (player == P2) return P1;
		return EMPTY;
	}

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2 or EMPTY, EMPTY is returned for an invalid (row,col)
	 */
	public char get(int row, int col) {
		if (!validCoordinate(row, col)) return EMPTY;
		char piece = board[row][col];
		if (piece == P1 || piece == P2) {
			return piece;
		}
		return EMPTY;
	}

	/**
	 * 
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return whether (row,col) is a position on the board. Example: (6,12) is not
	 *         a position on the board.
	 */
	private boolean validCoordinate(int row, int col) {
		return row >= 0 && row < getDimension() && col >= 0 && col < getDimension();
	}

	/**
	 * Check if there is an alternation of P1 next to P2, starting at (row,col) in
	 * direction (drow,dcol). That is, starting at (row,col) and heading in
	 * direction (drow,dcol), you encounter a sequence of at least one P1 followed
	 * by a P2, or at least one P2 followed by a P1. The board is not modified by
	 * this method. Why is this method important? If
	 * alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
	 * assuming that square is EMPTY, is a valid move, resulting in a collection of
	 * P2 being flipped.
	 *
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
	 *         alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
	 *         alternation
	 */
	private char alternation(int row, int col, int drow, int dcol) {
		if (drow < -1 || drow > 1) return EMPTY;
		if (dcol < -1 || dcol > 1) return EMPTY;
		// TODO accept 0 0?
		// TODO make isValidDirection(drow, dcol) method
		char player = get(row, col);
		char nextPiece = get(row + drow, col + dcol);
		if (player == EMPTY || nextPiece == EMPTY) return EMPTY;
		if (otherPlayer(player) == nextPiece) return nextPiece;
		return EMPTY;
	}

	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 * @return the number of other player tokens actually flipped, -1 if this is not
	 *         a valid move in this one direction, that is, EMPTY or the end of the
	 *         board is reached before seeing a player token.
	 */
	private int flip(int row, int col, int drow, int dcol, char player) {
		if (player != P1 && player != P2) return -1;
		// TODO helper method for this check?
		if (drow < -1 || drow > 1) return EMPTY;
		if (dcol < -1 || dcol > 1) return EMPTY;
		int count = 0;
		if (player == hasMove(row, col, drow, dcol)) {
			char piece;
			char otherPlayer = otherPlayer(player);
			do {
				row += drow;
				col += dcol;
				count++;
				piece = get(row, col);
				board[row][col] = player;
			} while (piece == otherPlayer);
		}
		return count - 1;
	}

	/**
	 * Return which player has a move (row,col) in direction (drow,dcol).
	 * 
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1,P2,EMPTY
	 */
	private char hasMove(int row, int col, int drow, int dcol) {
		char piece = get(row, col);
		if (piece != EMPTY) return EMPTY;
		char otherPlayer = get(row + drow, col + dcol);
		if (otherPlayer == P1 || otherPlayer == P2) {
			char player = otherPlayer(otherPlayer);
			// TODO keep going in this direction, see if next piece is player.
			do {
				// TODO first is always true cause its repeat of otherPlayer
				row += drow;
				col += dcol;
				piece = get(row, col);
			} while (piece == otherPlayer);
			if (player == piece) return player;
		}
		return EMPTY;
	}

	/**
	 * Return which player has a move (row,col) in directions drow {-1, 0, 1}
	 * to direction dcol {-1, 0, 1}.
	 *
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2,EMPTY
	 */
	private char hasMove(int row, int col) {
		char piece = get(row, col);
		if (piece != EMPTY) return EMPTY;
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				piece = hasMove(row, col, drow, dcol);
				if (piece != EMPTY) return piece;
			}
		}
		return EMPTY;
	}

	/**
	 * 
	 * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
	 *         neither do.
	 */
	public char hasMove() {
		boolean hasMoveP1 = false;
		boolean hasMoveP2 = false;
		for (int row = 0; row < getDimension(); row++) {
			for (int col = 0; col < getDimension(); col++) {
				char piece = get(row, col);
				// TODO need to check for case Empty (new move)
				// TODO need to check for case of not Empty (check if surrounded)
				if (piece != EMPTY) continue;
				char player = hasMove(row, col);
				if (!hasMoveP1 && player == P1) {
					hasMoveP1 = true;
				} else if (!hasMoveP2 && player == P2) {
					hasMoveP2 = true;
				}
				if (hasMoveP1 && hasMoveP2) return BOTH;
			}
		}
		if (hasMoveP1) return P1;
		if (hasMoveP2) return P2;
		return EMPTY;
	}

	/**
	 * Make a move for player at position (row,col) according to Othello rules,
	 * making appropriate modifications to the board. Nothing is changed if this is
	 * not a valid move.
	 * 
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player P1 or P2
	 * @return true if player moved successfully at (row,col), false otherwise
	 */
	public boolean move(int row, int col, char player) {
		if (player != P1 && player != P2) return false;
		boolean hasFlipped = false;
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				if (flip(row, col, drow, dcol, player) > 0) {
					// TODO check if multiple flips in different direction can happen
					// TODO need to check if any other pieces has a new move

					hasFlipped = true;

					/*
					boolean hasUpdated;
					do {
						hasUpdated = updateSurrounded();
					} while (hasUpdated);
					// TODO constantly flip flops
					 */
				}
			}
		}
		if (hasFlipped) board[row][col] = player;  // TODO stupid
		return hasFlipped;
	}


	/**
	 * Update pieces that are surrounded
	 * A player is surrounded if opposite directions
	 * from its place on the board is another player
	 *
	 * @return true if any piece was updated
	 *//*
	private boolean updateSurrounded() {
		//char hasMove = hasMove();
		//if (hasMove == EMPTY) return false;
		for (int row = 0; row < getDimension(); row++) {
			for (int col = 0; col < getDimension(); col++) {
				if (updateSurrounded(row, col)) {
					// TODO update piece if surrounded
					return true;
				}
			}
		}
		return false;
	}*/

	/**
	 * Update if player is surrounded at row, col.
	 * A player is surrounded if opposite directions
	 * from row, col is another player
	 *
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return true if player was updated because surrounded by other player
	 *//*
	private boolean updateSurrounded(int row, int col) {
		char player = get(row, col);
		if (player != P1 && player != P2) return false;
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				char otherPlayer = otherPlayer(player);
				if (alternation(row, col, drow, dcol) == otherPlayer) {
					// TODO alternation might be wrong output?
					// TODO fix flipflop of X to O updates
					char piece;
					int newRow = row;
					int newCol = col;
					do {
						// TODO first is always true cause its repeat of otherPlayer
						newRow += drow;
						newCol += dcol;
						piece = get(newRow, newCol);
					} while (piece == otherPlayer);
					newRow = row;
					newCol = col;
					if (player == piece) {
						do {
							// TODO first is always true cause its repeat of otherPlayer
							newRow += drow;
							newCol += dcol;
							piece = get(newRow, newCol);
							board[newRow][newCol] = player;
						} while (piece == otherPlayer);
						return true;
					}
				}
			}
		}
		return false;
	}*/

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	public int getCount(char player) {
		if (player != P1 && player != P2) return 0;
		int count = 0;
		for (int row = 0; row < getDimension(); row++) {
			for (int col = 0; col < getDimension(); col++) {
				if (player == get(row, col)) count++;
			}
		}
		return count;
	}

	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information. DO NOT MODIFY THIS!!
	 */
	public String toString() {
		/**
		 * See assignment web page for sample output.
		 */
		String s = "";
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < this.dim; col++) {
			s += "-+";
		}
		s += '\n';

		for (int row = 0; row < this.dim; row++) {
			s += row + "|";
			for (int col = 0; col < this.dim; col++) {
				s += this.board[row][col] + "|";
			}
			s += row + "\n";

			s += " +";
			for (int col = 0; col < this.dim; col++) {
				s += "-+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';
		return s;
	}

	/**
	 * A quick test of OthelloBoard. Output is on assignment page.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		OthelloBoard ob = new OthelloBoard(8);
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = P1;
			}
		}
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));

		// Should all be blank
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				if (row == 0 || col == 0) {
					ob.board[row][col] = P2;
				}
			}
		}
		System.out.println(ob.toString());

		// Should all be P2 (O) except drow=0,dcol=0
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		// Can't move to (4,4) since the square is not empty
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

		ob.board[4][4] = EMPTY;
		ob.board[2][4] = EMPTY;

		System.out.println(ob.toString());

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
			}
		}
		System.out.println("who has a move=" + ob.hasMove());
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
		System.out.println(ob.toString());

	}
}

