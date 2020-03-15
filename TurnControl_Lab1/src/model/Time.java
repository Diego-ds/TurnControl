package model;

import java.util.Calendar;

import CustomExceptions.TimeImpossibleToChangeException;

public class Time {
	private int hour;
	private int minute;
	private int seconds;
	private int hourDiff;
	private int minDiff;
	private int secDiff;
	private boolean change;
	public Time() {
		Calendar myC = Calendar.getInstance();
		this.hour = myC.get(Calendar.HOUR);
		this.minute = myC.get(Calendar.MINUTE);
		this.seconds = myC.get(Calendar.SECOND);
		this.hourDiff = 0;
		this.minDiff = 0;
		this.secDiff = 0;
		change=false;
	}
	public String getTime() {
		String msg="";
		if(!change) {
			msg=hour+":"+minute+":"+seconds;
		}else {
			msg=hour+hourDiff+":"+minute+minDiff+":"+seconds+secDiff;
		}
		return msg;
	}
	
	
	public void setCustomTime(int h,int m,int s) {
		hourDiff=hour-h;
		minDiff=minute-m;
		secDiff=seconds-s;
		change=true;
	}
	
	public void updateTime() throws TimeImpossibleToChangeException {
		if(!change) {
			Calendar myC = Calendar.getInstance();
			this.hour = myC.get(Calendar.HOUR);
			this.minute = myC.get(Calendar.MINUTE);
			this.seconds = myC.get(Calendar.SECOND);
		}else {
			throw new TimeImpossibleToChangeException();
		}
		
	}
	
	public boolean getChange() {
		return change;
	}
	public void setChange(boolean v) {
		change=v;
	}
	public int getHour() {
		int h;
		if(change) {
			h= hour+hourDiff;
		}else {
			h=hour;
		}
		return h;
	}
	public int getMinute() {
		int m;
		if(change) {
			m=minute+minDiff;
		}else {
			m=minute;
		}
		return m;
	}
	public int getSeconds() {
		int s;
		if(change) {
			s=seconds+secDiff;
		}else {
			s=seconds;
		}
		return s;
	}
	
	
	
	
	
	
	
}
