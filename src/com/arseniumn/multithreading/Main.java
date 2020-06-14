package com.arseniumn.multithreading;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		boolean correct = false;	
		long input = 0;
		System.out.println("Enter the number of steps...");
		while(!correct){
			try{
				input = in.nextLong();
				correct = true;
			}
			catch(InputMismatchException e){
				System.out.println("Input must be type long");
				System.out.println("Enter the number of steps...");
				input = in.nextLong();
			}
		}
		
		// initiating function 
		Process f = new Process(input);
		
		// number of cores used
		// int cores = Runtime.getRuntime().availableProcessors();
		int cores = 2;
		// starting timer
		long start = System.currentTimeMillis();
		// using method calculatePi from Function to calculate pi
		double pi = f.calculatePi(cores);
		// stopping timer
	        long end = System.currentTimeMillis();
	        long elapsed = end-start;
	        System.out.println("Estimated value of pi : "+pi);
	        System.out.println("Elapsed time : "+elapsed+ " ms");
	}
}
