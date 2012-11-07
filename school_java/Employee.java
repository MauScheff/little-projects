/*
    * Author:  Mauricio Zepdea, mzepedas@fit.edu
    * Course:  CSE 1002, Section 01, Spring 2008
    * Project: Lab 07, Java Inheritance
    */

public class Employee {
	/*
	 * Instance Variables
	 */
	private String _name, _type;
	private int _id;
	private double _gPay;
	
	/*
	 *Constructors 
	 */
	
	public Employee(String name, int id){
		this._name = name;
		this._id = id;
		this._type = null;
	}
	
	public Employee(String name, int id, String type){
		this._name = name;
		this._id = id;
		this._type = type;
	}
	
	/*
	 * Methods
	 */
	
	public void setGrossPay(double gPay){
		this._gPay = gPay;
	}
	
	public void calcPay(double hoursWorked, double payPerHour){
		if (hoursWorked <= 40){
			setGrossPay(hoursWorked*payPerHour);
			if (this._type==null) this._type="Part-time";
		} else {
			setGrossPay(40*payPerHour+(hoursWorked%40)*payPerHour*1.5);
			if (this._type==null) this._type="Full-time";
		}
		
	}
	
	public void printPay(){
		System.out.println("Employee Pay Record:\n");
		System.out.println("\tEmployee Name: " + this._name);
		System.out.println("\tEmployee ID: " + this._id);
		System.out.print("\tGross Pay: "); System.out.printf("%6.2f%n", this._gPay);
		System.out.println("\tEmployee Type: " + this._type);
		System.out.println("");
	}
}
