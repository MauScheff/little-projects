public class Lab08 { 

   public static void main(String[] args)   {

      System.out.println("\nCSE 1002 Lab 8 - Abstract classes\n");

      RectangleShape rectangle1 = new RectangleShape("Rectangle1", 3.0, 4.0);
      rectangle1.printShape();

      RectangleShape rectangle2 = new RectangleShape("Rectangle2", 4.0, 3.0);
      rectangle2.printShape();

      CircleShape circle1 = new CircleShape("Circle1", 4.0);
      circle1.printShape();

      CircleShape circle2 = new CircleShape("Circle2", 3.0);
      circle2.printShape();
   }
}