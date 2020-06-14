package com.arseniumn.server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ServerThread implements Runnable{
	
	private Socket dataSocket;
	private int index;
	private long block;
	private long rank;
	private ArrayList<Double> allSums = new ArrayList<Double>();
	private double step;
	private ReentrantLock lock;

	public ServerThread(Socket dataSocket, int index, long block, long rank, ArrayList<Double> allSums, double step,ReentrantLock lock) {
		super();
		this.dataSocket = dataSocket;
		this.index = index;
		this.block = block;
		this.rank = rank;
		this.allSums = allSums;
		this.step = step;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream serverOutputStream = new ObjectOutputStream(dataSocket.getOutputStream());
			ObjectInputStream serverInputStream = new ObjectInputStream(dataSocket.getInputStream());			
			Request req = new Request(index,block,rank,step);
			serverOutputStream.writeObject(req);			
			Reply rep = (Reply)serverInputStream.readObject();		
			lock.lock();
			this.allSums.add(rep.getSum());
			lock.unlock();    			
		}
		catch(IOException e) {
		    System.out.println("I/O Error");
		    e.printStackTrace();
		    System.exit(1);
	    }
	    catch(ClassNotFoundException c) {
		    System.out.println("Class not found");
	    }	
	}
}
