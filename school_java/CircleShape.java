public class CircleShape extends NewShape{
	private double r=0;
	private double a=0;
	private double p=0; 
	public CircleShape(String n, double x){
		super (n);
		r = x;
		a = this.calcArea(x);
		p = this.calcPerimeter(x);
	}
	private double calcArea(double x){
		return Math.PI*Math.sqrt(x);}
	private double calcPerimeter(double x){
		return 2*Math.PI*x;}
	public double getArea(){
		return this.a;}
	public double getPerimeter(){
		return this.p;}
	public void printShape(){
		System.out.println("\tShape: " + super.getName());
		System.out.printf("\tRadius: %.1f \n", this.r);
		System.out.printf("\tArea: %.2f \n", this.a);
		System.out.printf("\tPerimeter: %.2f \n\n", this.p);
	}
}
