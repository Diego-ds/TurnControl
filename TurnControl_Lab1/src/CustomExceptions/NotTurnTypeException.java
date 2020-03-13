package CustomExceptions;

@SuppressWarnings("serial")
public class NotTurnTypeException extends Exception{
	public NotTurnTypeException() {
		super("Error: There are not turn types registered yet or the given name for the turn type dont exist");
	}
}
