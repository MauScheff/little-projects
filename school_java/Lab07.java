/*
    * Author:  Mauricio Zepdea, mzepedas@fit.edu
    * Course:  CSE 1002, Section 01, Spring 2008
    * Project: Lab 07, Java Inheritance
    */

public class Lab07  { 

   public static void main(String[] args)   {

      System.out.println("\nCSE 1002 Lab 7\n");

      // create a full-time employee with > 40 hours

      Employee emp1 = new Employee("John Smith", 1001, "Full-time");
      emp1.calcPay(45.0, 12.0);
      emp1.printPay();

      // create a full-time exempt employee with > 40 hours

      Exempt emp2 = new Exempt("Paul Jones", 1002);
      emp2.calcPay(48.0, 15.0);
      emp2.printPay();

      // create a part-time employee with < 40 hours

      Employee emp3 = new Employee("Robert Green", 1003);
      emp3.calcPay(25.0, 10.0);
      emp3.printPay();
   }
}