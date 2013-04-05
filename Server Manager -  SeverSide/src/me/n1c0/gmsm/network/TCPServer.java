package me.n1c0.gmsm.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import me.n1c0.gmsm.command.CommandHandle;

public class TCPServer implements Runnable {
	
	private ServerSocket welcomeSocket = null;
	
	private boolean shutdownRequested = false;
	
	public void run(){
		try {
			List<String> clientSentence = new ArrayList<String>();
			String temp = null;
			List<String> outToClientSentence = null;
	        welcomeSocket = new ServerSocket(6789);
	
	        while(!Thread.currentThread().isInterrupted())
	        {
	        	Socket connectionSocket = welcomeSocket.accept();
	        	if(shutdownRequested) {
	        		break;
	        	}
	        	BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
	        	DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	        	for (int j = 0; j < 3; j++) {
	        		temp = null;
	        		temp = inFromClient.readLine();
	        		clientSentence.add(temp);
				}
	        	boolean acceptUser = new Authentification().checkCredentials(clientSentence.get(0), clientSentence.get(1));
	        	if(acceptUser) {
	        		outToClientSentence = new CommandHandle().commandHandle(clientSentence.get(2));
	        	}
	        	for( int i = 0; i < outToClientSentence.size(); i++) {
	        		outToClient.writeBytes(outToClientSentence.get(i) + '\n'); 
	        		System.out.println(outToClientSentence.get(i));
	        	}
	        	outToClient.writeBytes("end" + '\n');
	        }
        } catch (Exception e){
        	e.printStackTrace();
        }
	}
	
	public void killServer() {
		shutdownRequested = true;

		try {
			welcomeSocket.close();
		} catch (IOException e) {
		}
	}
}


