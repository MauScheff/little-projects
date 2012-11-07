/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab03
 */

public class LinkedList<T> implements Lab03LinkedList<T> {

   class Node {

      T data;

      Node next;

      Node previous;

      public Node(T data) {
         this.data = data;
         this.next = null;
         this.previous = null;
      }

      public Node() {
         this.data = null;
         this.next = null;
         this.previous = null;
      }

      public Node getNext() {
         return this.next;
      }

      public void setNext(Node nextLink) {
         this.next = nextLink;
      }

      public Node getPrevious() {
         return this.previous;
      }

      public void setPrevious(Node previousLink) {
         this.previous = previousLink;
      }

      public void bind(Node b) {
         this.setNext(b);
         b.setPrevious(this);
      }

      public T getData() {
         return this.data;
      }

      public String toString() {
         return (this.data == null) ? "null" : this.data.toString();
      }
   }

   /*
    * Head and Tail are Sentinels
    */
   public Node head;

   public Node tail;

   public int size;

   public LinkedList() {
      head = new Node();
      tail = new Node();
      head.setNext(tail);
      tail.setPrevious(head);
      size = 0;
   }

   public int size() {

      return this.size;
   }

   public boolean isEmpty() {

      return this.size() == 0;
   }

   public void add(T elem) {

      this.add(this.size(), elem);
   }

   @Complexity (basic_operation = "see previousElementAt()",
		N = "see PreviousElementAt()",
		number_of_steps = "see previousElementAt()",
		big_O = "see previousElementAt()")
   public void add(int index, T elem) throws IndexOutOfBoundsException {
      if (index > this.size() || index < 0) {
         throw new IndexOutOfBoundsException();
      }

      Node tempNode = previousElementAt(index);
      Node newNode = new Node(elem);
      newNode.bind(tempNode.getNext());
      tempNode.bind(newNode);
      size++;
   }

   @Complexity (basic_operation = "see previousElementAt()",
		N = "see PreviousElementAt()",
		number_of_steps = "see previousElementAt()",
		big_O = "see previousElementAt()")
   public T remove(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= size()) {
         throw new IndexOutOfBoundsException();
      }

      Node tempNode = previousElementAt(index);
      Node oldNode = tempNode.getNext();
      tempNode.bind(tempNode.getNext().getNext());
      size--;
      return oldNode.getData();
   }

   @Complexity (basic_operation = "Linking nodes",
		N = "None",
		number_of_steps = "4",
		big_O = "1")
   public T set(int index, T element) throws IndexOutOfBoundsException {
      if (index < 0 || index >= this.size()) {
         throw new IndexOutOfBoundsException();
      }

      Node oldNode = elementAt(index);
      this.remove(index);
      this.add(index, element);
      return (T) oldNode.getData();
   }

   public void clear() {
      this.size = 0;
      this.head = new Node();
      this.tail = new Node();
      head.bind(tail);
   }

   /*
    * Return the element previous to the index @param index the index of a node
    * @return the node previous to the index
    */
   @Complexity (basic_operation = "Assignment",
		N = "Index",
		number_of_steps = "N - 1",
		big_O = "N")
   public Node previousElementAt(int index) {
      Node tempLink = (index == 0) ? head : head.getNext();

      for (int j = 0; j < index - 1; j++) {
         tempLink = tempLink.getNext();
      }

      return tempLink;
   }

   @Complexity (basic_operation = "see previousElementAt()",
		N = "see PreviousElementAt()",
		number_of_steps = "see previousElementAt()",
		big_O = "see previousElementAt()")
   public Node elementAt(int index) {
      return this.previousElementAt(index).getNext();
   }

   @Complexity (basic_operation = "see previousElementAt()",
	       N = "see PreviousElementAt()",
	       number_of_steps = "see previousElementAt()",
	       big_O = "see previousElementAt()")
   public T get(int index) throws IndexOutOfBoundsException {
      if (index < 0 || index >= this.size()) {
         throw new IndexOutOfBoundsException();
      }

      return this.elementAt(index).getData();
   }

   @Complexity (basic_operation = "assignment",
		N = "this.size",
		number_of_steps = "2N",
		big_O = "N")
   public T[] toArray() {
      T[] array = (T[]) new Object[this.size()];
      Node tempLink = head.getNext();

      for (int j = 0; j < array.length; j++) {
         array[j] = tempLink.getData();
         tempLink = tempLink.getNext();
      }

      return array;
   }
}
