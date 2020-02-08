package CustomExceptions;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception{
public UserAlreadyExistException(String username,String userId) {
	super("The user with the username: "+username+" and Id: "+userId+" already exists");
}


}
