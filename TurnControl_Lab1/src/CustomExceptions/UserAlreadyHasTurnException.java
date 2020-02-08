package CustomExceptions;

@SuppressWarnings("serial")
public class UserAlreadyHasTurnException extends Exception{
		public UserAlreadyHasTurnException(String name) {
			super("The user "+name+" has been assigned with a turn previously");
		}
}
