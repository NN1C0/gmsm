package me.n1c0.gmsm.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {
	
	public void run(){
		try {
		String clientSentence;
        String capitalizedSentence;
        ServerSocket welcomeSocket = new ServerSocket(6789);

        while(!Thread.currentThread().isInterrupted())
        {
        	Socket connectionSocket = welcomeSocket.accept();
        	BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        	DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        	clientSentence = inFromClient.readLine();
        	System.out.println("Received: " + clientSentence);
        	capitalizedSentence = clientSentence.toUpperCase() + '\n';
        	outToClient.writeBytes(capitalizedSentence);        	
        }
        } catch (Exception e){
        	e.printStackTrace();
        }
	}
}


