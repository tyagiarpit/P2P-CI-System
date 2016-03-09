package csc573.common;

import csc573.client.Client;
import csc573.server.Server;

public class Start {
	private static final String USAGE = "java csc573.common.Start [client|server]";
	public static boolean isServer = false;
	public static void main(String[] args) {
		Logger.info("Starting...");
		if(args.length!=1)
		{
			Logger.error("Invalid number of arguments");
			System.out.println(USAGE);
			Server.start(Properties.SERVER_PORT);
		}
		else if(!args[0].equalsIgnoreCase("client")&&!args[0].equalsIgnoreCase("server")){
			Logger.error("Invalid value of argument");
			Logger.info("Value of argument can be 'Server' or 'Client'");
			System.out.println(USAGE);
		}
		else if(args[0].equalsIgnoreCase("Server")){
			Server.start(Properties.SERVER_PORT);
		}
		else if(args[0].equalsIgnoreCase("Client")){
			Client.start();
		}

	}
}
