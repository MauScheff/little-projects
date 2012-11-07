import java.util.*;
import java.io.*;

/*
 * Author:  Mauricio Zepeda, mzepedas@fit.edu
 * Course:  CSE 1002, Section 01, Fall 2007
 * Project: Lab 05, Processing Text Files
 */
public class lab05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("");
		lab05.printData(lab05.readData(args));
	}

	private static Media[] readData(String[] args) {
		Media[] collection = null;
		Scanner fileReader = null;
		Scanner elementCounter = null;
		int nElements = 0;

		if (args.length == 0) {
			System.out.println("No input filename.");
			System.exit(0);
		} else {
			System.out.println("CSE 1002 Lab 5 - Mauricio Zepeda");
			System.out.println("");
			try {
				elementCounter = new Scanner(new FileReader(args[0]));
				while (elementCounter.hasNext()) {
					elementCounter.nextLine();
					nElements++;
				}
				elementCounter.close();
				fileReader = new Scanner(new FileReader(args[0]));
				collection = new Media[nElements];
				for (int n = 0; n < collection.length; n++) {
					String sentence = fileReader.nextLine();
					StringTokenizer tokenizer = new StringTokenizer(sentence,
							",");
					tokenizer.nextToken();
					String type = "";
					if (sentence.charAt(0) == '1')
						type = "book";
					else if (sentence.charAt(0) == '2')
						type = "cd";
					else if (sentence.charAt(0) == '3')
						type = "dvd";
					String title = tokenizer.nextToken();
					String author = tokenizer.nextToken();
					int count = Integer.parseInt(tokenizer.nextToken());
					double cost = Double.parseDouble(tokenizer.nextToken());
					collection[n] = new Media(type, title, author, count, cost);
					collection[n].addToCounter(count);
					collection[n].addToCost(count, cost);
				}

			} catch (IOException e) {
				System.out.println("Error opening file:" + e);
			}
		}
		return collection;
	}

	private static void printData(Media[] collection) {
		for (int i = 0; i < collection.length; i++) {
			System.out.println("\t" + "Type: " + collection[i].readType()
					+ "\t" + "Title: " + collection[i].readTitle());
			System.out.print("\t" + "Count: " + collection[i].readCount()
					+ "\t" + "Author: " + collection[i].readAuthor() + "\t"
					+ "Cost: ");
			System.out.printf("$ %7.2f %n%n", collection[i].readCost());
		}
		System.out.print("\t Book Count: " + Media.readBookCounter()
				+ "\t Book Cost: ");
		System.out.printf("$ %7.2f %n", Media.readBookCost());
		System.out.print("\t CD Count:   " + Media.readcdCounter()
				+ "\t CD Cost:   ");
		System.out.printf("$ %7.2f %n", Media.readCdCost());
		System.out.print("\t DVD Count:  " + Media.readdvdCounter()
				+ "\t DVD Cost:  ");
		System.out.printf("$ %7.2f %n%n", Media.readDvdCost());
	}

}
