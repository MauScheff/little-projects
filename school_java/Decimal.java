/**
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab01
 */

import java.util.*;

public class Decimal {
    
    /*
     * Global Variables
     */
    private static Scanner stdin = new Scanner(System.in);
    
    /*
     * Entry Point Method
     */
    public static void main(String[] args){
	while (true){
	    String babylonian = stdin.nextLine();
	    if (babylonian.equals("{}")) break;
	    System.out.println(convert(modString(babylonian))); //Remove Brackets and convert number
	}
    }

    /*
     * Convert from base sixty to base ten
     */
    private static int convert(String babylonian){

	int result = 0;
	StringTokenizer tokenizer = new StringTokenizer(babylonian, ",");
	int power = tokenizer.countTokens()-1;
	while(tokenizer.hasMoreTokens()){
	    result += (numerize(tokenizer.nextToken())*Math.pow(60, power));
	    power--;
	}
	return result;
    }
    
    /*
     * Convert Babylonian-like number to integer value
     */
    private static int numerize(String babylonianToken){
	int result = 0;
	for(int j=0;j<babylonianToken.length();j++){
	    if (babylonianToken.charAt(j) == '<') result += 10;
	    else if (babylonianToken.charAt(j) == '^') result += 1;
	}
	return result;
    }

    /*
     * Modify String (Remove Trailing and leading brackets, 
     * and check for empty positions (two commas together), 
     * if found, replace with " " so StringTokenizer 
     * recognizes as a token.
     */
    private static String modString(String str){
	str = str.substring(1, str.length()-1);
	if(str.length()>=2){
	    for(int j=1;j<str.length();j++){
		if(str.charAt(j-1) == ',' && str.charAt(j) == ','){
		    str = str.substring(0,j) + " " + str.substring(j);
		}
	    }
	}
	return str;
    }
}