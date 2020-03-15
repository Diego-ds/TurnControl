package CustomExceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class UserAlreadyHasTurnException extends Exception implements Serializable{
		public UserAlreadyHasTurnException(String name) {
			super("The user "+name+" has been assigned with a turn previously");
		}
}
