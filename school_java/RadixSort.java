/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab07
 */

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class RadixSort implements SortInterface {

    public static void main (String[] args) throws FileNotFoundException{

	File file = new File(args[0]);
	Scanner stdin = new Scanner(file);

	    ArrayList<String> ISBN = new ArrayList<String>();
	    ArrayList<String> Title = new ArrayList<String>();

	    RadixSort rsd = new RadixSort();
	    
	    while(stdin.hasNextLine()) {
		ISBN.add(stdin.next());
		Title.add(stdin.nextLine());
	    }

	    ISBN.trimToSize();
	    Title.trimToSize();

	    String[] sortedISBN = rsd.sort(ISBN.toArray(new String[ISBN.size()]));
	    String[] sortedTitle = rsd.sort(Title.toArray(new String[Title.size()]));

	    System.out.println("Sorted by ISBN: ");

	    for (String isbn : sortedISBN) {
		System.out.println(isbn);
	    }
	    
	    System.out.println("Sorted by Title: ");
	    
	    for (String title : sortedTitle) {
		System.out.println(title);
	    }
    } 

   /**
    * Sort the given array using counting sort. The lexicographic ordering
    * of the strings should be used. (the order in which the letters are in
    * the ascii table)
    *
    * @param li
    *                the array to be sorted
    * @param index
    *                the index of the character to sort on
    * @return the array sorted on the index'th character.
    */
    @Complexity (
         basic_operation = "Assignment", 
         N = "li.length", 
         number_of_steps = "3N + N*Log(N)",
         big_O = "N*Log(N)"
      )
    public String[] countingSort(String[] li, int index) {
	int min = ' ' + 0;
	int max = ' ' + 0;

	// Find Max in range (Min is ascii value of ' ')
	for (int j = 0; j < li.length; j++) {
	    if (li[j].charAt(index) > max) {
		max = li[j].charAt(index) + 0;
	    }
	}
      	
	// Array where elements within the range of values fit.
	int[] count = new int[max - min + 1];

	// Count elements
	for (int j = 0; j < li.length; j++) {
	    count[li[j].charAt(index) - min]++;
	}

	// Modify count so that it holds the number of elements less
	// than or equals to j.
	for (int j = 1; j < count.length; j++) {
	    count[j] += count[j-1];
	}

	// Traverse the original array backwards, finding the position of each
	// element in count and finally dropping that number in the resulting array.
	String[] result = new String[li.length];
	for (int j = li.length - 1 ; j >= 0; j--) {
	    int newIndex = count[li[j].charAt(index) - min] - 1;
	    count[li[j].charAt(index) - min]--;
	    result[newIndex] = li[j];
	}

	return result;
    }

   /**
    * Sort the given array using radix sort.
    *
    * @param li
    *                the array to be sorted
    */
    @Complexity (
         basic_operation = "countingSort()", 
         N = "li.length", 
         number_of_steps = "3N + N*countingSort",
         big_O = "N*K (k is the max key length)"
      )
    public String[] sort(String[] li) {
	
	// Find largest string
	int maxLength = 0;
	for (int j = 0; j < li.length; j++) {
	    if (li[j].length() > maxLength) {
		maxLength = li[j].length();
	    }
	}

	// Append spaces
	for (int j = 0; j < li.length; j++) {
	    li[j] += spaces(maxLength - li[j].length());
	}

	// Radix Sort
	for (int j = maxLength - 1; j >= 0; j--) {
	    li = countingSort(li, j);
	}

	// Trim
	for (int j = 0; j < li.length; j++) {
	    li[j] = li[j].trim();
	}

	return li;
    }

    /*
     * Return a string of spaces.
     * @param number the number of spaces wanted
     * @retunr a string of spaces
     */
    private static String spaces(int number) {
	String result = "";

	for (int j = 0; j < number; j++) {
	    result += " ";
	}
	
	return result;
    }
}
 