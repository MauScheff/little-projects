/*
 * Author:  Mauricio Zepeda, mzepedas@fit.edu
 * Course:  CSE 1002, Section 01, Spring 2008
 * Project: Lab 01, Greatest Number of Three
 */

import java.util.*;

public class lab01 {

	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);

		int a, b, c;
		System.out.print("Please enter the first number: ");
		a = keyboard.nextInt();
		System.out.print("Please enter the second number: ");
		b = keyboard.nextInt();
		System.out.print("Please enter the third number: ");
		c = keyboard.nextInt();

		System.out.println("The smallest number is: " + Math.min(Math.min(a, b), c));

	}
}
