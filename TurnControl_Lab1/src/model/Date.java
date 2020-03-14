package model;

import java.util.Calendar;

public class Date {
	private int year;
	private int month;
	private int day;
	private int yearDiff;
	private int monthDiff;
	private int dayDiff;
	private boolean change;
	public Date() {
		Calendar miC = Calendar.getInstance();
		this.year = miC.get(Calendar.YEAR);
		this.month = miC.get(Calendar.MONTH);
		this.day = miC.get(Calendar.DAY_OF_MONTH);
		this.yearDiff = 0;
		this.monthDiff = 0;
		this.dayDiff = 0;
		change=false;
	}
	
	public String getDate() {
		String msg="";
		if(!change) {
			msg= year+"/"+month+"/"+day+" ";
		}else {
			msg=year+yearDiff+"/"+month+monthDiff+"/"+day+dayDiff+" ";
		}
		
		return msg;
	}
	
	public void setCustomDate(int y,int m,int d) {
		yearDiff=year-y;
		monthDiff=month-m;
		dayDiff=day-d;
		change=true;
		
	}
	
	
}
