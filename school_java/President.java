import java.io.*;

public class President implements Serializable   { 

   private String PresidentName;
   private int orderInOffice;
   private int startOfTerm;
   private int endOfTerm;

   public President(String name, int order, int start, int end)  {   

      PresidentName = name;
      orderInOffice = order;
      startOfTerm = start;
      endOfTerm = end;
   }

   public String getName()  {

      return PresidentName;
   }

   public int getOrder()  {

      return orderInOffice;
   }

   public int getStart()  {

      return startOfTerm;
   }

   public int getEnd()  {

      return endOfTerm;
   }

   public void printPresident()  {

      System.out.println("\n\tName: "+this.getName());
      System.out.println("\tOrder: "+this.getOrder());
      System.out.printf("\tTerm: %d-%d\n", this.getStart(), this.getEnd());   
   }
}
