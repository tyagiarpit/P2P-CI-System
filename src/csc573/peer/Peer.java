package csc573.peer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import csc573.common.ConnectionThread;
import csc573.common.Logger;
import csc573.common.PeerConnectionThread;

public class Peer {
	public static int port;
	public static String hostname;
	public static String os;
	public static String path;
	private static ServerSocket serverSocket = null;
	private static Socket peerSocket = null;
	private static boolean killed = false;

	public void stop() {
		killed = true;
	}

	public static void start(String serverHostname, int serverPort, String rfcpath){
		port = 0;
		path = rfcpath;
		try {
			port = (int) (20000+(Math.random()*1000));
			serverSocket = new ServerSocket(port);
			hostname = InetAddress.getLocalHost().getHostName();
			os = System.getProperty("os.name");
			peerSocket = new Socket();
			new Thread(new PeerConnectionThread(peerSocket,serverHostname,serverPort, path)).start();
		} catch (IOException e) {
			Logger.error(e);
			System.exit(1);
		}
		while(!killed){
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				if(killed) {
					System.out.println("Server Stopped.") ;
					return;
				}
				throw new RuntimeException(
						"Error accepting client connection", e);
			}
			new Thread(new ConnectionThread(clientSocket)).start();
		}
	}

}
