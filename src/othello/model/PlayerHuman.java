package othello.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PlayerHuman makes a move by requesting input from a human in the console.
 * The player will be asked for the row and col, where the player must
 * input a valid number on the Othello board. If the player does not put a valid number
 * they can try again until they input a valid number.
 * 
 * @author ilir
 *
 */
public class PlayerHuman extends Player {
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Requests input from a human player in console.
	 * First asks the player for which row and waits for a valid input.
	 * Then asks the player for which col and waits for a valid input.
	 * Finally, returns a move using the provided row and col.
	 *
	 * @return a move using the provided row and col.
	 */
	public Move getMove() {
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	/**
	 * Repeatedly asks a human player in console for an input
	 * specifying what it wants using the provided message.
	 *
	 * @param message to be displayed to the player in console
	 * @return a valid number that the player inputs
	 */
	private int getMove(String message) {
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}

