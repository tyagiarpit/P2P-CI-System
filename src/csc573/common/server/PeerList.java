package csc573.common.server;

import java.util.LinkedList;

public class PeerList {
	private static final LinkedList<Peer> peers = new LinkedList<Peer>();
	
	public static synchronized void addPeer(String hostname, int port){
		Peer peer = lookup(hostname);
		if(peer!=null)
			peer.setPort(port);
		else{
			peer = new Peer(hostname,port);
			peers.add(peer);
		}
	}
	
	private static synchronized Peer lookup(String hostName){
		for (Peer peer : peers) {
			if(peer.getHostname().equalsIgnoreCase(hostName))
				return peer;
		}
		return null;
	}
	
	
	public synchronized void removePeer(String hostName){
		Peer temp = null;
		for (Peer peer : peers) {
			if(peer.getHostname().equalsIgnoreCase(hostName))
				temp = peer;
		}
		peers.remove(temp);
	}
	
	public static void display(){
		for (Peer peer : peers) {
			System.out.println(peer);
		}
	}
	
}
