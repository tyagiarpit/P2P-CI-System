package csc573.common;

import java.io.File;

import csc573.peer.Peer;
import csc573.server.Server;

public class Start {
	private static final String USAGE = "java csc573.common.Start [peer <hostname> <port> <RFC Path>|server]";
	public static boolean isServer = false;
	public static void main(String[] args) {
		Logger.info("Starting...");
		if(args.length > 4 || args.length < 1)
		{
			Logger.error("Invalid number of arguments");
			System.out.println(USAGE);
			System.exit(1);
		}
		else if(!args[0].equalsIgnoreCase("peer")&&!args[0].equalsIgnoreCase("server")){
			Logger.error("Invalid value of argument");
			Logger.info("Value of argument can be 'Server' or 'Peer'");
			System.out.println(USAGE);
			System.exit(1);
		}
		else if(args[0].equalsIgnoreCase("server")){
			Server.start(Properties.SERVER_PORT);
		}
		else if(args[0].equalsIgnoreCase("peer")){
			int port = 0;
			String hostname = null;
			String path = null;
			if(args.length<4){
				Logger.error("Invalid number of arguments");
				System.out.println(USAGE);
				System.exit(1);
			}
			else{
				hostname = args[1];
				port = Helper.toInt(args[2]);
				if(port==-1){
					Logger.error("Invalid value of argument");
					Logger.info("Port should be integer");
					System.out.println(USAGE);
					System.exit(1);
				}
				path = args[3];
				File directory = new File(path);
				if(!(directory.exists() && directory.isDirectory())){
					Logger.error("Invalid value of argument");
					Logger.info("RFC Path doesnot exists");
					System.out.println(USAGE);
					System.exit(1);
				}
				
			}
			Peer.start(hostname,port, path);
		}

	}
}
