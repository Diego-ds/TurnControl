package model;

public class Turn {
	private String turn;
	private String status;
	public final static String ATENDIDO ="Attended";
	public final static String NO_ESTABA ="The user wasn't present";
	public final static String NO_ATENDIDO ="Not attended yet";
	
	public Turn(String turn,String status) {
		this.turn=turn;
		this.status=status;
		
	}
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Turn: "+turn+"|"+" Status:"+status+"\n";
	}
	
	
}
