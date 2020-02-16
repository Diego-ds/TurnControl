package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.User;

class UserTest {

	private User user;
	
	public void setup1() {
		user = new User(User.CC,"314124","Diego","Garcia","317711342","calle 38");
	}
	
	@Test
	public void testAddUser() {
		setup1();
		assertEquals(User.CC,user.getTypeId());
		assertEquals("314124",user.getId());
		assertEquals("Diego",user.getName());
		assertEquals("Garcia",user.getLastname());
		assertEquals("317711342",user.getPhone());
		assertEquals("calle 38",user.getAdress());
		
		
	}

}
