import java.util.Arrays;

public class Lab03ListTestSuite {
   static private final String[] keyword = new String[] {
         "abstract", "assert", "boolean", "break","byte", "continue",
         "default", "do", "double", "else", "enum", "extends", 
         "false", "final", "finally", "float", "for", "goto", "if",
         "implements", "import", "instanceof", "int", "interface",
         "long", "native", "new", "null", "package", "private",
         "protected", "public", "return", "short", "static", "strictfp",
         "super", "switch", "synchronized", "this", "throw", "throws",
         "transient", "true", "try", "void", "volatile", "while"
         };
   
   static boolean testValidAdd(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=0; i<keyword.length; i++) {
         t.add(keyword[i]); t.add(null);
         s.add(keyword[i]); s.add(null);
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }
   
   static boolean testSize(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=1; i<keyword.length; i<<=2) {
         t.add(keyword[i]); t.add(null); t.remove(t.size()-1);
         s.add(keyword[i]); s.add(null); s.remove(s.size()-1);
      }
      return t.size() == s.size();
   }

   static boolean testValidInsert(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=0; i<4; i++) {
         t.add(i, keyword[i]); 
         s.add(i, keyword[i]);
      }
      for (int i=1; i<s.size(); i<<=2) {
         t.add(i, keyword[keyword.length - i]); t.add(null);
         s.add(i, keyword[keyword.length - i]); s.add(null);
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }
   
   static boolean testInvalidInsert(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=-1; i>Integer.MIN_VALUE && i<0; i <<= 3) {
         try {
            t.add(i, "invalid");
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      final int size = t.size();
      for (int i=size+1; i<Integer.MAX_VALUE && i>size; i <<=3) {
         try {
            t.add(i, "invalid");
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }
   
   static boolean testValidGet(Lab03LinkedList<String> t, java.util.List<String> s) {
      final int size = t.size();
      if (size != s.size()) return false;
      
      for (int i=0; i<size; i++) {
         if (!t.get(i).equals(s.get(i))) {
            return false;
         }
      }
      return true;
   }
   
   static boolean testInvalidGet(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=-1; i>Integer.MIN_VALUE && i<0; i <<= 3) {
         try {
            t.get(i);
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      final int size = t.size();
      for (int i=size; i<Integer.MAX_VALUE && i>=size; i <<=3) {
         try {
            t.get(i);
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      return true;
   }
   
   static boolean testValidSet(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=0; i<keyword.length && i<s.size(); i=2*i + 1) {
         final String k = keyword[i].toUpperCase();
         if (! t.set(i, k).equals(s.set(i, k))) return false;
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }

   static boolean testInvalidSet(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=-1; i>Integer.MIN_VALUE && i<0; i <<= 3) {
         try {
            t.set(i, "invalid");
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      final int size = t.size();
      for (int i=size; i<Integer.MAX_VALUE && i>=size; i <<=3) {
         try {
            t.set(i, "invalid");
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }
   
   static boolean testValidRemove(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=0; i<keyword.length && i<s.size(); i=2*i + 1) {
         if (! t.remove(i).equals(s.remove(i))) return false;
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }

   static boolean testInvalidRemove(Lab03LinkedList<String> t, java.util.List<String> s) {
      for (int i=-1; i>Integer.MIN_VALUE && i<0; i <<= 3) {
         try {
            t.remove(i);
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      final int size = t.size();
      for (int i=size; i<Integer.MAX_VALUE && i>=size; i <<=3) {
         try {
            t.remove(i);
         } catch (IndexOutOfBoundsException e) {
            continue;
         }
         return false;
      }
      return Arrays.equals(t.toArray(), s.toArray());
   }
   
   static boolean testToArray(Lab03LinkedList<String> t, java.util.List<String> s) {
      final Object[] array = t.toArray();
      array[0] = "never return private field";
      return Arrays.equals(t.toArray(), s.toArray());
   }
}