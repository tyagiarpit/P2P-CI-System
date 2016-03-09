package csc573.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import csc573.common.ConnectionThread;
import csc573.common.Logger;
import csc573.common.Start;

public class Server {
	private static int port;
	private static ServerSocket serverSocket = null;
    private static boolean killed = false;
	private static int requestCounter = 0;
	
	public static synchronized int requestNumber(){
		requestCounter++;
		return requestCounter;
	}
	
	public void stop() {
		killed = true;
	}

	public static void start(int serverPort) {
		Start.isServer = true;
		port = serverPort;
		try {
			serverSocket = new ServerSocket(port);
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
