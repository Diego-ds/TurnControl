package model;

import java.time.LocalDate;

public class Date {
	private LocalDate actualDate;
	public Date() {
		actualDate= LocalDate.now();
	}
	public LocalDate getActualDate() {
		return actualDate;
	}
	public void setActualDate(LocalDate actualDate) {
		this.actualDate = actualDate;
	}
	
}
