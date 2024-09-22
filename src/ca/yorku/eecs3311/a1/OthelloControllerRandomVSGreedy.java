package ca.yorku.eecs3311.a1;

/**
 * The goal here is to print out the probability that Random wins and Greedy
 * wins as a result of playing 10000 games against each other with P1=Random and
 * P2=Greedy. What is your conclusion, which is the better strategy?
 * The greedy strategy is the better strategy compared to the random strategy.
 * @author ilir
 *
 */
public class OthelloControllerRandomVSGreedy extends OthelloController {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerRandomVSGreedy() {
		super(new Othello(), new PlayerRandom(), new PlayerGreedy());
	}

	/**
	 * Plays a computer using a random strategy Vs a computer using a greedy strategy.
	 */
	public void play() {
		while (!othello.isGameOver()) {
			Move move = getNextMove();
			othello.move(move.getRow(), move.getCol());
		}
	}

	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like: 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 * @param args
	 */
	public static void main(String[] args) {
		int p1wins = 0, p2wins = 0, numGames = 10000;
		for(int games = 0; games < numGames; games++) {
			OthelloController oc = new OthelloControllerRandomVSGreedy();
			oc.play();
			char winner = oc.othello.getWinner();
			if (winner == OthelloBoard.P1) {
				p1wins++;
			} else if (winner == OthelloBoard.P2) {
				p2wins++;
			}
		}

		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}
}

