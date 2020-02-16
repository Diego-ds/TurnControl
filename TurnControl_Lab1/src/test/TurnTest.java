package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Turn;

class TurnTest {
	
	private Turn turn;
	
	public void setup2() {
		turn = new Turn("A00",Turn.NO_ATENDIDO);
	}
	
	@Test
	public void testTurn() {
		
		setup2();
		assertEquals("A00", turn.getTurn());
		assertEquals(Turn.NO_ATENDIDO, turn.getStatus());
		
	}

}
