package csc573.common.server;

import java.util.concurrent.LinkedBlockingQueue;

public class RFCIndex {
	private static final LinkedBlockingQueue<RFCEntry> RFCEntries = new LinkedBlockingQueue<RFCEntry>();
	
	public static void addRFCEntry(RFCEntry entry){
		RFCEntries.add(entry);
	}
	
	public static void removeRFCEntry(RFCEntry entry){
		RFCEntries.remove(entry);
	}
	
	public static void removePeerEntries(String hostName, int port){
		for (RFCEntry entry : RFCEntries) {
			if(entry.getPort() == port && entry.getHostname().equals(hostName))
				RFCEntries.remove(entry);
		}
	}
	
	public static void display(){
		for (RFCEntry entry : RFCEntries) {
			System.out.println(entry);
		}
	}
	
	public static String listAll(){
		StringBuffer buffer = new StringBuffer();
		for (RFCEntry entry : RFCEntries) {
			buffer.append("RFC "+entry.getNumber()+" "+entry.getTitle()+" "+entry.getHostname()+" "+entry.getPort()+"\r\n");
		}
		return buffer.toString();
	}
	
	public static String listAllWithNumber(int number){
		StringBuffer buffer = new StringBuffer();
		for (RFCEntry entry : RFCEntries) {
			if(entry.getNumber()==number)
				buffer.append("RFC "+entry.getNumber()+" "+entry.getTitle()+" "+entry.getHostname()+" "+entry.getPort()+"\r\n");
		}
		return buffer.toString();
	}
	
}
