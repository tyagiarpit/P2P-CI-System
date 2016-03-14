package csc573.common.peer;

public class RFC {
	private int number;
	private String title;
	public RFC(int number, String title) {
		super();
		this.number = number;
		this.title = title;
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
	
	
}
