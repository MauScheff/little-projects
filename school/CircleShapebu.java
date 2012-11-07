
public class CircleShape extends NewShape{
	/*
	 * Instance Variables
	 */
	private double _radius= 0.0;
	private double _area=0.0;
	private double _perimeter=0.0;
	
	/*
	 * Constructor
	 */
	public CircleShape(String name, double radius){
		super (name);
		_radius = radius;
		_area = this.calcArea(radius);
		_perimeter = this.calcPerimeter(radius);
	}
	
	/*
	 * Methods
	 */
	private double calcArea(double radius){
		return Math.PI*Math.sqrt(radius);
	}
	
	private double calcPerimeter(double radius){
		return 2*Math.PI*radius;
	}
	
	public double getArea(){
		return this._area;
	}
	
	public double getPerimeter() return this._perimeter;
	
	public void printShape(){
		System.out.println("\tShape: " + super.getName());
		System.out.printf("\tRadius: %.1f \n", this._radius);
		System.out.printf("\tArea: %.2f \n", this._area);
		System.out.printf("\tPerimeter: %.2f \n\n", this._perimeter);
	}

}
