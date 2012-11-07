/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 1002, Section 01, Fall 2007
 * Project: Lab 09, Catching Exceptions
 */
import java.io.*;
import java.util.*;

public class Lab09 {

	public static void main(String[] args) {
		System.out.println("CSE 1002 Lab 9 - Mauricio Zepeda");
		Scanner fileRead = null;
		int[] numbers = new int[9];
		int posCounter = 0;

		try {
			fileRead = new Scanner(new File(args[0]));
			System.out.println("\nFile: " + args[0] + " opened.\n");
		} catch (FileNotFoundException fnf) {
			System.out.println("\n\t*** File Not Found: " + args[0]
					+ " (No such file or directory)");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("\n\tUnknown error!\n");
		}

		while (fileRead.hasNextLine()) {
			String line = fileRead.nextLine();
			try {
				if (Character.isDigit(line.charAt(0))) {
					int number = 0;
					try {
						number = Integer.parseInt(line);
						numbers[posCounter] = number;
						posCounter++;
					} catch (NumberFormatException e) {
						System.out
								.println("\t*** Input not an Integer: For input string: \""
										+ line + "\"\n");
					} catch (Exception e) {
						System.out.println("\n\tUnknown error!\n");
					}
				} else {
					System.out.println("\tWord: " + line + "\n");
				}
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("\t*** Empty Input String: String index out of range: 0\n");
			}
		}
		fileRead.close();
		
		for (int i = 0; i < posCounter; i++) {
			System.out.println("\tArray[" + i + "]: " + numbers[i]);
		}
	}
}
