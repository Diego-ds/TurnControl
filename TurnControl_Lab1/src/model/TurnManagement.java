package model;
import java.util.*;
import CustomExceptions.*;

public class TurnManagement {
	ArrayList <User> usuarios;
	ArrayList <String> turnList;
	char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private int posAlp;
	private int leftNum;
	private int rightNum;
	private int indexTurn;
	
	public TurnManagement() {
		usuarios=new ArrayList <User>();
		turnList = new ArrayList <String>();
		this.indexTurn=0;
		this.posAlp=0;
		this.leftNum=0;
		this.rightNum=0;
		
	}
	public void addUser(String typeId,String id,String name,String lastname,String phone,String adress) throws UserAlreadyExistException {
		User us = new User(typeId,id,name,lastname,phone,adress);
			if(usuarios.isEmpty()) {
				usuarios.add(us);
			}else {
				for(int i =0;i<usuarios.size();i++) {
					 if(usuarios.get(i).getId().equalsIgnoreCase(id) && usuarios.get(i).getTypeId().equalsIgnoreCase(typeId)) {
						throw new UserAlreadyExistException(name,id);
					 }
				}
				
				usuarios.add(us);
			}
			
	}
	public User searchUser(String id,String typeId) throws UserNotFoundException {
		User obj = null;
		if(usuarios.isEmpty()) {
			throw new UserNotFoundException(id,typeId);
		 }else{
			 for(int i=0;i<usuarios.size();i++) {
				 if(usuarios.get(i).getId().equalsIgnoreCase(id) && usuarios.get(i).getTypeId().equalsIgnoreCase(typeId) ) {
					obj=usuarios.get(i);
				 }
			 }
		}			
		if(obj==null) {
			throw new UserNotFoundException(id,typeId);
		}
		
		return obj;
		
	}
	//crear metodo si a la persona ya le han asignado turno
	
	public String assignTurn(String id,String typeId) throws UserNotFoundException, UserAlreadyHasTurnException {
		String msg="";
		if(searchUser(id,typeId).getTurn()!=null) {
			throw new UserAlreadyHasTurnException(searchUser(id,typeId).getName());
		}else {
			searchUser(id,typeId).setTurn(getActualTurn(), Turn.NO_ATENDIDO);
			msg="User with the ID: "+ typeId+": "+id+" has been assigned with the turn: "+getActualTurn();
		}
		turnList.add(getActualTurn());
		advanceTurn();
		return msg;
	}
	
	public String getActualTurn() {
		String turnAct= String.format("%c%d%d",alphabet[posAlp],leftNum,rightNum);
		return turnAct;
	}
	
	public String getListTurn() throws TurnNotAssignedYetException {
		String t="";
		if(turnList.isEmpty()) {
			throw new TurnNotAssignedYetException(getActualTurn());
		}else {
			t= turnList.get(indexTurn);
		}
		return t;
	}
	public void advanceTurn() {
		rightNum++;
		if(rightNum>9) {
			if(leftNum==9) {
				leftNum=0;
				rightNum=0;
				posAlp++;
			}else {
				leftNum++;
				rightNum=0;
			}
		}else if(posAlp>25) {
			posAlp=0;
		}
			
		
	}
	
	public String attendTurn(boolean attend) throws TurnNotAssignedYetException {
		String msg="";
		boolean val=true;
		String actualT= turnList.get(indexTurn);
		if(usuarios.isEmpty()) {
			throw new TurnNotAssignedYetException(actualT);
		}else {
			for(int i=0;i<usuarios.size();i++) {
				if(usuarios.get(i).getTurn().getTurn().equalsIgnoreCase(turnList.get(indexTurn))) {
					val=false;
					if(attend) {
						usuarios.get(i).getTurn().setStatus(Turn.ATENDIDO);
						msg="The user "+usuarios.get(i).getName()+" "+usuarios.get(i).getLastname()+" was attended in the turn "+turnList.get(indexTurn)+"\n";
					}else {
						usuarios.get(i).getTurn().setStatus(Turn.NO_ESTABA);
						msg="The user "+usuarios.get(i).getName()+" "+usuarios.get(i).getLastname()+" was not here to be attended \n";
					}
				}
			}
			if(val) {
				throw new TurnNotAssignedYetException(actualT);
			}
		}
		indexTurn++;
		return msg;
	}
	
	
}
