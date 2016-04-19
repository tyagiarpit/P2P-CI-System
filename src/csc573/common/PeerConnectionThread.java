package csc573.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import csc573.common.peer.RFC;
import csc573.common.peer.RFCList;
import csc573.peer.Peer;
import csc573.peer.PeerRequestGenerator;

public class PeerConnectionThread implements Runnable {
	private Socket clientSocket = null;
	private String rfcPath = null;
	private String serverHostname = null;
	private int serverPort = 0;
	
	public PeerConnectionThread(Socket clientSocket, String serverHostname, int serverPort, String path) {
		this.clientSocket = clientSocket;
		this.serverHostname = serverHostname;
		this.serverPort = serverPort;
		this.rfcPath = path;
	}

	@Override
	public void run() {
		InputStream input = null;
		OutputStream output = null;
		try {
			loadCurrentRFCs();
			clientSocket = new Socket(serverHostname,serverPort);
			output = clientSocket.getOutputStream();
			input = clientSocket.getInputStream();
			
			for (RFC rfc : RFCList.RFCEntries) {
				byte[] buffer = new byte[2048];
				String request = PeerRequestGenerator.generateAddRequest(rfc);
				
				output.write(request.getBytes());
				output.flush();
				
				input.read(buffer);

				String response = new String(buffer);

				System.out.println(response);
				Thread.sleep(100);
			}
			
			Scanner scan = new Scanner(System.in);
			String request = null;
			do{
				System.out.println("1. List All");
				System.out.println("2. Lookup RFC");
				System.out.println("3. Get RFC");
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				int i = scan.nextInt();
				if(i==0)
					System.exit(0);
				if(i==1){
					request = PeerRequestGenerator.generateListRequest();
					output.write(request.getBytes());
					output.flush();
					
					byte[] buffer = new byte[4096];
					input.read(buffer);
					
					String response = new String(buffer);
					
					System.out.println(response);
				}
				else if(i==2){
					System.out.print("Enter RFC Number: ");
					int rfc = scan.nextInt();
					request = PeerRequestGenerator.generateLookupRequest(rfc, "Unknown");
					output.write(request.getBytes());
					output.flush();
					
					byte[] buffer = new byte[4096];
					input.read(buffer);
					
					String response = new String(buffer);
					
					System.out.println(response);
				}
				else if(i==3){
					System.out.print("Enter RFC Number: ");
					int rfcnumber = scan.nextInt();
					System.out.print("Enter RFC Title: ");
					scan.nextLine();
					String title = scan.nextLine();
					System.out.print("Enter Hostname: ");
					String hostname = scan.nextLine();
					System.out.print("Enter Port: ");
					int port = scan.nextInt();
					
					Socket tempSocket = new Socket(hostname,port);
					OutputStream t_output = tempSocket.getOutputStream();
					InputStream t_input = tempSocket.getInputStream();
					
					request = PeerRequestGenerator.generateGetRequest(rfcnumber);
					t_output.write(request.getBytes());
					t_output.flush();
					
					byte[] buffer1 = new byte[20480];
					t_input.read(buffer1);
					
					String response1 = new String(buffer1);
					
					System.out.println(response1);
					
					tempSocket.close();
					t_output.close();
					t_input.close();
					
					if(response1.startsWith("P2P-CI/1.0 200 OK"))
					{
						RFC rfc = new RFC(rfcnumber,title);
						RFCList.addRFC(rfc);
						request = PeerRequestGenerator.generateAddRequest(rfc);
						output.write(request.getBytes());
						output.flush();
						
						byte[] buffer = new byte[4096];
						input.read(buffer);
						
						String response = new String(buffer);
						
						System.out.println(response);
						
						PrintWriter out = new PrintWriter(Peer.path+File.separator+rfc.getNumber()+"-"+rfc.getTitle());
						
						String content = response1.substring(response1.indexOf("Content-Type:"));
						content = content.substring(content.indexOf("\r\n")+2);
						
						out.print(content);
						out.flush();
						out.close();
					}
					
					
				}
				
				

			}
			while(true);
			
		} catch (Exception e) {
			System.exit(1);
		}
		finally{
			try {
				input.close();
				output.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void loadCurrentRFCs() throws IOException{
		File directory = new File(rfcPath);
		File[] files = directory.listFiles();
		for (File file : files) {
			String name = file.getName();
			if(name.indexOf('-')<0)
				continue;
			int number = Helper.toInt(name.substring(0, name.indexOf('-')));
			String title = name.substring(name.indexOf('-')+1);
			RFCList.addRFC(new RFC(number,title));
		}
	}

}
