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
	
}
