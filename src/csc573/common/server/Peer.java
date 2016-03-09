package csc573.common.server;

public class Peer {
	private String hostname;
	private int port;
	
	public Peer(String hostname, int port) {
		super();
		this.hostname = hostname;
		this.port = port;
	}
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public String toString(){
		return "PEER "+hostname + " " + port;
	}
	
}
