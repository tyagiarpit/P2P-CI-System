package csc573.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import csc573.peer.PeerRequestProcessor;
import csc573.server.ServerRequestProcessor;

public class ConnectionThread implements Runnable {
	private Socket clientSocket = null;
	
	public ConnectionThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			byte[] buffer = new byte[2048];
			InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            input.read(buffer);
            
            String request = new String(buffer);
            
            System.out.println(request);
            
            String response = null;
            if(Start.isServer)
            	response = ServerRequestProcessor.process(request);
            else
            	response = PeerRequestProcessor.process(request);
            	
            output.write(response.getBytes());
            
            input.close();
            output.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
