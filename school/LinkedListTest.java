import java.util.Arrays;

public class LinkedListTest {
   public static void main(String[] args) {
      final Lab03LinkedList t = new LinkedList();
      final java.util.List<String> s = new java.util.LinkedList<String>();

      System.out.print("add: ");
      try {
         if (Lab03ListTestSuite.testValidAdd(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }

      System.out.print("clear: ");
      try {
         t.clear();
         s.clear();
         if (Arrays.equals(t.toArray(), s.toArray()))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
      
      System.out.print("empty: ");
      try {
         if (t.isEmpty() && (new LinkedList()).isEmpty())
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
      
      System.out.print("not empty: ");
      try {
         t.add("test");
         s.add("test");
         if (!t.isEmpty())
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
      
      System.out.print("size: ");
      try {
         if (Lab03ListTestSuite.testSize(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }

      System.out.print("valid get: ");
      try {
         if (Lab03ListTestSuite.testValidGet(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }

      System.out.print("invalid get: ");
      try {
         if (Lab03ListTestSuite.testInvalidGet(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
      
      System.out.print("valid set: ");
      try {
         if (Lab03ListTestSuite.testValidSet(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
      
      System.out.print("invalid set: ");
      try {
         if (Lab03ListTestSuite.testInvalidSet(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
      
      System.out.print("valid remove: ");
      try {
         if (Lab03ListTestSuite.testValidRemove(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }

      System.out.print("invalid remove: ");
      try {
         if (Lab03ListTestSuite.testInvalidRemove(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }

      System.out.print("valid insert:");
      try {
         if (Lab03ListTestSuite.testValidInsert(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }

      System.out.print("invalid insert:");
      try {
         if (Lab03ListTestSuite.testInvalidInsert(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e.getMessage() + ">");
      }
      
      System.out.print("toArray: ");
      try {
         if (Lab03ListTestSuite.testToArray(t, s))
            System.out.println("PASS");
         else
            System.out.println("FAIL");
      } catch (Exception e) {
         System.out.println("FAIL <" + e + ">");
      }
   }
}