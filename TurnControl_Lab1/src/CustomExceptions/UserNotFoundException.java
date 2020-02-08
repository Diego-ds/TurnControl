package CustomExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String id) {
		super("The user with the identification: "+id+" can't be found in the system");
	}
}
