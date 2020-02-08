package model;
import java.util.*;
import CustomExceptions.*;

public class TurnManagement {
	ArrayList <User> usuarios;
	
	public TurnManagement() {
		usuarios=new ArrayList <User>();
	}
	public void addUser(String typeId,String id,String name,String lastname,String phone,String adress) throws UserAlreadyExistException {
		User us = new User(typeId,id,name,lastname,phone,adress);
		boolean val=false;
			if(usuarios.isEmpty()) {
				usuarios.add(us);
			}else {
				for(int i =0;i<usuarios.size() && !val;i++) {
					 if(usuarios.get(i).getId().equalsIgnoreCase(id)) {
						 val=true;
						throw new UserAlreadyExistException(name,id);
					 }
				}
			}
			
			if(!val) {
				usuarios.add(us);
			}
	}
	public User searchUser(String id) throws UserNotFoundException {
		User obj = null;
		if(usuarios.isEmpty()) {
			throw new UserNotFoundException(id);
		 }else{
			 for(int i=0;i<usuarios.size();i++) {
				 if(usuarios.get(i).getId().equalsIgnoreCase(id)) {
					obj=usuarios.get(i);
				 }
			 }
		}			
		if(obj==null) {
			throw new UserNotFoundException(id);
		}
		
		return obj;
		
	}
}
