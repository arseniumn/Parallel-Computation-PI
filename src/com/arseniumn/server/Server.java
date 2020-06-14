package com.arseniumn.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
	
	private static final int PORT = 1234;
	private static final int NUMBER_OF_CLIENTS = 6;
	private static final long NUMBER_OF_STEPS = 1000000;
	private static ArrayList<Double> allSums = new ArrayList<Double>();
	private static double step = 1.0/NUMBER_OF_STEPS;
	private static ReentrantLock lock = new ReentrantLock();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException,ClassNotFoundException{
		ServerSocket socketConnection = new ServerSocket(PORT);
		Thread clients[] = new Thread[NUMBER_OF_CLIENTS];
		// Rank counts where each slice of the steps starts
		long rank = 0;
		// Block is the number of steps each client calculates the sum for
		long block = NUMBER_OF_STEPS/NUMBER_OF_CLIENTS;
				
		for(int i=0;i<NUMBER_OF_CLIENTS;i++){
			System.out.println("Server awaiting connections");
			Socket pipe = socketConnection.accept();      
			clients[i] = new Thread(new ServerThread(pipe,i,block,rank,allSums,step,lock));			
			rank += block;
		}
		long start = System.currentTimeMillis();
		for(int i=0;i<NUMBER_OF_CLIENTS;i++) 
			clients[i].start();
				
		for(int i=0;i<NUMBER_OF_CLIENTS;i++) {		
			try {
				clients[i].join();
			}
			catch(InterruptedException e) {
				System.out.println("Thread "+i+" Interrupted");
				e.printStackTrace();
			}
		}
		double sum = 0;
		for(int i=0;i<NUMBER_OF_CLIENTS;i++) 
			sum = sum +allSums.get(i);			
		double pi = sum*step;
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Estimated value of pi : "+pi);
		System.out.println("Time : "+elapsed+" ms");
	}
}
