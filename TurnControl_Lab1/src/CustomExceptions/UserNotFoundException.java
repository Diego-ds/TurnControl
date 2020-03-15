package CustomExceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserNotFoundException extends Exception implements Serializable {
	public UserNotFoundException(String id,String typeId) {
		super("The user with "+typeId+": "+id+ " can't be found in the system");
	}
}
