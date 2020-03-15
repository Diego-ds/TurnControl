package model;

public class TurnType implements Comparable <TurnType> {
	private double time;
	private String name;
	public TurnType(double time, String name) {
		this.time = time;
		this.name = name;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int compareTo(TurnType o) {
		
		return name.compareToIgnoreCase(o.getName());
	}
	
	
}
