package CustomExceptions;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception {
	public UserNotFoundException(String id,String typeId) {
		super("The user with "+typeId+": "+id+ " can't be found in the system");
	}
}
