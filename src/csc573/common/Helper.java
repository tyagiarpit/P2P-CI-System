package csc573.common;

public class Helper {
	public static int toInt(String input){
		int i = -1;
		try{
			i = Integer.parseInt(input);
		}
		catch(Exception e){
			return -1;
		}
		return i;
	}
}
