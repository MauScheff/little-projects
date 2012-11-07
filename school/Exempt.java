/*
    * Author:  Mauricio Zepdea, mzepedas@fit.edu
    * Course:  CSE 1002, Section 01, Spring 2008
    * Project: Lab 07, Java Inheritance
    */

public class Exempt extends Employee{
	/*
	 * Constructor
	 */
	public Exempt(String name, int id) {
		super (name, id, "Exempt");
	}
	/*
	 * Overriding method
	 */
	public void calcPay(double hoursWorked, double payPerHour){
		if (hoursWorked >= 40) super.setGrossPay(40*payPerHour);
		else super.setGrossPay(hoursWorked*payPerHour);	
	}
}
