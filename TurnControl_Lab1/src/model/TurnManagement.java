package model;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import CustomExceptions.*;

public class TurnManagement implements Serializable{
	private static final long serialVersionUID = 1L;
	ArrayList <User> usuarios;
	ArrayList <String> turnList;
	ArrayList <TurnType> turnTypeList;
	Date sysDate;
	Time sysTime;
	char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	private int posAlp;
	private int leftNum;
	private int rightNum;
	private int indexTurn;
	
	public TurnManagement() {
		sysDate=new Date();
		sysTime=new Time();
		turnTypeList= new ArrayList<TurnType>();
		usuarios=new ArrayList <User>();
		turnList = new ArrayList <String>();
		this.indexTurn=0;
		this.posAlp=0;
		this.leftNum=0;
		this.rightNum=0;
		
	}
	/**
     * <b>Name:</b> generateUser.<br>
     * This method adds a new random user.<br>
     * <b>pre:</b> ArrayList usuarios must be initialized.<br>
     * <b>pos:</b> the user has been created and added .<br>
     * @throws UserAlreadyExistException<br> 
     * @throws Exception<br> 
     * @return void<br>
    */
	public void generateUser() throws UserAlreadyExistException, Exception {
		String id;
		String typeId;
		String name;
		String lastname;
		String phone;
		String adress;
		BufferedReader br = new BufferedReader(new FileReader("data/Lastname.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("data/Names.txt"));
		String []na = br2.readLine().split(";");
		String [] la=br.readLine().split(";");
		Random random = new Random();
		name=na[random.nextInt(na.length)];
		lastname=la[random.nextInt(la.length)];
		phone = String.valueOf((long)(300000000+(random.nextDouble()*99999999.0)-30000000));
		id = String.valueOf((long)(100000000+(random.nextDouble()*99999999.0)-10000000));
		int r = random.nextInt(4);
		if(r==0) {
			typeId=User.CC;
		}else if(r==1) {
			typeId=User.RC;
		}else if(r==2) {
			typeId=User.CE;
		}else {
			typeId=User.TI;
		}
		adress = "Street "+random.nextInt(100)+" # "+random.nextInt(50)+"-"+random.nextInt(100);
		addUser(typeId, id, name,lastname,phone,adress);
	}
	/**
     * <b>Name:</b> numberNewUser.<br>
     * This method adds a detemined number of random users.<br>
     * <b>pre:</b> ArrayList usuarios must be initialized.<br>
     * <b>pos:</b> the users has been created and added .<br>
     * @throws UserAlreadyExistException<br> 
     * @throws Exception<br> 
     * @return void<br>
    */
	public void numberNewUsers(int n) throws UserAlreadyExistException, Exception {
		for(int i=0;i<n;i++) {
			generateUser();
		}
	}
	/**
     * <b>Name:</b> attendTurnsUntilNow.<br>
     * This method attend all the turns until the actual date and time.<br>
     * <b>pre:</b> There must be at least 1 turn created.<br>
     * <b>pos:</b> The turns has been attended checking the duration of each one until the actual date and time of the system .<br>
     * @throws TurnNotAssignedYetException<br> 
     * @throws TimeImpossibleToChangeException<br> 
     * @return msg String indicating the turns that has been attended<br>
    */
	public String attendTurnsUntilNow() throws TurnNotAssignedYetException, TimeImpossibleToChangeException {
		boolean val=false;
		Random r = new Random();
		String msg="";
		if(turnList.isEmpty()) {
			throw new TurnNotAssignedYetException("A00");
		}else {
			Calendar actual = Calendar.getInstance();
			GregorianCalendar myC = new GregorianCalendar(sysDate.getYear(),sysDate.getMonth(),sysDate.getDay(),sysTime.getHour(),sysTime.getMinute(),sysTime.getSeconds());
			while(!val) {
				if(actual.after(myC)) {
					boolean attend=true;
					int e = r.nextInt(2);
					if(e==1) {
						attend=false;
					}
					if(searchActualTurn()!=null) {
						myC.add(Calendar.MINUTE, (int) searchActualTurn().getType().getTime());
						double parteDecimal = searchActualTurn().getType().getTime() % 1;
						if(parteDecimal<=0.25) {
							myC.add(Calendar.SECOND, 15);
						}else if(parteDecimal>0.25 && parteDecimal<=0.50) {
							myC.add(Calendar.SECOND, 30);
						}else if(parteDecimal>0.50 && parteDecimal<=0.75) {
							myC.add(Calendar.SECOND, 45);
						}else {
							myC.add(Calendar.SECOND, 55);
						}
						if(actual.after(myC)) {
							msg+=attendTurn(attend);
							myC.add(Calendar.SECOND, 15);
						}
					}else {
						val=true;
					}	
				}else {
					val=true;
				}
				updateDateTime();
			}
		}
		return msg;
	}
	/**
     * <b>Name:</b> updateDateTime.<br>
     * This method update the date and time of the system to the actual one.<br>
     * <b>pos:</b> the date and time has been updated .<br>
     * @throws TimeImpossibleToChangeException<br> 
     * @return void<br>
    */
	public void updateDateTime() throws TimeImpossibleToChangeException {
		sysTime.updateTime();
		sysDate.updateDate();
	}
	/**
     * <b>Name:</b> getDateTime.<br>
     * This method get the actual date and time in text chain.<br>
     * <b>pos:</b> The date and time has been turned into a String .<br>
     * @throws TimeImpossibleToChangeException<br> 
     * @return String with the actual date and time<br>
    */
	public String getDateTime() {
		return sysDate.getDate()+sysTime.getTime();
	}
	/**
     * <b>Name:</b> setNewDate.<br>
     * This method adds a new user, if the user already exist or there are missing mandatory data then return an exception.<br>
     * <b>pre:</b> sysDate and sysTime must be initialized.<br>
     * <b>pos:</b> The new date and time has been set up.<br>
     * @param  y The new year. <br>
     * @param  m The new month. <br>
     * @param  d The new day of the month. <br>
     * @param  h The new hour. <br>
     * @param  mm The new minute  <br>
     * @param  s The new second <br>
     * @throws TimeImpossibleToChangeException<br> 
     * @return void<br>
    */
	
	public void setNewDate(int y,int m,int d,int h,int mm,int s) throws TimeImpossibleToChangeException {
		Calendar myC = Calendar.getInstance();
		if(m>12 || m<0 || d<1 || d>31 || h<0 || h>24 || mm<0 || m>60 || s<0 || s>60) {
			throw new TimeImpossibleToChangeException();
		}else if(y<myC.get(Calendar.YEAR)) {
			throw new TimeImpossibleToChangeException();
		}else if(y==myC.get(Calendar.YEAR) && m<myC.get(Calendar.MONTH)) {
			throw new TimeImpossibleToChangeException();
		}else if(y==myC.get(Calendar.YEAR) && m==myC.get(Calendar.MONTH) && d<myC.get(Calendar.DAY_OF_MONTH)) {
			throw new TimeImpossibleToChangeException();
		}else if(y==myC.get(Calendar.YEAR) && m==myC.get(Calendar.MONTH) && d==myC.get(Calendar.DAY_OF_MONTH) && h<myC.get(Calendar.HOUR)) {
			throw new TimeImpossibleToChangeException();
		}else if(y==myC.get(Calendar.YEAR) && m==myC.get(Calendar.MONTH) && d==myC.get(Calendar.DAY_OF_MONTH) && h==myC.get(Calendar.HOUR) && mm<myC.get(Calendar.MINUTE)) {
			throw new TimeImpossibleToChangeException();
		}else {
			sysDate.setCustomDate(y, m-1, d);
			sysTime.setCustomTime(h, mm, s);
		}
	}
	
	/**
     * <b>Name:</b> addTurnType.<br>
     * This method add a new turn type.<br>
     * <b>pre:</b> The turnTypeList must be initialized.<br>
     * @param  name The name of the turn type. <br>
     * @param  time The duration that takes attend this turn. <br>
     * <b>pos:</b> The new turn type has been added .<br>
     * @return void<br>
    */
	public void addTurnType(String name,double time) {
		TurnType tp = new TurnType(time,name);
		turnTypeList.add(tp);
		
	}
	/**
     * <b>Name:</b> searchTurnType.<br>
     * This method search a turn type by its name.<br>
     * <b>pre:</b> The turnTypeList must be initialized.<br>
     * <b>pos:</b> The turn type has been searched and returned .<br>
     * @param  name The name of the turn type that we are looking for. <br>
     * @return tp TurnType An object TurnType <br>
    */
	public TurnType searchTurnType(String name) {
		TurnType tp = null;
		boolean val=false;
		Collections.sort(turnTypeList);
		int min=0;
		int max=turnTypeList.size()-1;
		while(min<=max && !val) {
			int mid = (min+max)/2;
			if(turnTypeList.get(mid).getName().equalsIgnoreCase(name)) {
				val=true;
				tp=turnTypeList.get(mid);
			}else if(turnTypeList.get(mid).getName().compareToIgnoreCase(name)<0) {
				min=mid+1;
			}else if(turnTypeList.get(mid).getName().compareToIgnoreCase(name)>0) {
				max=mid-1;
			}
		}
		return tp;
	}
	/**
     * <b>Name:</b> searchActualTurn.<br>
     * This method search the actual turn .<br>
     * <b>pos:</b> The actual turn has been searched .<br>
     * @return tp Turn An object Turn <br>
    */
	public Turn searchActualTurn() throws TurnNotAssignedYetException {
		Turn tp=null;
		boolean val =false;
		String t = getListTurn();
		for(int i=0;i<usuarios.size() && !val;i++) {
			if(usuarios.get(i).getTurn().getTurn().equalsIgnoreCase(t)) {
				tp=usuarios.get(i).getTurn();
				val=true;
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
				Collections.sort(usuarios, new UserNameComparator());
				for(int i =0;i<usuarios.size();i++) {
					 if(usuarios.get(i).getId().equalsIgnoreCase(id) && usuarios.get(i).getTypeId().equalsIgnoreCase(typeId)) {
						throw new UserAlreadyExistException(name,id);
					 }
				}
				
				usuarios.add(us);
				Collections.sort(usuarios,new UserNameComparator());
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
			turnList.add(getActualTurn());
			advanceTurn();
		}
		
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
		if(indexTurn>=turnList.size()) {
			t= "Ran out of shifts";
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
		boolean val=false;
		String actualT= getListTurn();
		if(usuarios.isEmpty()) {
			throw new TurnNotAssignedYetException(actualT);
		}else {
			for(int i=0;i<usuarios.size() ;i++) {
				if(usuarios.get(i).getTurn()!= null ) {
					if(usuarios.get(i).getTurn().getTurn().equalsIgnoreCase(actualT)) {
						if(attend) {
							usuarios.get(i).getTurn().setStatus(Turn.ATENDIDO);
							msg="The user "+usuarios.get(i).getName()+" "+usuarios.get(i).getLastname()+" was attended in the turn "+turnList.get(indexTurn)+"\n";
							indexTurn++;
							
						}else {
							usuarios.get(i).getTurn().setStatus(Turn.NO_ESTABA);
							msg="The user "+usuarios.get(i).getName()+" "+usuarios.get(i).getLastname()+" was not here to be attended \n";
							indexTurn++;
							
						}
					}
						
				}
					
			}
		}
		
		return msg;
	}
	/**
     * <b>Name:</b> userReport.<br>
     * This method generate a report of all the turns that one user ever had.<br>
     * <b>pos:</b> The report has been generated.<br>
     * @param  id String The user ID. <br>
     * @param  typeID String The user type ID. <br>
     * @throws UserNotFoundException<br> 
     * @return msg String Text chain with the user turns<br>
    */
	public String UserReport(String id,String typeID) throws UserNotFoundException {
		String msg ="";
		msg = searchUser(id,typeID).getListOfTurn();
		return msg;
	}
	
	public String showUser(String typeId,String Id) throws UserNotFoundException {
		String msg= "";
		msg =searchUser(Id,typeId).toString();
		return msg;
	}
	
	
}
