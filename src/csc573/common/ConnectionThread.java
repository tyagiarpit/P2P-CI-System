package csc573.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import csc573.common.server.PeerList;
import csc573.common.server.RFCIndex;
import csc573.peer.PeerRequestProcessor;
import csc573.server.ServerRequestProcessor;

public class ConnectionThread implements Runnable {
	private Socket clientSocket = null;
	private String peerHostname = null;
	private int peerPort = 0;

	public ConnectionThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = clientSocket.getInputStream();
			output = clientSocket.getOutputStream();
			do{
				byte[] buffer = new byte[2048];
				input.read(buffer);

				String request = new String(buffer);
				
				Logger.info("\n"+request+"\n");
				
				String response = null;
				if(Start.isServer){
					response = ServerRequestProcessor.process(request);
					if(peerHostname==null && response.startsWith("P2P-CI/1.0 200 OK")){
						peerHostname = ServerRequestProcessor.getHostName(request);
						peerPort = ServerRequestProcessor.getPort(request);
					}
				}
				else
					response = PeerRequestProcessor.process(request);

				output.write(response.getBytes());
				output.flush();
			}
			while(true);


		} catch (Exception e) {
			if(Start.isServer){
				PeerList.removePeer(peerHostname,peerPort);
				RFCIndex.removePeerEntries(peerHostname, peerPort);
				System.out.println("Removed Peer "+peerHostname+":"+peerPort+"\n");
			}
		}
		finally{
			try {
				input.close();
				output.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

}
