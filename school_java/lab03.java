   /*
    * Author:  Mauricio Zepeda, mzepedas@fit.edu
    * Course:  CSE 1002, Section 01, Spring 2008
    * Project: Lab 03, Dividing by Prime Numbers
    */

import java.util.*;
public class lab03 {
	public static void main(String args[]){
		Scanner keyboard = new Scanner(System.in);
		while(true){
			int upperLimit=0, lowerLimit=0;
			
			System.out.print("Enter maximum number N (where N >= 30, 0 to exit):");
			upperLimit = keyboard.nextInt();
			if (upperLimit==0){
				System.out.println("Exiting the program...");
				break;
			}
			else if (upperLimit < 30){
				System.out.println("Setting maximum number to 30.");
				upperLimit = 30;
			}
			
			System.out.print("Enter minimum number M (where 0 < M < N, 0 to exit):");
			lowerLimit = keyboard.nextInt();
			if (lowerLimit==0){
				System.out.println("Exiting the program...");
				break;
			}
			else if (lowerLimit < 0 || lowerLimit >= upperLimit){
				System.out.println("Setting the minimum number to 1.");
				lowerLimit = 1;
			}
			System.out.println("Checking numbers in the range " + lowerLimit + "..." + upperLimit + "\n");
			
			System.out.println("\tPrime" + "\tCount" );
			
			lab03.printRow(2, lab03.primeCounter(2, upperLimit, lowerLimit));
			lab03.printRow(3, lab03.primeCounter(3, upperLimit, lowerLimit));
			lab03.printRow(5, lab03.primeCounter(5, upperLimit, lowerLimit));
			lab03.printRow(7, lab03.primeCounter(7, upperLimit, lowerLimit));
			lab03.printRow(11, lab03.primeCounter(11, upperLimit, lowerLimit));
			lab03.printRow(13, lab03.primeCounter(13, upperLimit, lowerLimit));
			lab03.printRow(17, lab03.primeCounter(17, upperLimit, lowerLimit));
			lab03.printRow(19, lab03.primeCounter(19, upperLimit, lowerLimit));
			lab03.printRow(23, lab03.primeCounter(23, upperLimit, lowerLimit));
			lab03.printRow(29, lab03.primeCounter(29, upperLimit, lowerLimit));
			
			System.out.println("");
		}
	}
	
	private static int primeCounter(int prime, int upperLimit, int lowerLimit){
		int count=0;
		for(int i = lowerLimit;i<=upperLimit;i++){
			if (i%prime==0){
				count++;
			}
		}
		return count;
	}
	
	private static void printRow(int prime, int count){
		if (count>0){
			System.out.println("\t" + prime + "\t" + count);
		}
	}
}
