package model;
import java.util.*;

public class TurnManagement {
	ArrayList <User> usuarios;
	
	public TurnManagement() {
		usuarios=new ArrayList <User>();
	}
	public void addUser(String typeId,String id,String name,String lastname,String phone,String adress) {
		User us = new User(typeId,id,name,lastname,phone,adress);
		usuarios.add(us);
	}
}
