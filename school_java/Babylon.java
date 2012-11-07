/**
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab01
 */

import java.util.*;

public class Babylon {
    
    /*
     * Global Variables
     */
    private static Scanner stdin = new Scanner(System.in);
    
    /*
     * Entry Point Method
     */
    public static void main(String[] args){
	
	while (true) {
	    int number = stdin.nextInt();
	    if (number == 0) break;
	    String result = convert(number);
	    System.out.println("{" + result.substring(0, result.length()-1) + "}"); //Remove leading comma.
	}
    }

    /*
     * Convert number to base sixty
     */
    private static String convert(int number){
	int x = 0;
	String result = "";
	while (number != 0) {
	    result = stringinize(number % 60) + "," + result;
	    number /= 60;
	}
	return result;
    }

    /*
     * Convert number to Babyolian-like symbols
     */
    private static String stringinize(int number){
	String result = "";
	int ones = number % 10;
	int tens = number / 10;

	while ( tens != 0 ){
	    result += "<";
	    tens--;
	}

	while ( ones != 0 ){
	    result += "^";
	    ones--;
	}

	return result;
    }
}