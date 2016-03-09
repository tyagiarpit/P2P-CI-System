package csc573.common;

import java.util.ArrayList;

public class Properties {
	public static final int SERVER_PORT = 7734;
	public static final ArrayList<String> P2P_VALID_METHOD = new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = 2839943983770976026L;

	{
		add("GET");
	}};
	
	public static final ArrayList<String> P2P_VALID_HEADER = new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = -4005406481407305510L;

	{
		add("Host");
		add("OS");
	}};
	public static final ArrayList<String> P2S_VALID_METHOD = new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = -7836032265490190170L;

	{
		add("ADD");
		add("LOOKUP");
		add("LIST");
	}};
	
	public static final ArrayList<String> P2S_VALID_HEADER = new ArrayList<String>(){/**
		 * 
		 */
		private static final long serialVersionUID = -4812432483383855157L;

	{
		add("Host");
		add("Port");
		add("Title");
	}};
	
	public static final String VERSION = "P2P-CI/1.0";
}
