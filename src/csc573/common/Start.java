package csc573.common;

import csc573.peer.Peer;
import csc573.server.Server;

public class Start {
	private static final String USAGE = "java csc573.common.Start [peer <port>|server]";
	public static boolean isServer = false;
	public static void main(String[] args) {
		Logger.info("Starting...");
		if(args.length > 2 || args.length < 1)
		{
			Logger.error("Invalid number of arguments");
			System.out.println(USAGE);
		}
		else if(!args[0].equalsIgnoreCase("peer")&&!args[0].equalsIgnoreCase("server")){
			Logger.error("Invalid value of argument");
			Logger.info("Value of argument can be 'Server' or 'Peer'");
			System.out.println(USAGE);
		}
		else if(args[0].equalsIgnoreCase("server")){
			Server.start(Properties.SERVER_PORT);
		}
		else if(args[0].equalsIgnoreCase("peer")){
			int port = 0;
			if(args.length<2){
				Logger.error("Invalid number of arguments");
				System.out.println(USAGE);
			}
			else{
				port = Helper.toInt(args[1]);
				if(port==-1){
					Logger.error("Invalid value of argument");
					Logger.info("Port should be integer");
					System.out.println(USAGE);
				}

			}
			Peer.start(port);
		}

	}
}
