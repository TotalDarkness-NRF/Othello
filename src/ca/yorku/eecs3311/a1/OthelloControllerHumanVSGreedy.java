package ca.yorku.eecs3311.a1;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 * @author ilir
 *
 */
public class OthelloControllerHumanVSGreedy extends OthelloController {
	private final OthelloReporter reporter;

	public OthelloControllerHumanVSGreedy() {
		super(new Othello(), new PlayerHuman(OthelloBoard.P1), new PlayerGreedy(OthelloBoard.P2));
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
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a greedy strategy, that is, it picks the first
	 * move which maximizes its number of token on the board.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 * @param args
	 */
	public static void main(String[] args) {
		OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy();
		oc.play();
	}
}
