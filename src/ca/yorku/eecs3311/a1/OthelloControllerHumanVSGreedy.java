package ca.yorku.eecs3311.a1;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 * @author ilir
 *
 */
public class OthelloControllerHumanVSGreedy extends OthelloController {

	/**
	 * Constructs a new OthelloController with a new Othello game, ready to play
	 * with one user at the console and one player using a greedy strategy.
	 */
	public OthelloControllerHumanVSGreedy() {
		super(new Othello(), new PlayerHuman(), new PlayerGreedy());
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
