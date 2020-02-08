package model;

public class User {
	private String typeId;
	private String id;
	private String name;
	private String lastname;
	private String phone;
	private String adress;
	public static final String CC ="CEDULA DE CIUDADANIA";
	public static final String TI ="TARJETA DE IDENTIDAD";
	public static final String CE ="CEDULA DE EXTRANJERIA";
	public static final String RC ="REGISTRO CIVIL";
	Turn turn;
	public User(String typeId,String id,String name,String lastname,String phone,String adress) {
		this.typeId=typeId;
		this.id=id;
		this.name=name;
		this.lastname=lastname;
		this.phone=phone;
		this.adress=adress;
		turn=null;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public void setTurn(String turn,String status) {
		this.turn = new Turn(turn,status);
	}
}
