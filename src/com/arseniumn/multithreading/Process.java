package com.arseniumn.multithreading;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Process {
	
	private long numSteps=0;
	private double sum = 0.0;
	private double step;
	private long block=0;
	private static ReentrantLock lock = new ReentrantLock();
	private static ArrayList<Double> allSums = new ArrayList<Double>();
	
	public Process(long numSteps) {
		super();
		this.numSteps = numSteps;
		this.step = 1.0/(double)numSteps;
	}
	
	public double calculatePi(int threadsNum){
		this.block = numSteps / threadsNum;
		Worker[] threads = new Worker[threadsNum];
		long j=0;
		for(int i=0;i<threadsNum;i++){
			// j is the rank 
			threads[i] = new Worker(i,j,block,step,allSums);
			// increasing j by block
			j += block;
		}
		// starting threads
		for(int i=0;i<threadsNum;i++)
			threads[i].start();		
		// waiting for threads to finish their job
		for(int i=0;i<threadsNum;i++){
			try{
				threads[i].join();
			}
			catch(InterruptedException e){
				System.out.println("Thread "+i+" interrupted");
			}
		}
		// adding all sums from each thread to 1 sum
		for(int i=0;i<threadsNum;i++)
			sum += allSums.get(i);		
		double pi = sum*step;
		return pi;
	}

	public static ArrayList<Double> getAllSums() {
		return allSums;
	}

	public static void setAllSums(ArrayList<Double> allSums) {
		Process.allSums = allSums;
	}

	public static ReentrantLock getLock() {
		return lock;
	}
}
