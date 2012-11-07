public abstract class NewShape  {

   private String shapeName;

   public NewShape(String name)  {

      shapeName = name;
   }

   public abstract double getArea();

   public abstract double getPerimeter();

   public final String getName()  {

      return shapeName;
   }

   public void printShape()  {

      System.out.println("\tShape: "+shapeName);
      System.out.println("");
   }
}