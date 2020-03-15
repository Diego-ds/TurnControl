package model;

import java.io.Serializable;

public class Turn implements Serializable {
	private static final long serialVersionUID = 1L;
	private String turn;
	private String status;
	public final static String ATENDIDO ="Attended";
	public final static String NO_ESTABA ="The user wasn't present";
	public final static String NO_ATENDIDO ="Not attended yet";
	private TurnType type;
	
	public Turn(String turn,String status,TurnType type) {
		this.turn=turn;
		this.status=status;
		this.type=type;
		
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
	public TurnType getType() {
		return type;
	}
	public void setType(TurnType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Turn: "+turn+"|"+" Status:"+status+"\n";
	}
	

	
	
	
}
