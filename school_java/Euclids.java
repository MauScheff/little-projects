/**
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Subject: Euclid's Implementation
 */


import java.util.*;
import java.io.*;

public class Euclids {

    private static Scanner stdin = new Scanner(new BufferedInputStream(System.in));
    
    public static void main(String[] args){
	System.out.println("Please enter first number");
	int a = stdin.nextInt();
	stdin.nextLine();
	System.out.println("Please enter second number");
	int b = stdin.nextInt();
	stdin.nextLine();
	System.out.println("Recursive? (y, n)");
	String option = stdin.nextLine();
	if (option.equalsIgnoreCase("y")) System.out.println(euclidsRecursive(a, b)); 
	else System.out.println(euclids(a, b));
    }
    
    private static int euclids(int a, int b){
	int temp = 0;
	while (b != 0){
	    temp = b;
	    b = a % b;
	    a = temp;
	}
	return a;
    }

    private static int euclidsRecursive(int a, int b){
	if (b == 0) return a;
	else return euclidsRecursive(b, a % b);
    }
}