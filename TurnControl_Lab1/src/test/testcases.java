package test;
import model.*;
import static org.junit.jupiter.api.Assertions.*;
import CustomExceptions.*;
import org.junit.jupiter.api.Test;


class testcases {
	private User user;
	private Turn turn;
	private TurnManagement control;
	
	public void setUp1() {
		
		user = new User(User.CC,"384122","Diego","Garcia","31832324","cra 43 ");
		user.setTurn(control.getActualTurn(), Turn.NO_ATENDIDO);
		
	}
	
	
	@Test
	public void addUserTest() {
		fail("Not yet implemented");
	}

}
