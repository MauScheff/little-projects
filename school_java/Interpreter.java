/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab03
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Interpreter {

   private static LinkedList<StockAtom> stockData = new LinkedList<StockAtom>();

   public static Scanner stdin = new Scanner(System.in);

   public static void main(String[] args) {
       readFile(); // This is where the file gets read and the data gets placed into "StockData" LinkedList
    
       while (true) { // The lab had no intormation on when to finish capturing input
	   System.out.print("Query? ");
	   String unparsedLine = stdin.nextLine(); //Ex. "min 3 mo from:0305"
	   String[] userInput = unparsedLine.split(" "); //Ex. {"min", "3", "mo", "from", "0305"}
	
	   try {
	       double[] results = callStatisticsFunction(userInput[0], 
                   getRangeOfData(userInput[1], userInput[2], userInput[3]));
	       System.out.format("DJI: %.2f%n", results[0]);
	       System.out.format("NSYE: %.2f%n", results[1]);
	     System.out.format("NASDAQ: %.2f%n%n", results[2]);
	   } catch (Exception e) {
	       System.out.println("Query impossible – please check your input\n");
	   }
       }
   }
    
    /*
     * Returns and array containing the data between two dates.
     *
     * @param timeLength (Ex. 3) 
     * @param timeQualifier (Ex. mo)
     * @return array of StockAtom's
     */
   private static StockAtom[] getRangeOfData(String timeLength,
         String timeQualifier, String fromOrToDate) throws IllegalArgumentException{

      int[] fieldAndAmount = getDateFieldAndAmount(timeQualifier, timeLength);
      String[] dateString = fromOrToDate.split(":");
      if (dateString[0].equals("to")){
	  //If it's negative go backwards in the calendar.
	  fieldAndAmount[1] *= -1;
      }

      String date = dateString[1];
      Calendar dateObjectA = new GregorianCalendar(2007, Integer.parseInt(date
            .substring(0, 2)), Integer.parseInt(date.substring(2)));
      Calendar dateObjectB = (Calendar) dateObjectA.clone();
      dateObjectB.add(fieldAndAmount[0], fieldAndAmount[1]);

      if (dateObjectB.before(new GregorianCalendar(2006, 12, 31))
            || dateObjectB.after(new GregorianCalendar(2008, 01, 01)))
         throw new IllegalArgumentException();
      
      
      // Call get data with the dates in order.
      if (dateObjectA.after(dateObjectB)) {
         return getData(dateObjectToString(dateObjectB),
               dateObjectToString(dateObjectA));
      }
      return getData(dateObjectToString(dateObjectA),
            dateObjectToString(dateObjectB));
   }

    /*
     * Convert a Calendar Object to a string like "0610"
     * @param dateObject A Java Calendar Object
     * @return A String formated like "0212"
     */
    private static String dateObjectToString(Calendar dateObject) {
	int month = dateObject.get(Calendar.MONTH);
	String monthStr = String.valueOf(month);
	if (month < 10)
	    monthStr = "0" + monthStr;
	int day = dateObject.get(Calendar.DAY_OF_MONTH);
	String dayStr = String.valueOf(day);
	if (day < 10)
	    dayStr = "0" + dayStr;
	
	return monthStr + dayStr;
    }
    
    /*
     * Returns an array of StockAtom containing the data from within
     * a range.
     * Note: The operations are performed assuming the list is in
     * date Descending order.
     * @param from "starting date"
     * @param to "ending date"
     * @return An array containing StockAtom's within the range.
     */
   private static StockAtom[] getData(String from, String to) {
       Object[] objectArray = stockData.toArray();
       LinkedList<StockAtom> results = new LinkedList<StockAtom>();
       
       for (int j = 0; j < objectArray.length; j++) {
	   StockAtom temp = (StockAtom) objectArray[j];
	   
	   if (temp.getDate().compareTo(from) >= 0 && temp.getDate().compareTo(to) < 0)
	       results.add(temp);
       }
       
       Object[] resultsObj = results.toArray();
       StockAtom[] res = new StockAtom[resultsObj.length];
      
       for (int j=0; j < res.length; j++) {
	   res[j] = (StockAtom) resultsObj[j];
       }
       
       return res;
   }
    
    /*
     * @param fieldStr (Ex. "wk")
     * @param timeLength How much amount of fieldStr
     * @return array containing a java Calendar constant representing
     * a month, and the amount of this constant.
     */
    private static int[] getDateFieldAndAmount(String fieldStr, String timeLength) {
      int amount = Integer.parseInt(timeLength);
      int field = 0;
      if (fieldStr.equals("mo")) {
	  field = Calendar.MONTH;
      } else if (fieldStr.equals("qtr")) {
	  field = Calendar.MONTH;
	  amount *= 3;
      } else if (fieldStr.equals("dy")) {
	  field = Calendar.DAY_OF_MONTH;
      } else if (fieldStr.equals("wk")) {
	  field = Calendar.WEEK_OF_MONTH;
      } else if (fieldStr.equals("biwk")) {
	  field = Calendar.WEEK_OF_MONTH;
	  amount *= 2;
      }
      
      return new int[] { field, amount };
    }

    /*
     * Calls the corresponding function the user defined on an
     * array of atoms and return the result of this function.
     */
    private static double[] callStatisticsFunction(String function,
						   StockAtom[] atoms) {
	double[] results = { 0.0, 0.0, 0.0 };
	
	if (function.equals("min")) {
	    results = Statistics.minimum(atoms);
	} else if (function.equals("max")) {
	    results = Statistics.maximum(atoms);
	} else if (function.equals("range")) {
	    results = Statistics.range(atoms);
	} else if (function.equals("mean")) {
	    results = Statistics.mean(atoms);
      } else if (function.equals("med")) {
	    results = Statistics.median(atoms);
	} else if (function.equals("var")) {
         results = Statistics.variancePopulation(atoms);
	} else if (function.equals("stdv")) {
	    results = Statistics.standardDeviation(atoms);
	}
	return results;
   }
    
    /*
     * Parses uses input and places it in the list (stockData)
     */
    private static void readFile() {
	Scanner fileReader = null;
	try {
	    fileReader = new Scanner(new File("stock.dat"));
	} catch (FileNotFoundException e) {
	    System.out.println("Error: " + e.getMessage());
      }
	
	while (fileReader.hasNextLine()) {
	    String line = fileReader.nextLine();
	    String[] data = line.split(",");
         stockData.add(new StockAtom(data[0], Double.parseDouble(data[1]),
				     Double.parseDouble(data[2]), Double.parseDouble(data[3])));
	}
    }
    
}