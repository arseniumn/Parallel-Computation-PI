package com.arseniumn.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
	
	private static final String HOST = "localhost";
	private static final int PORT = 1234;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException,ClassNotFoundException {
		
		Socket socketConnection = new Socket(HOST,PORT);	
		ObjectInputStream clientIn = new ObjectInputStream(socketConnection.getInputStream());
		ObjectOutputStream clientOut = new ObjectOutputStream(socketConnection.getOutputStream());		
		Request req = (Request)clientIn.readObject();		
		double sum = 0.0;
		double step = req.getStep();
		int index = req.getIndex();
		long block = req.getBlock();
		long rank = req.getRank();		
		for (long i = rank;i < block*(index+1);i++) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);           
        }		
		Reply rep = new Reply(sum);
		clientOut.writeObject(rep);				
	}
}