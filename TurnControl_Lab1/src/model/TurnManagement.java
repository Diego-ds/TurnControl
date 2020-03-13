package model;
import java.util.*;
import CustomExceptions.*;

public class TurnManagement {
	ArrayList <User> usuarios;
	ArrayList <String> turnList;
	ArrayList <TurnType> turnTypeList;
	char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private int posAlp;
	private int leftNum;
	private int rightNum;
	private int indexTurn;
	
	public TurnManagement() {
		turnTypeList= new ArrayList<TurnType>();
		usuarios=new ArrayList <User>();
		turnList = new ArrayList <String>();
		this.indexTurn=0;
		this.posAlp=0;
		this.leftNum=0;
		this.rightNum=0;
		
	}
	
	public void addTurnType(String name,double time) {
		TurnType tp = new TurnType(time,name);
		turnTypeList.add(tp);
		
	}
	public TurnType searchTurnType(String name) {
		TurnType tp = null;
		for(int i=0;i<turnTypeList.size();i++) {
			if(turnTypeList.get(i).getName().equalsIgnoreCase(name)) {
				tp=turnTypeList.get(i);
			}
		}
		return tp;
	}
	/**
     * <b>Name:</b> addUser.<br>
     * This method adds a new user, if the user already exist or there are missing mandatory data then return an exception.<br>
     * <b>pre:</b> ArrayList usuarios must be initialized.<br>
     * <b>pos:</b> the user has been created and added or it throw an exception.<br>
     * @param  typeId the type of the ID. <br>
     * @param  id the number of the ID. <br>
     * @param  firstNames the first names of the user. <br>
     * @param  lastNames the last names of the user. <br>
     * @param  adress the address of the user (it can be blanked). <br>
     * @param  telephone the telephone of the user (it can be blanked). <br>
     * @throws UserAlreadyExistException<br> 
     * @throws Exception<br> 
     * @return void<br>
    */
	public void addUser(String typeId,String id,String name,String lastname,String phone,String adress) throws UserAlreadyExistException, Exception {
		if(id.isEmpty() || name.isEmpty() || lastname.isEmpty()) {
			throw new Exception("Error: ID, name and lastname are mandatory data for registration");
		}
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
	/**
     * <b>Name:</b> searchUser.<br>
     * This method search an user by his typeId and ID, if the user don't exist throw an exception.<br>
     * <b>pre:</b> ArrayList usuarios must be initialized.<br>
     * <b>pos:</b> the user has been found it and returned or throw an exception.<br>
     * @param  typeId the type of the ID. <br>
     * @param  id the number of the ID. <br>
     * @throws UserNotFoundException<br> 
     * @return obj User object<br>
    */
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
	
	/**
     * <b>Name:</b> assignTurn.<br>
     * This method assign a turn to a user by his typeID and ID, if the user don exist or already has a turn throw an exception.<br>
     * <b>pre:</b> ArrayList usuarios must be initialized.<br>
     * <b>pos:</b> the user has been assigned with a turn and returned a message or throw an exception.<br>
     * @param  typeId the type of the ID. <br>
     * @param  id the number of the ID. <br>
     * @throws UserNotFoundException<br> 
     * @throws UserAlreadyHasTurnException<br>
     * @throws NotTurnTypeException <br>
     * @return msg String indicating if the turn was correctly assigned<br>
	 
    */
	public String assignTurn(String id,String typeId,String nameTurnType) throws UserNotFoundException, UserAlreadyHasTurnException, NotTurnTypeException {
		String msg="";
		if(searchUser(id,typeId).getTurn()!=null && searchUser(id,typeId).getTurn().getStatus().equalsIgnoreCase(Turn.NO_ATENDIDO) ) {
			throw new UserAlreadyHasTurnException(searchUser(id,typeId).getName());
		}else if(turnTypeList.isEmpty() || searchTurnType(nameTurnType)==null){
			throw new NotTurnTypeException();
		}else {
			searchUser(id,typeId).setTurn(getActualTurn(), Turn.NO_ATENDIDO,searchTurnType(nameTurnType));
			msg="User with the ID "+ typeId+": "+id+" has been assigned with the turn: "+getActualTurn();
		}
		turnList.add(getActualTurn());
		advanceTurn();
		return msg;
	}
	/**
     * <b>Name:</b> getActualTurn.<br>
     * This method gets the actual turn.<br>
     * <b>pre:</b> Array alphabet, int leftNum and int rightNum must be initialized.<br>
     * <b>pos:</b> the actual turn has been returned.<br>
     * @return turnct String indicating the turn<br>
    */
	public String getActualTurn() {
		String turnAct= String.format("%c%d%d",alphabet[posAlp],leftNum,rightNum);
		return turnAct;
	}
	/**
     * <b>Name:</b> getListTurn.<br>
     * This method gets the actual turn in queue.<br>
     * <b>pre:</b> ArrayList turnList must be initialized.<br>
     * <b>pos:</b> the actual turn in queue has been returned.<br>
     * @return t String indicating the turn in queue<br>
    */
	public String getListTurn() throws TurnNotAssignedYetException {
		String t="";
		if(turnList.isEmpty()) {
			throw new TurnNotAssignedYetException(getActualTurn());
		}else {
			t= turnList.get(indexTurn);
		}
		return t;
	}
	/**
     * <b>Name:</b> advanceTurn.<br>
     * This method advance a turn .<br>
     * <b>pre:</b> Array alphabet, int leftNum and int rightNum must be initialized.<br>
     * <b>pos:</b> the actual turn has advanced in 1.<br>
     * @return void<br>
    */
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
			if(posAlp>25) {
			posAlp=0;
			leftNum=0;
			rightNum=0;
		}
		}
			
		
	}
	/**
     * <b>Name:</b> attendTurn.<br>
     * This method attend a user in queue, if the turn has not been assigned yet throw an exception.<br>
     * <b>pre:</b> ArrayList usuarios must be initialized.<br>
     * <b>pos:</b> the user has been attended and returned a message or throw an exception.<br>
     * @param  attend boolean indicate if the user was attended or not was present. <br>
     * @throws TurnNotAssignedYetException<br> 
     * @return msg String indicating if the user was attended<br>
    */
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
	
	public String showUser(String typeId,String Id) throws UserNotFoundException {
		String msg= "";
		msg =searchUser(Id,typeId).toString();
		return msg;
	}
}
