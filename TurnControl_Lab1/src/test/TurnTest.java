package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Turn;

class TurnTest {
	
	private Turn turn;
	
	public void setup1() {
		turn = new Turn("A00",Turn.NO_ATENDIDO);
	}
	
	@Test
	public void testTurn() {
		
		setup1();
		assertEquals("A00", turn.getTurn());
		assertEquals(Turn.NO_ATENDIDO, turn.getStatus());
		
	}

}
