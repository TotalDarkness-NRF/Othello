package ca.yorku.eecs3311.a1;

/**
 * Run the main from this class to play two humans against eachother. Only
 * minimal modifications to this class are permitted.
 * 
 * @author arnold
 *
 */
public class OthelloControllerHumanVSHuman extends OthelloController {
	private final OthelloReporter reporter;

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with two users at the console.
	 */
	public OthelloControllerHumanVSHuman() {
		super(new Othello(), new PlayerHuman(OthelloBoard.P1), new PlayerHuman(OthelloBoard.P2));
		this.reporter = new OthelloReporter(this.othello);
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
	 * Run main to play two Humans against each other at the console.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloController oc = new OthelloControllerHumanVSHuman();
		oc.play();
	}

}

