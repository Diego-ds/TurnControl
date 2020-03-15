package CustomExceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TimeImpossibleToChangeException extends Exception implements Serializable {
	public TimeImpossibleToChangeException() {
		super("The time can't be changed because the new date is earlier than actual");
	}
}
