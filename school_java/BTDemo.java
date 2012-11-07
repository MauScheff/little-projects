import java.util.Comparator;


public class BTDemo {
   /**
    * from Cormen et al book. exercise 18.2-1
    */
   public static void demo1 (BT<Character> tree) {
      final Comparator<Character> cmp = new Comparator<Character>() {
         public int compare(Character arg0, Character arg1) {
            return arg0.compareTo(arg1);
         }
      };
      System.out.println("demo1");
      System.out.printf("before insert : %s%n", tree);
      insertDemo(tree, cmp,
       'F', 'S', 'Q', 'K', 'C', 'L', 'H', 'T', 'V', 'M',
       'R', 'N', 'P', 'A', 'B', 'X', 'Y', 'D', 'Z', 'E'
      );
      
      getInfixOrderDemo(tree);
      
      findMaxDemo(tree, cmp, 'A', 'G', 'F', 'S', 'Z');
      findMinDemo(tree, cmp, 'A', 'G', 'F', 'S', 'Z');

      deleteDemo(tree, cmp,
       'H', 'M', 'Q', 'F', 'S', 'K', 'C', 'L', 'T', 'V',
       'R', 'N', 'P', 'A', 'B', 'X', 'Y', 'D', 'Z', 'E'
      );
   }
   
   public static void demo2 (BT<Integer> tree) {
      final Comparator<Integer> cmp = new Comparator<Integer>() {
         public int compare(Integer arg0, Integer arg1) {
            return arg0.compareTo(arg1);
         }
      };
      System.out.println("demo2");
      System.out.printf("before insert : %s%n", tree);
      insertDemo(tree, cmp, 9, 3, 2, 1, 12, 10, 21, 16, 11, 13, 16);
      deleteDemo(tree, cmp, 11, 13, 1, 12, 11);
   }
   
   public static <T> void insertDemo (BT<T> tree, Comparator<? super T> cmp, T... elements) {
      for (final T e : elements) {
         tree.insert(e, cmp);
         System.out.printf("after insert %s : %s%n", e, tree);
      }
   }
   
   public static <T> void deleteDemo (BT<T> tree, Comparator<? super T> cmp, T... elements) {
      for (final T e : elements) {
         tree.delete(e, cmp);
         System.out.printf("after delete %s : %s%n", e, tree);
      }
   }

   public static <T> void findMaxDemo (BT<T> tree, Comparator<? super T> cmp, T... elements) {
      for (final T e : elements) {
         System.out.printf("findMax %s : %s%n", e, tree.findMax(e, cmp));
      }
   }
   
   public static <T> void findMinDemo (BT<T> tree, Comparator<? super T> cmp, T... elements) {
      for (final T e : elements) {
         System.out.printf("findMin %s : %s%n", e, tree.findMin(e, cmp));
      }
   }
   
   public static <T> void getInfixOrderDemo (BT<T> tree) {
      System.out.print("getInfixOrder: ");
      for (final T e : tree.getInfixOrder()) {
         System.out.printf("%s ", e);
      }
      System.out.println();
   }
}
