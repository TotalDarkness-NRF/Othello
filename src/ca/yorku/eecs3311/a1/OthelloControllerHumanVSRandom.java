package ca.yorku.eecs3311.a1;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 * @author ilir
 *
 */
public class OthelloControllerHumanVSRandom extends OthelloController {
	private final OthelloReporter reporter;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with one user at the console and one player using a random strategy.
	 */
	public OthelloControllerHumanVSRandom() {
		super(new Othello(), new PlayerHuman(), new PlayerRandom());
		this.reporter = new OthelloReporter(othello);
	}

	public void play() {
		while (!othello.isGameOver()) {
			reporter.report();
			Move move = getNextMove();
			reporter.reportMove(othello.getWhosTurn(), move);
			othello.move(move.getRow(), move.getCol());
		}
		reporter.reportFinal();
	}
	
	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a random strategy, that is, it picks a random
	 * move that it can make.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloController oc = new OthelloControllerHumanVSRandom();
		oc.play();
	}
}
