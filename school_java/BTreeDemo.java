
public class BTreeDemo {

   public static void main(String[] args) {
      
      // tests BTree with minimum #keys = 1 (or maximum #keys = 3).
      BTDemo.demo1(new BTree<Character>(1));
      
      // tests BTree with minimum #keys = 2 (or maximum #keys = 5).
      BTDemo.demo2(new BTree<Integer>(2));
   }

}
