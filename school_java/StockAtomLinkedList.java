public class StockAtomLinkedList extends LinkedList {
   
   private Node cursor;

   public StockAtomLinkedList(){
      super();
      cursor = head;
   }
   /*
    * Uses the cursor to traverse the linked list, returns the next element.
    */
   public StockAtom getNext() {
      if (this.cursor == head) {
         cursor.getNext();
      }
      this.cursor = cursor.getNext();
      return toStockAtom(this.cursor);
   }

   /*
    * Returns true if the cursor has a next valid node
    */
   public boolean hasNext() {

      if (this.cursor == head) {
         cursor.getNext();
      }
      return (this.cursor != tail);
   }

   public StockAtom toStockAtom(Node node) {
      return (StockAtom) node.getData();
   }
}
