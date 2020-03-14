package model;

import java.time.LocalTime;

public class Time {
	private LocalTime actualTime;
	public Time() {
		actualTime=LocalTime.now();
	}
	public LocalTime getActualTime() {
		return actualTime;
	}
	public void setActualTime(LocalTime actualTime) {
		this.actualTime = actualTime;
	}
	
}
