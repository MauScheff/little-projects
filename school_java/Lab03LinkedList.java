public interface Lab03LinkedList<T> {
  /**
   * Returns the number of elements that already inserted in this list.
   * 
   * @return the number of elements in this list.
   */
  int size();

  /**
   * Tests if this list has no elements.
   * 
   * @return <tt>true</tt> if this list has no elements; <tt>false</tt>
   *         otherwise.
   */
  boolean isEmpty();

  /**
   * Appends the specified element to the end of this list.
   * 
   * @param elem
   *          element to be added to this array list.
   */
  void add(T elem);

  /**
   * Inserts the specified element to the specified position in this list.
   * 
   * @param index
   *          index at which the specified element is to be inserted.
   * @param elem
   *          element to be inserted.
   * @throws IndexOutOfBoundsException
   *           if index is out of range (index < 0 || index > size()).
   */
  void add(int index, T elem) throws IndexOutOfBoundsException;

  /**
   * Replaces the element at the specified position in this list with the
   * specified element.
   * 
   * @param index
   *          index of element to replace.
   * @param element
   *          element to be stored at the specified position.
   * @return the element previously at the specified position.
   * @throws IndexOutOfBoundsException
   *           if index out of range (index < 0 || index >= size()).
   */
  T set(int index, T element) throws IndexOutOfBoundsException;

  /**
   * Removes the element at the specified position in this list. Shifts any
   * subsequent elements to the left (subtracts one from their indices).
   * 
   * @param index
   *          the index of the element to removed.
   * @return the element that was removed from the list.
   * @throws IndexOutOfBoundsException
   *           if index out of range (index < 0 || index >= size()).
   */
  T remove(int index) throws IndexOutOfBoundsException;

  /**
   * Removes all of the elements from this list. The list will be empty after
   * this call returns.
   */
  void clear();

  /**
   * Returns the element at the specified position in this list.
   * 
   * @param index
   *          index of element to return.
   * @return the element at the specified position in this list.
   * @throws IndexOutOfBoundsException
   *           if index is out of range (index < 0 || index >= size()).
   */
  T get(int index) throws IndexOutOfBoundsException;

  /**
   * Returns an array containing all of the elements in this list in the correct
   * order.
   * 
   * @return an array containing all of the elements in this list in the correct
   *         order.
   */
  T[] toArray();
}