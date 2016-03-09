package csc573.common;

import java.io.PrintStream;

public class Logger {
	private static final String ERROR 	= "[ERROR] ";  
	private static final String INFO 	= "[INFO]  ";  
	private static final String DEBUG 	= "[DEBUG] ";  
	private static PrintStream out = System.out;
	private Logger(){
		
	}
	public synchronized static void setOutputStream(PrintStream stream){
		out = stream;
	}
	private synchronized static void log(String message){
		out.println(message);
	}
	public static void debug(String message){
		log(DEBUG+message);
	}
	public static void info(String message){
		log(INFO+message);
	}
	public static void error(String message){
		log(ERROR+message);
	}
	public synchronized static void error(Exception e){
		log(ERROR+e.getMessage());
		e.printStackTrace(out);
	}
}
