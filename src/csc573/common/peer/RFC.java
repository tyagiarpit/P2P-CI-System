package csc573.common.peer;

public class RFC {
	private int number;
	private String content;
	public RFC(int number, String content) {
		super();
		this.number = number;
		this.content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
