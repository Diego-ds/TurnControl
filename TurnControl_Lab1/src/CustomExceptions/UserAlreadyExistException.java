package CustomExceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends Exception implements Serializable{
public UserAlreadyExistException(String username,String userId) {
	super("The user with the username: "+username+" and Id: "+userId+" already exists");
}


}
