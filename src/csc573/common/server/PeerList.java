package csc573.common.server;

import java.util.concurrent.LinkedBlockingQueue;

public class PeerList {
	private static final LinkedBlockingQueue<Peer> peers = new LinkedBlockingQueue<Peer>();
	
	public static void addPeer(String hostname, int port){
		Peer peer = lookup(hostname,port);
		if(peer==null)
		{
			peer = new Peer(hostname,port);
			peers.add(peer);
		}
	}
	
	public static Peer lookup(String hostName, int port){
		for (Peer peer : peers) {
			if(peer.getHostname().equalsIgnoreCase(hostName) && peer.getPort()==port)
				return peer;
		}
		return null;
	}
	
	
	public static void removePeers(String hostName){
		for (Peer peer : peers) {
			if(peer.getHostname().equalsIgnoreCase(hostName))
				peers.remove(peer);
		}
	}
	
	public static void removePeer(String hostName, int port){
		for (Peer peer : peers) {
			if(peer.getHostname().equalsIgnoreCase(hostName) && peer.getPort() == port)
				peers.remove(peer);
		}
	}
	
	public static void display(){
		for (Peer peer : peers) {
			System.out.println(peer);
		}
	}
	
}
