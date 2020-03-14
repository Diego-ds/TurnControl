package CustomExceptions;

@SuppressWarnings("serial")
public class TimeImpossibleToChangeException extends Exception {
	public TimeImpossibleToChangeException() {
		super("The time can't be changed because the new date is earlier than actual");
	}
}
