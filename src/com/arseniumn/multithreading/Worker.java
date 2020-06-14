package com.arseniumn.multithreading;

import java.util.ArrayList;

public class Worker extends Thread{
	
	private long index;
	private long rank;
	private long block;
	private double step;
	private ArrayList<Double> list = new ArrayList<Double>();
	
	public Worker(long index,long rank, long block,double step,ArrayList<Double> list) {
		super();
		this.index = index;
		this.rank = rank;
		this.block = block;
		this.step = step;
		this.list = list;
	}

	@Override
	public void run() {
		double sum=0;
		for (long i = rank;i < block*(index+1);i++) {
            double x = ((double)i+0.5)*step;
            sum += 4.0/(1.0+x*x);
            
        }
		Process.getLock().lock();
		list.add(sum);
		Process.getLock().unlock();	
	}	
}