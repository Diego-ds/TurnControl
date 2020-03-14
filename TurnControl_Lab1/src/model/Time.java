package model;

import java.util.Calendar;

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
		this.hour = myC.get(myC.get(Calendar.HOUR));
		this.minute = myC.get(myC.get(Calendar.MINUTE));
		this.seconds = myC.get(myC.get(Calendar.SECOND));
		this.hourDiff = 0;
		this.minDiff = 0;
		this.secDiff = 0;
		change=false;
	}
	public String getTime() {
		updateTime();
		String msg="";
		if(!change) {
			msg=hour+":"+minute+":"+seconds;
		}else {
			msg=hour+hourDiff+":"+minute+minDiff+":"+seconds+secDiff;
		}
		return msg;
	}
	
	
	public void setCustomTime(int h,int m,int s) {
		updateTime();
		hourDiff=hour-h;
		minDiff=minute-m;
		secDiff=seconds-s;
		change=true;
	}
	
	public void updateTime() {
		Calendar myC = Calendar.getInstance();
		this.hour = myC.get(myC.get(Calendar.HOUR));
		this.minute = myC.get(myC.get(Calendar.MINUTE));
		this.seconds = myC.get(myC.get(Calendar.SECOND));
	}
	
	
	
	
	
}
