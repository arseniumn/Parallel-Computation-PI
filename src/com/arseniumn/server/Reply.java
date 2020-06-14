package com.arseniumn.server;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Reply implements Serializable{
	
	private double sum;

	public Reply(double sum) {
		super();
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
}