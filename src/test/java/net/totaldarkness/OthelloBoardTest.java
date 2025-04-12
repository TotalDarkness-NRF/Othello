package net.totaldarkness;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.totaldarkness.othello.model.Move;
import net.totaldarkness.othello.model.Othello;
import net.totaldarkness.othello.model.OthelloBoard;

import static org.junit.jupiter.api.Assertions.*;

public class OthelloBoardTest {
	OthelloBoard board;
	Move[] moves = { new Move(2, 4), new Move(2, 5), new Move(2, 6), new Move(2, 3), new Move(2, 2), new Move(3, 2),
			new Move(4, 2), new Move(5, 4), new Move(6, 4) };
	
	@BeforeEach
	public void setUp() throws Exception {
		board=new OthelloBoard(Othello.DIMENSION); // 8x8 board
		board.move(2, 4, OthelloBoard.P1);
		board.move(2, 5, OthelloBoard.P2);
		board.move(2, 6, OthelloBoard.P1);
		board.move(2, 3, OthelloBoard.P2);
		
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
	public void testOthelloBoard() {
		OthelloBoard b=new OthelloBoard(Othello.DIMENSION);
		// Check initial position
		assertEquals(b.get(3, 3), OthelloBoard.P1, "Initial tokens P1");
		assertEquals(b.get(4, 4), OthelloBoard.P1, "Initial tokens P1");
		assertEquals(b.get(3, 4), OthelloBoard.P2, "Initial tokens P2");
		assertEquals(b.get(4, 3), OthelloBoard.P2, "Initial tokens P2");
		
		for(int row=0;row<Othello.DIMENSION;row++) {
			for(int col=0;col<Othello.DIMENSION;col++) {
				if((row==3 || row==4) && (col==3 ||col==4))continue;
				assertEquals(b.get(row, col),OthelloBoard.EMPTY, "Initial empty spots");
			}
		}
		
	}

	@Test
	public void testGet() {
		// initialized board to OthelloBoard.DIMENSION
		int dim=Othello.DIMENSION;
		OthelloBoard b=new OthelloBoard(Othello.DIMENSION);

		assertEquals(b.get(-7,12), OthelloBoard.EMPTY, "Off board get");
		assertEquals(b.get(3,11), OthelloBoard.EMPTY, "Off board get");
		assertEquals(b.get(11,3), OthelloBoard.EMPTY, "Off board get");
		assertEquals(b.get(22,22), OthelloBoard.EMPTY, "Off board get");
		assertEquals(b.get(0,dim), OthelloBoard.EMPTY, "Off board get");
		assertEquals(b.get(dim,0), OthelloBoard.EMPTY, "Off board get");
		
		assertEquals(b.get(3, 3), OthelloBoard.P1, "On board get P1");
		assertEquals(b.get(4, 4), OthelloBoard.P1, "On board get P1");
		assertEquals(b.get(3, 4), OthelloBoard.P2, "On board get P2");
		assertEquals(b.get(4, 3), OthelloBoard.P2, "On board get P2");
	}

	@Test
	public void testGetDimension() {
		assertEquals(board.getDimension(),Othello.DIMENSION);
	}

	@Test
	public void testOtherPlayer() {
		assertEquals(OthelloBoard.otherPlayer(OthelloBoard.P1),OthelloBoard.P2 );
		assertEquals(OthelloBoard.otherPlayer(OthelloBoard.P2),OthelloBoard.P1 );
		assertEquals(OthelloBoard.otherPlayer(OthelloBoard.EMPTY),OthelloBoard.EMPTY );
	}

	@Test
	public void testHasMove() {
		assertEquals(board.hasMove(),OthelloBoard.BOTH);
	}

	@Test
	public void testMove() {
		// Check the scenario we setup in board...
		//   0 1 2 3 4 5 6 7
		//  +-+-+-+-+-+-+-+-+
		// 2| | | |O|X|X|X| |2
		//  +-+-+-+-+-+-+-+-+
		// 3| | | |O|O| | | |3
		//  +-+-+-+-+-+-+-+-+
		// 4| | | |O|X| | | |4
		//  +-+-+-+-+-+-+-+-+
		//   0 1 2 3 4 5 6 7
		//
		// X:4 O:4  X moves next
		assertEquals(board.get(2,0), OthelloBoard.EMPTY);
		assertEquals(board.get(2,1), OthelloBoard.EMPTY);
		assertEquals(board.get(2,2), OthelloBoard.EMPTY);
		assertEquals(board.get(2,3), OthelloBoard.P2);
		assertEquals(board.get(2,4), OthelloBoard.P1);
		assertEquals(board.get(2,5), OthelloBoard.P1);
		assertEquals(board.get(2,6), OthelloBoard.P1);
		assertEquals(board.get(2,7), OthelloBoard.EMPTY);
		
		String beforeMove=board.toString(); // To verify that the board has not changed
		assertFalse(board.move(2, 3, OthelloBoard.P1), "bad move spot occupied");
        assertEquals(beforeMove, board.toString(), "board unchanged for bad move");
		assertFalse(board.move(2, 4, OthelloBoard.P1), "bad move spot occupied");
        assertEquals(beforeMove, board.toString(), "board unchanged for bad move");
	
		assertFalse(board.move(4, 0, OthelloBoard.P1), "bad move no neighbours");
        assertEquals(beforeMove, board.toString(), "board unchanged for bad move");
		assertFalse(board.move(3, 5, OthelloBoard.P1), "bad move no flips");
        assertEquals(beforeMove, board.toString(), "board unchanged for bad move");
		
		// row: col: X makes move (2,2)
		assertTrue(board.move(2, 2, OthelloBoard.P1));
		//
		//   0 1 2 3 4 5 6 7
		//  +-+-+-+-+-+-+-+-+
		// 2| | |X|X|X|X|X| |2
		//  +-+-+-+-+-+-+-+-+
		// 3| | | |X|O| | | |3
		//  +-+-+-+-+-+-+-+-+
		// 4| | | |O|X| | | |4	
		//  +-+-+-+-+-+-+-+-+
		//   0 1 2 3 4 5 6 7

		assertEquals(board.get(2,0), OthelloBoard.EMPTY);
		assertEquals(board.get(2,1), OthelloBoard.EMPTY);
		assertEquals(board.get(2,2), OthelloBoard.P1);
		assertEquals(board.get(2,3), OthelloBoard.P1);
		assertEquals(board.get(2,4), OthelloBoard.P1);
		assertEquals(board.get(2,5), OthelloBoard.P1);
		assertEquals(board.get(2,6), OthelloBoard.P1);
		assertEquals(board.get(2,7), OthelloBoard.EMPTY);
		
	}

	@Test
	public void testGetCount() {
		assertEquals(board.getCount(OthelloBoard.P1),4, "counting P1");
		assertEquals(board.getCount(OthelloBoard.P2),4, "counting P2");
		board.move(2, 2, OthelloBoard.P1);
		assertEquals(board.getCount(OthelloBoard.P1),7, "counting P1");
		assertEquals(board.getCount(OthelloBoard.P2),2, "counting P2");
	}
}
