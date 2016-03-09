package csc573.common.server;

import java.util.LinkedList;

public class RFCIndex {
	private static final LinkedList<RFCEntry> RFCEntries = new LinkedList<RFCEntry>();
	
	public static synchronized void addRFCEntry(RFCEntry entry){
		RFCEntries.add(entry);
	}
	
	public static synchronized void removeRFCEntry(RFCEntry entry){
		RFCEntries.remove(entry);
	}
	
	public static synchronized void removePeer(int rfcNumber, String hostName){
		RFCEntry temp = null;
		for (RFCEntry entry : RFCEntries) {
			if(entry.getNumber()==rfcNumber && entry.getHostname().contentEquals(hostName))
				temp = entry;
		}
		RFCEntries.remove(temp);
	}
	
	public static void display(){
		for (RFCEntry entry : RFCEntries) {
			System.out.println(entry);
		}
	}
	
	public static String listAll(){
		StringBuffer buffer = new StringBuffer();
		for (RFCEntry entry : RFCEntries) {
			buffer.append("RFC "+entry.getNumber()+" "+entry.getTitle()+" "+entry.getHostname()+" "+PeerList.lookup(entry.getHostname()).getPort()+"\r\n");
		}
		return buffer.toString();
	}
	
	public static String listAllWithNumber(int number){
		StringBuffer buffer = new StringBuffer();
		for (RFCEntry entry : RFCEntries) {
			if(entry.getNumber()==number)
				buffer.append("RFC "+entry.getNumber()+" "+entry.getTitle()+" "+entry.getHostname()+" "+PeerList.lookup(entry.getHostname()).getPort()+"\r\n");
		}
		return buffer.toString();
	}
	
}
