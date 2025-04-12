package net.totaldarkness;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.totaldarkness.othello.model.Move;
import net.totaldarkness.othello.model.Othello;
import net.totaldarkness.othello.model.OthelloBoard;

import static org.junit.jupiter.api.Assertions.*;

public class OthelloTest {
	Othello othello;
	Move[] moves = { new Move(2, 4), new Move(2, 5), new Move(2, 6), new Move(2, 3), new Move(2, 2), new Move(3, 2),
			new Move(4, 2), new Move(5, 4), new Move(6, 4) };

	@BeforeEach
	public void setUp() throws Exception {
		othello = new Othello();
		othello.move(2, 4);
		othello.move(2, 5);
		othello.move(2, 6);
		othello.move(2, 3);
		
		// Board now looks like
		//   0 1 2 3 4 5 6 7
		//  +-+-+-+-+-+-+-+-+
		// 0| | | | | | | | |0
		//  +-+-+-+-+-+-+-+-+
		// 1| | | | | | | | |1
		//  +-+-+-+-+-+-+-+-+
		// 2| | | |O|X|X|X| |2
		//  +-+-+-+-+-+-+-+-+
		// 3| | | |O|O| | | |3
		//  +-+-+-+-+-+-+-+-+
		// 4| | | |O|X| | | |4
		//  +-+-+-+-+-+-+-+-+
		// 5| | | | | | | | |5
		//  +-+-+-+-+-+-+-+-+
		// 6| | | | | | | | |6
		// +-+-+-+-+-+-+-+-+
		// 7| | | | | | | | |7
		//  +-+-+-+-+-+-+-+-+
		//   0 1 2 3 4 5 6 7
		//
		// X:4 O:4  X moves next
		// row: col: X makes move (2,2)
		//
		//   0 1 2 3 4 5 6 7
		//  +-+-+-+-+-+-+-+-+
		// 0| | | | | | | | |0
		//  +-+-+-+-+-+-+-+-+
		// 1| | | | | | | | |1
		//  +-+-+-+-+-+-+-+-+
		// 2| | |X|X|X|X|X| |2
		//  +-+-+-+-+-+-+-+-+
		// 3| | | |X|O| | | |3
		//  +-+-+-+-+-+-+-+-+
		// 4| | | |O|X| | | |4
		//  +-+-+-+-+-+-+-+-+
		// 5| | | | | | | | |5
		//  +-+-+-+-+-+-+-+-+
		// 6| | | | | | | | |6
		//  +-+-+-+-+-+-+-+-+
		// 7| | | | | | | | |7
		//  +-+-+-+-+-+-+-+-+
		//   0 1 2 3 4 5 6 7
		//
		// X:7 O:2  O moves next

	}

	@Test
	public void testGetWhosTurn() {
		assertEquals(othello.getWhosTurn(),OthelloBoard.P1);
		othello.move(2, 2);
		assertEquals(othello.getWhosTurn(),OthelloBoard.P2);
	}

	@Test
	public void testGetCount() {
		assertEquals(othello.getCount(OthelloBoard.P1), 4);
		assertEquals(othello.getCount(OthelloBoard.P2), 4);
		othello.move(2, 2);
		assertEquals(othello.getCount(OthelloBoard.P1), 7);
		assertEquals(othello.getCount(OthelloBoard.P2), 2);
	}

	@Test
	public void testGetWinner() {
		Othello o=new Othello();
        for (Move move : moves) {
            assertEquals(o.getWinner(), OthelloBoard.EMPTY, "During play");
            o.move(move.getRow(), move.getCol());
        }
		assertEquals(o.getWinner(), OthelloBoard.P1, "After winner");
	}

	@Test
	public void testIsGameOver() {
		Othello o=new Othello();
		for(int i=0;i<moves.length;i++) {
            assertFalse(o.isGameOver(), "During play");
			o.move(moves[i].getRow(), moves[i].getCol());
		}
        assertTrue(o.isGameOver(), "After winner");
	}

}

