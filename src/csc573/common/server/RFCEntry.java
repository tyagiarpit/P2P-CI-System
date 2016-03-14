package csc573.common.server;

public class RFCEntry {
	private int number;
	private String title;
	private String hostname;
	private int port;
	
	public RFCEntry(int number, String title, String hostname, int port) {
		super();
		this.number = number;
		this.title = title;
		this.hostname = hostname;
		this.port = port;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		return "RFC "+number+" "+title+" "+hostname+ " "+port;
	}
	
}
