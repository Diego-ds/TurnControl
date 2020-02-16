package test;

import static org.junit.jupiter.api.Assertions.*;
import model.TurnManagement;
import model.User;

import org.junit.jupiter.api.Test;

import CustomExceptions.UserAlreadyExistException;
import CustomExceptions.UserAlreadyHasTurnException;
import CustomExceptions.UserNotFoundException;

class TurnManagementTest {
	
	private TurnManagement control;
	
	public void setup3() {
		control = new TurnManagement();
	}
	@Test
	public void testAddUser() {
		
		setup3();
		
		try {
			control.addUser(User.TI, "132452", "Pepe", "Perez", "31721151", "calle 32");
			control.addUser(User.CC, "", "Pedro", "Garcia", "313152", "cra32");
			fail("The addUser method is adding users without mandatory data");
		} catch (UserAlreadyExistException e) {

		} catch (Exception e) {
			
		}
		
		try {
			control.addUser(User.TI, "132452", "Pepe", "Perez", "31721151", "calle 32");
			fail("The method addUser is adding repeated users");
		} catch (UserAlreadyExistException e) {
			
		} catch (Exception e) {
			
		}
		
		
	}
	
	@Test
	public void testSearchUser() {
		setup3();
		
		try {
			control.searchUser( "412313",User.RC);
			fail("There are not users registered yet, the method searchUser is not working correctly");
		} catch (UserNotFoundException e1) {
		
		}
		try {
			control.addUser(User.CE, "AS2414", "Rodrigo", "Saenz", "31312", "calle 32");
			control.searchUser( "AS2414",User.CE);
			
		} catch (UserNotFoundException e) {
			fail("The searchUser method is not working");
		} catch (UserAlreadyExistException e) {
		
		} catch (Exception e) {
		
		}
		
		try {
			control.searchUser("AS4",User.CE);
			fail("The searchUser method is not working corectly");
		} catch (UserNotFoundException e) {
			
		}
		
		
	}
	@Test
	public void testAssignTurn() {
		setup3();
		
		
		try {
			control.addUser(User.RC, "241553", "Marcela", "Blanco", "32142", "cra21");
			control.assignTurn( "241553",User.RC);
		} catch (UserNotFoundException e) {
			fail("The assignTurn method is not working correctly ");
		} catch (UserAlreadyHasTurnException e) {
			
		} catch (UserAlreadyExistException e) {
			
		} catch (Exception e) {
			
		}
		
		try {
			control.assignTurn("241553", User.RC);
			fail("the assignTurn method is not catching the exception that the user has already a turn ");
		} catch (UserNotFoundException e) {
			
		} catch (UserAlreadyHasTurnException e) {
		
		}
	}
	@Test
	public void testAdvanceTurn() {
		setup3();
		
		assertEquals("A00",control.getActualTurn() );
	
		for(int i=0;i<3000;i++) {
			if(i==1) {
				assertEquals("A01",control.getActualTurn() );
			}
			else if(i==200) {
				assertEquals("C00",control.getActualTurn() );
			}else if(i==2600) {
				assertEquals("A00",control.getActualTurn() );
			}
			control.advanceTurn();
		}
	}
	
	@Test
	public void testAttendTurn() {
		setup3();
		
		try {
			control.addUser(User.CE, "Aw241", "Marselo", "Reyes", "2311", "calle 23");
			control.assignTurn("Aw241", User.CE);
			assertEquals("The user "+"Marselo"+" "+"Reyes"+" was attended in the turn "+"A00"+"\n", control.attendTurn(true));
		} catch (UserAlreadyExistException e) {
			
		} catch (Exception e) {
		
		}
		
		
	}
	
	

}
