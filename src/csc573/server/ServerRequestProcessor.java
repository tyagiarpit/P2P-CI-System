package csc573.server;

import java.util.HashMap;

import csc573.common.Helper;
import csc573.common.Properties;
import csc573.common.server.PeerList;
import csc573.common.server.RFCEntry;
import csc573.common.server.RFCIndex;

public class ServerRequestProcessor {

	public static String process(String request) {
		String[] lines = request.split("\r\n");
		String[] words = lines[0].split(" ");
		if(!Properties.P2S_VALID_METHOD.contains(words[0]))
			return getErrorResponse(400);
		else if(words[0].equals("ADD")){
			processAddRequest(lines,words);
		}
		else if(words[0].equals("LIST")){
			processListRequest(lines,words);
		}
		else if(words[0].equals("LOOKUP")){
			processLookupRequest(lines,words);
		}

		return getErrorResponse(400);
	}

	private static String getErrorResponse(int errorCode){
		HashMap<Integer, String> errorCodeMap = new HashMap<Integer, String>(){/**
		 * 
		 */
			private static final long serialVersionUID = -2626419425480129186L;

			{
				put(400,"Bad Request");
				put(404,"Not Found");
				put(505,"P2P-CI Version Not Supported ");
			}};

			return Properties.VERSION+" "+errorCode+" "+errorCodeMap.get(errorCode);
	}

	private static String processAddRequest(String[] lines, String[] words){
		if(words.length!=4)
			return getErrorResponse(400);
		else if(!words[1].equals("RFC"))
			return getErrorResponse(400);
		else if(Helper.toInt(words[2])==-1)
			return getErrorResponse(400);
		else if(words[3].startsWith("P2P-CI/")&&!words[3].endsWith("1.0"))
			return getErrorResponse(505);
		else if(!words[3].startsWith("P2P-CI"))
			return getErrorResponse(400);
		else{
			int RFCNumber = Helper.toInt(words[2]);
			String hostname = null;
			int port = 0;
			String title = null;
			if(lines.length!=4)
				return getErrorResponse(400);
			else{
				for(int i = 1;i<lines.length;i++){
					String line = lines[i];
					if(line.startsWith("Host:")){
						hostname = line.substring(5).trim();
					}
					else if(line.startsWith("Port:")){
						port = Helper.toInt(line.substring(5).trim());
						if(port==-1)
							return getErrorResponse(400);
					}
					else if(line.startsWith("Title:")){
						title = line.substring(6).trim();
					}
					else
						return getErrorResponse(400);
				}
				PeerList.addPeer(hostname, port);
				RFCIndex.addRFCEntry(new RFCEntry(RFCNumber, title, hostname));
				return "200 OK "+Properties.VERSION;
			}

		}
	}
	
	private static String processLookupRequest(String[] lines, String[] words){
		if(words.length!=4)
			return getErrorResponse(400);
		else if(!words[1].equals("RFC"))
			return getErrorResponse(400);
		else if(Helper.toInt(words[2])==-1)
			return getErrorResponse(400);
		else if(words[3].startsWith("P2P-CI/")&&!words[3].endsWith("1.0"))
			return getErrorResponse(505);
		else if(!words[3].startsWith("P2P-CI"))
			return getErrorResponse(400);
		else{
			int RFCNumber = Helper.toInt(words[2]);
			String hostname = null;
			int port = 0;
			String title = null;
			if(lines.length!=4)
				return getErrorResponse(400);
			else{
				for(int i = 1;i<lines.length;i++){
					String line = lines[i];
					if(line.startsWith("Host:")){
						hostname = line.substring(5).trim();
					}
					else if(line.startsWith("Port:")){
						port = Helper.toInt(line.substring(5).trim());
						if(port==-1)
							return getErrorResponse(400);
					}
					else if(line.startsWith("Title:")){
						title = line.substring(6).trim();
					}
					else
						return getErrorResponse(400);
				}
				PeerList.addPeer(hostname, port);
				RFCIndex.addRFCEntry(new RFCEntry(RFCNumber, title, hostname));
				return "200 OK "+Properties.VERSION;
			}

		}
	}
	
	private static String processListRequest(String[] lines, String[] words){
		if(words.length!=4)
			return getErrorResponse(400);
		else if(!words[1].equals("RFC"))
			return getErrorResponse(400);
		else if(Helper.toInt(words[2])==-1)
			return getErrorResponse(400);
		else if(words[3].startsWith("P2P-CI/")&&!words[3].endsWith("1.0"))
			return getErrorResponse(505);
		else if(!words[3].startsWith("P2P-CI"))
			return getErrorResponse(400);
		else{
			int RFCNumber = Helper.toInt(words[2]);
			String hostname = null;
			int port = 0;
			String title = null;
			if(lines.length!=4)
				return getErrorResponse(400);
			else{
				for(int i = 1;i<lines.length;i++){
					String line = lines[i];
					if(line.startsWith("Host:")){
						hostname = line.substring(5).trim();
					}
					else if(line.startsWith("Port:")){
						port = Helper.toInt(line.substring(5).trim());
						if(port==-1)
							return getErrorResponse(400);
					}
					else if(line.startsWith("Title:")){
						title = line.substring(6).trim();
					}
					else
						return getErrorResponse(400);
				}
				PeerList.addPeer(hostname, port);
				RFCIndex.addRFCEntry(new RFCEntry(RFCNumber, title, hostname));
				return "200 OK "+Properties.VERSION;
			}

		}
	}

}
