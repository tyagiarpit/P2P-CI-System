package csc573.common.peer;

import java.util.LinkedList;

public class RFCList {
private static final LinkedList<RFC> RFCEntries = new LinkedList<RFC>();
	
	public static synchronized void addRFC(RFC entry){
		RFCEntries.add(entry);
	}
	
	public static synchronized void removeRFC(RFC entry){
		RFCEntries.remove(entry);
	}
	
	public static synchronized void removeRFC(int rfcNumber){
		RFC temp = null;
		for (RFC entry : RFCEntries) {
			if(entry.getNumber()==rfcNumber)
				temp = entry;
		}
		RFCEntries.remove(temp);
	}
	
	public static synchronized RFC lookup(int number){
		for (RFC entry : RFCEntries) {
			if(entry.getNumber()==number)
				return entry;
		}
		return null;
	}
	
	public static void display(){
		for (RFC entry : RFCEntries) {
			System.out.println(entry);
		}
	}
	
}
