package CustomExceptions;

@SuppressWarnings("serial")
public class TurnNotAssignedYetException extends Exception {
	public TurnNotAssignedYetException(String turn) {
		super("The turn "+turn+" has not been assigned yet");
	}

}
