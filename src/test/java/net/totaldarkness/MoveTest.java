package net.totaldarkness;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import net.totaldarkness.othello.model.Move;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {
	Move move;
	@BeforeEach
	public void setUp() throws Exception {
		move=new Move(3,5);
	}

	@Test
	public void testGetRow() {
		assertEquals(move.getRow(),3, "getRow");
	}

	@Test
	public void testGetCol() {
		assertEquals(move.getCol(),5, "getCol");
	}

	@Test
	public void testToString() {
		assertEquals(move.toString(),"(3,5)", "toString");
	}

}

