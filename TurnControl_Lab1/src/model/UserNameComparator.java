package model;

import java.util.Comparator;

public class UserNameComparator implements Comparator <User> {

	@Override
	public int compare(User arg0, User arg1) {
		int rt=0;
		if(arg0.getName().compareToIgnoreCase(arg1.getName())>0) {
			rt=1;
		}else if(arg0.getName().compareToIgnoreCase(arg1.getName())<0) {
			rt=-1;
		}
		return rt;
	}
	
}
