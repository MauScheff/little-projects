
public class RectangleShape extends NewShape{
	/*
	 * Instance Variables
	 */
	private double _width= 0.0;
	private double _height=0.0;
	private double _area=0.0;
	private double _perimeter=0.0;
	
	/*
	 * Constructor
	 */
	public RectangleShape(String name, double height, double width){
		super (name);
		_height = height;
		_width = width;
		_area = this.calcArea(height, width);
		_perimeter = this.calcPerimeter(height, width);
	}
	
	/*
	 * Methods
	 */
	private double calcArea(double height, double width){
		return height*width;
	}
	
	private double calcPerimeter(double height, double width){
		return (height*2)+(width*2);
	}
	
	public double getArea(){
		return this._area;
	}
	
	public double getPerimeter(){
		return this._perimeter;
	}
	
	public void printShape(){
		System.out.println("\tShape: " + super.getName());
		System.out.printf("\tHeight: %.1f", this._height);
		System.out.printf("\tWidth: %.1f \n", this._width);
		System.out.printf("\tArea: %.2f \n", this._area);
		System.out.printf("\tPerimeter: %.2f \n\n", this._perimeter);
	}

}
