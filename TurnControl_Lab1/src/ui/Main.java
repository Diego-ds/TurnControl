package ui;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import CustomExceptions.NotTurnTypeException;
import CustomExceptions.TimeImpossibleToChangeException;
import CustomExceptions.TurnNotAssignedYetException;
import CustomExceptions.UserAlreadyExistException;
import CustomExceptions.UserAlreadyHasTurnException;
import CustomExceptions.UserNotFoundException;
import model.TurnManagement;
import model.User;

public class Main implements Serializable {
	private static final long serialVersionUID = 1L;
	TurnManagement objTurn;
	public Main() {
		objTurn= new TurnManagement();
	}
	public static void main(String[] args) {
		Main obj = new Main();
		Scanner teclado = new Scanner(System.in);
		boolean val = true;
		int option;
		
		while(val) {
			obj.showMenu();
			option=teclado.nextInt();
			switch(option) {
			case 1:
				obj.addUser();
				break;
			case 2:
				try {
					obj.addTurnType();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				obj.assignTurn();
				break;
			case 4:
				try {
					try {
						obj.attendTurnUntilNow();
					} catch (TimeImpossibleToChangeException e) {
						System.out.println(e.getMessage());
					}
				} catch (TurnNotAssignedYetException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:
				obj.showUser();
				break;
			case 6:
				try {
					obj.generateUser();
				} catch (UserAlreadyExistException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				try {
					obj.setNewDate();
				} catch (NumberFormatException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (TimeImpossibleToChangeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 8:
				try {
					obj.updateTime();
				} catch (TimeImpossibleToChangeException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 9:
				obj.getDateTime();
				break;
			case 10:
				try {
					obj.saveData();
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 11:
				try {
					obj.chargeData();
				} catch (FileNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (ClassNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 12:
				try {
					obj.UserReport();
				} catch (UserNotFoundException e) {
					System.out.println(e.getMessage());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 13:
				System.out.println("Thank you!");
				val=false;
				break;
			default:
				System.out.println("Wrong option. Please try again\n");
				
				
				
			}
		}
		

	}
	public void showMenu() {
		System.out.println("The current date is: "+objTurn.getDateTime()+"\n");
		System.out.println("Please select an option.\n");
		System.out.println("1. Add a new user.\n");
		System.out.println("2. Add a turn type.\n");
		System.out.println("3. Assign a turn to an user.\n");
		System.out.println("4. Attend turns until now.\n");
		System.out.println("5. Search and show an user.\n");
		System.out.println("6. Generate new users.\n");
		System.out.println("7. Change the date and time of the system.\n");
		System.out.println("8. Update the current date and time.\n");
		System.out.println("9. Show the date and time of the system.\n");
		System.out.println("10. Save the current data.\n");
		System.out.println("11. Charge data.\n");
		System.out.println("12. Generate a turn report of an user .\n");
		System.out.println("13. Exit.\n");
		
	}
	public void addUser() {
		Scanner teclado= new Scanner(System.in);
		System.out.println("Please the name of the user.\n");
		String name = teclado.nextLine();
		System.out.println("Please enter the lastname of the user.\n");
		String lastname = teclado.nextLine();
		System.out.println("Please select the type of ID of the user.\n");
		System.out.println("<1> Citizenship card\n<2> Identity card\n<3> Foreign citizenship card\n<4> civil registration\n ");
		String op = teclado.nextLine();	
		int op1 = Integer.parseInt(op);
		String typeId="";
		if(op1==1) {
			typeId=User.CC;
		}else if(op1==2) {
			typeId=User.TI;
		}else if(op1==3) {
			typeId=User.CE;
		}else {
			typeId=User.RC;
		}
		System.out.println("Please enter the ID of the user\n");
		String id=teclado.nextLine();
		System.out.println("Please enter the adress of the user");
		String adress = teclado.nextLine();
		System.out.println("Please enter the phone number of the user");
		String phone = teclado.nextLine();
		try {
			objTurn.addUser(typeId, id, name, lastname, phone, adress);
		} catch (UserAlreadyExistException e) {
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
			
	}
	public void assignTurn() {
		Scanner teclado= new Scanner(System.in);
		System.out.println("Please select the type of ID of the user.\n");
		System.out.println("<1> Citizenship card\n<2> Identity card\n<3> Foreign citizenship card\n<4> civil registration\n ");
		String op = teclado.nextLine();	
		int op1= Integer.parseInt(op);
		String typeId="";
		if(op1==1) {
			typeId=User.CC;
		}else if(op1==2) {
			typeId=User.TI;
		}else if(op1==3) {
			typeId=User.CE;
		}else {
			typeId=User.RC;
		}
		System.out.println("Please enter the ID\n");
		String id=teclado.nextLine();
		System.out.println("Please enter the name of the turn type\n");
		String turnType=teclado.nextLine();
		try {
			System.out.println(objTurn.assignTurn(id, typeId,turnType));
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}catch(UserAlreadyHasTurnException e) {
			System.out.println(e.getMessage());
		} catch (NotTurnTypeException e) {
			System.out.println(e.getMessage());
		}
	}
	public void generateUser() throws UserAlreadyExistException, Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("How many user do you want to create");
		int users = Integer.parseInt(br.readLine());
		objTurn.numberNewUsers(users);;
	}
	public void attendTurnUntilNow() throws TurnNotAssignedYetException, TimeImpossibleToChangeException {
		System.out.println(objTurn.attendTurnsUntilNow());
	}
	public void getDateTime() {
		System.out.println(objTurn.getDateTime());
	}
	public void updateTime() throws TimeImpossibleToChangeException {
		objTurn.updateDateTime();
	}
	public void setNewDate() throws NumberFormatException, IOException, TimeImpossibleToChangeException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the year");
		int y=Integer.parseInt(br.readLine());
		System.out.println("Please enter the number of the month (1-12) ");
		int m=Integer.parseInt(br.readLine());
		System.out.println("Please enter the day of the month");
		int d=Integer.parseInt(br.readLine());
		System.out.println("Please enter the hour");
		int h=Integer.parseInt(br.readLine());
		System.out.println("Please enter the minute");
		int mm=Integer.parseInt(br.readLine());
		System.out.println("Please enter the second");
		int s=Integer.parseInt(br.readLine());
		objTurn.setNewDate(y, m, d, h, mm, s);
		
	}
	public void addTurnType() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the name of the new type of turn");
		String name=br.readLine();
		System.out.println("Enter the duration that takes the turn");
		double time=Double.parseDouble(br.readLine());
		objTurn.addTurnType(name, time);
	}
	public void attendTurn() {
		Scanner teclado = new Scanner(System.in);
		boolean val=true;
		try {
			System.out.println("The current turn is: "+objTurn.getListTurn()+"\n");
			System.out.println("The user was: \n<1> Attended\n<2> The user was not here\n");
			String us = teclado.nextLine();
			int userAt = Integer.parseInt(us);
			if(userAt==2) {
				val=false;
			}
			System.out.println(objTurn.attendTurn(val));
			
			
		} catch (TurnNotAssignedYetException e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void showUser() {
		Scanner teclado= new Scanner(System.in);
		System.out.println("Please select the type of ID of the user.\n");
		System.out.println("<1> Citizenship card\n<2> Identity card\n<3> Foreign citizenship card\n<4> civil registration\n ");
		String op = teclado.nextLine();	
		int op1= Integer.parseInt(op);
		String typeId="";
		if(op1==1) {
			typeId=User.CC;
		}else if(op1==2) {
			typeId=User.TI;
		}else if(op1==3) {
			typeId=User.CE;
		}else {
			typeId=User.RC;
		}
		System.out.println("Please enter the ID\n");
		String id=teclado.nextLine();
		
		try {
			System.out.println(objTurn.showUser(typeId, id));
		} catch (UserNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void chargeData() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tl.txt"));
		objTurn=(TurnManagement) ois.readObject();
		ois.close();
	}
	public void saveData() throws FileNotFoundException, IOException {
		ObjectOutputStream oop = new ObjectOutputStream(new FileOutputStream("tl.txt"));
		oop.writeObject(objTurn);
		oop.close();
	}
	
	public void UserReport() throws UserNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select the type of ID of the user.\n");
		System.out.println("<1> Citizenship card\n<2> Identity card\n<3> Foreign citizenship card\n<4> civil registration\n ");
		String op = br.readLine();
		int op1= Integer.parseInt(op);
		String typeId="";
		if(op1==1) {
			typeId=User.CC;
		}else if(op1==2) {
			typeId=User.TI;
		}else if(op1==3) {
			typeId=User.CE;
		}else {
			typeId=User.RC;
		}
		System.out.println("Please enter the ID\n");
		String id=br.readLine();
		System.out.println(objTurn.UserReport(id, typeId));
	}
	

}
