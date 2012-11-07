/*
 * Author:  Mauricio Zepeda, mzepedas@fit.edu
 * Course:  CSE 1002, Section 01, Spring 2008
 * Project: Lab 04, working with 2D Arrays
 */

import java.util.*;
import java.io.*;

public class LetterArray {

	private char[][] _array;
	private int _dimension;

	Scanner fileScanner = null;

	public LetterArray(String filename, int dimension) {
		_dimension = dimension;
		try {
			fileScanner = new Scanner(new FileReader(filename));
			_array = new char[_dimension][_dimension];
			for (int y = 0; y <= _dimension - 1; y++) {
				for (int x = 0; x <= _dimension - 1; x++) {
					_array[x][y] = fileScanner.nextLine().charAt(0);
				}
			}
		} catch (IOException e) {
			System.out.printf("Error opening file: %n%s%n", e);
		}
	}

	public void printAcross() {
		System.out.println("Printing Left to Right: \n");
		for (int y = 0; y <= _dimension - 1; y++) {
			for (int x = 0; x <= _dimension - 1; x++) {
				System.out.print("\t" + _array[x][y]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public void printDown() {
		System.out.println("Printing Top to Bottom: \n");
		for (int y = 0; y <= _dimension - 1; y++) {
			for (int x = 0; x <= _dimension - 1; x++) {
				System.out.print("\t" + _array[y][x]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public void printBackForth() {
		System.out.println("Printing Back and Forth: \n");
		for (int y = 0; y <= _dimension - 1; y++) {
			if (y % 2 == 0) {
				for (int x = 0; x <= _dimension - 1; x++) {
					System.out.print("\t" + _array[x][y]);
				}
				System.out.print("\n");
			} else {
				for (int x = _dimension - 1; x >= 0; x--) {
				System.out.print("\t" + _array[x][y]);
				}
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}

	public void printDiagonal(){
		 System.out.println("Printing Diagonally: \n");
		 for(int y=0,x=0;y<=_dimension-1;y++,x++){
			 System.out.print("\t" + _array[x][y]);
		 }
		 System.out.print("\n");
		 for(int y=_dimension-1, x=0;x<=_dimension-1;y--, x++){
			 System.out.print("\t" + _array[x][y]);
		 }
	}
	 
	public void closeFile() {
		fileScanner.close();
	}

}
