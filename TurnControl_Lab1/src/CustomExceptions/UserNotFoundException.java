package CustomExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String username) {
		super("The user with username: "+username+" can't be found in the system");
	}
}
