package csc573.peer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import csc573.common.Helper;
import csc573.common.Properties;
import csc573.common.peer.RFC;
import csc573.common.peer.RFCList;

public class PeerRequestProcessor {

	public static String process(String request) {
		String[] lines = null;
		if(request.indexOf("\r\n")>-1)
			lines = request.split("\r\n");
		else if(request.indexOf("\n")>-1)
			lines = request.split("\n");
		else if(request.indexOf("\r")>-1)
			lines = request.split("\r");
		else
			lines = request.split("");
		
		String[] words = lines[0].split(" ");
		if(!Properties.P2P_VALID_METHOD.contains(words[0]))
			return getErrorResponse(400);
		else if(words[0].equals("GET")){
			return processGetRequest(lines,words);
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

			return Properties.VERSION+" "+errorCode+" "+errorCodeMap.get(errorCode)+"\r\n";
	}
	
	private static String processGetRequest(String[] lines, String[] words){
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
			String OS = null;
			if(lines.length<4)
				return getErrorResponse(400);
			else{
				for(int i = 1;i<lines.length;i++){
					String line = lines[i];
					if(line.startsWith("Host:")){
						hostname = line.substring(5).trim();
					}
					else if(line.startsWith("OS:")){
						OS = line.substring(6).trim();
					}
					else if(line.trim().length()>0)
						return getErrorResponse(400);
				}
				RFC rfc = RFCList.lookup(RFCNumber);
				if(rfc==null)
					return getErrorResponse(404);
				else
					return getRFCResponse(rfc);
			}

		}
	}

	private static String getRFCResponse(RFC rfc) {
		StringBuffer responseBuffer = new StringBuffer();
		File file = new File(Peer.path+File.separator+rfc.getNumber()+"-"+rfc.getTitle());
		if(!file.exists())
			return getErrorResponse(404);
		
		byte[] data;
		try {
			data = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return getErrorResponse(404);
		}
		String content = new String(data);
		//Date: Wed, 12 Feb 2009 15:12:05 GMT 
		SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
		String date = dateFormat.format(new Date());
		String modifiedDate = dateFormat.format(new Date(file.lastModified()));
		responseBuffer.append(Properties.VERSION+" 200 OK\r\n");
		responseBuffer.append("Date: "+date+"\r\n");
		responseBuffer.append("OS: "+Peer.os+"\r\n");
		responseBuffer.append("Last Modified: "+modifiedDate+"\r\n");
		responseBuffer.append("Content-Length: "+content.length()+"\r\n");
		responseBuffer.append("Content-Type: text/text\r\n");
		responseBuffer.append(content);
		return responseBuffer.toString();
	}
	
	

}
