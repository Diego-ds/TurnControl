package CustomExceptions;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TurnNotAssignedYetException extends Exception implements Serializable {
	public TurnNotAssignedYetException(String turn) {
		super("The turn "+turn+" has not been assigned yet");
	}

}
