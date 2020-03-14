package ui;
import java.util.*;

import CustomExceptions.NotTurnTypeException;
import CustomExceptions.TurnNotAssignedYetException;
import CustomExceptions.UserAlreadyExistException;
import CustomExceptions.UserAlreadyHasTurnException;
import CustomExceptions.UserNotFoundException;
import model.TurnManagement;
import model.User;

public class Main {
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
				obj.assignTurn();
				break;
			case 3:
				obj.attendTurn();
				break;
			case 4:
				obj.showUser();
				break;
			case 5:
				System.out.println("Thank you!");
				val=false;
				break;
			default:
				System.out.println("Wrong option. Please try again\n");
				
				
				
			}
		}
		

	}
	public void showMenu() {
		System.out.println("Please select an option.\n");
		System.out.println("1. Add a new user.\n");
		System.out.println("2. Assign a turn to an user.\n");
		System.out.println("3. Attend a turn.\n");
		System.out.println("4. Search and show an user.\n");
		System.out.println("5. Exit.\n");
		
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
	

}
