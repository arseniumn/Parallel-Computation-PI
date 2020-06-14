package com.arseniumn.server;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Request implements Serializable{
	
	private int index;
	private long block;
	private long rank;
	private double step;
	
	public Request(int index, long block, long rank, double step) {
		super();
		this.index = index;
		this.block = block;
		this.rank = rank;
		this.step = step;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public long getBlock() {
		return block;
	}

	public void setBlock(long block) {
		this.block = block;
	}

	public long getRank() {
		return rank;
	}

	public void setRank(long rank) {
		this.rank = rank;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}
}
