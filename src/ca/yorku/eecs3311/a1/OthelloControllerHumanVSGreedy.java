package ca.yorku.eecs3311.a1;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 * @author ilir
 *
 */
public class OthelloControllerHumanVSGreedy {
	protected final Othello othello;
	private final OthelloReporter reporter;
	private final PlayerHuman player1;
	private final PlayerGreedy player2;

	public OthelloControllerHumanVSGreedy() {
		this.othello = new Othello();
		this.reporter = new OthelloReporter(this.othello);
		this.player1 = new PlayerHuman(this.othello, OthelloBoard.P1);
		this.player2 = new PlayerGreedy(this.othello, OthelloBoard.P2);
	}

	public void play() {
		while (!othello.isGameOver()) {
			reporter.report();

			Move move = null;
			char whosTurn = othello.getWhosTurn();

			if (whosTurn == OthelloBoard.P1)
				move = player1.getMove();
			if (whosTurn == OthelloBoard.P2)
				move = player2.getMove();

			reporter.reportMove(whosTurn, move);
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
