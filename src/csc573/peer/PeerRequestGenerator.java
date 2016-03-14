package csc573.peer;

import csc573.common.Properties;
import csc573.common.peer.RFC;

public class PeerRequestGenerator {
	public static String generateAddRequest(int RFCNumber,String title){
		StringBuffer buffer = new StringBuffer();
		buffer.append("ADD RFC "+RFCNumber+" "+Properties.VERSION+"\r\n");
		buffer.append("Host: "+Peer.hostname+"\r\n");
		buffer.append("Port: "+Peer.port+"\r\n");
		buffer.append("Title: "+title+"\r\n");
		return buffer.toString();
	}
	
	public static String generateAddRequest(RFC rfc){
		return generateAddRequest(rfc.getNumber(), rfc.getTitle());
	}
	
	public static String generateLookupRequest(int RFCNumber,String title){
		StringBuffer buffer = new StringBuffer();
		buffer.append("LOOKUP RFC "+RFCNumber+" "+Properties.VERSION+"\r\n");
		buffer.append("Host: "+Peer.hostname+"\r\n");
		buffer.append("Port: "+Peer.port+"\r\n");
		buffer.append("Title: "+title+"\r\n");
		return buffer.toString();
	}

	public static String generateListRequest(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("LIST ALL "+Properties.VERSION+"\r\n");
		buffer.append("Host: "+Peer.hostname+"\r\n");
		buffer.append("Port: "+Peer.port+"\r\n");
		return buffer.toString();
	}

	public static String generateGetRequest(int rfc) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("GET RFC "+rfc+" "+Properties.VERSION+"\r\n");
		buffer.append("Host: "+Peer.hostname+"\r\n");
		buffer.append("OS: "+Peer.os+"\r\n");
		return buffer.toString();
	}

}
