/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Author: Deborah Wong, wongd@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab10
 */

import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Arrays;

public class BTree<T> extends BT<T> {
    
    public class Node extends BNode {
	
	// Extends T[] keys
	private boolean isLeaf;
	private int numberOfKeys;
	private Node[] child;
	
	public Node(int maxNumKeys) {
	    this.isLeaf = false;
	    this.numberOfKeys = 0;
	    this.diskWriteKeys((T[]) new Object[maxNumKeys]);
	    this.child = (Node[]) Array.newInstance(Node.class, maxNumKeys + 1);
	}

	// Extends T[] diskReadKeys()
	// Extends void diskWriteKeys(T[] newKeys)

	public Node[] diskReadNodes() {
	    return child;
	}

	public void diskWriteNodes(Node[] nodes) {
	    this.child = nodes;
	}

	public void setLeaf(boolean isLeaf) {
	    this.isLeaf = isLeaf;
	}

	public boolean isLeaf() {
	    return this.isLeaf;
	}
	
	public int getNumberOfKeys() {
	    return this.numberOfKeys;
	}
	
	public void setNumberOfKeys(int numberOfKeys) {
	    this.numberOfKeys = numberOfKeys;
	}

	public void incrementNumKeys() {
	    this.numberOfKeys++;
	}

	public void decrementNumKeys() {
	    this.numberOfKeys--;
	}

	public boolean isFull() {
	    return (this.numberOfKeys == this.diskReadKeys().length);
	}

	/*
	 * @return The index of the key in this node, -1 if it doesn't exist.
	 */
	public int containsKey(T key, Comparator<? super T> comparator) {
	    T[] keys = this.diskReadKeys();
	    int equality;
	    
	    for (int i = 0; i < this.getNumberOfKeys(); i++) {
		equality = comparator.compare(keys[i], key);
		if (equality == 0) return i;
	    }

	    return -1;
	}

	public String toString() {
	    String result = "[";
	    T[] keys = this.diskReadKeys();
	    
	    int j = 0;
	    while (j < this.numberOfKeys - 1) {
		result += keys[j] + ", ";
		j++;
	    }

	    if (this.numberOfKeys != 0 && keys[j] != null) {
		result += keys[j];
	    }

	    result += "]";
	    
	    return result;
	}

    }

    public class NodeAndIndex {
	
	Node node;
	int index;

	public NodeAndIndex(Node node, int index) {
	    this.node = node;
	    this.index = index;
	}

	public Node getNode() {
	    return this.node;
	}

	public int getIndex() {
	    return this.index;
	}
    }

    private int treeHeight;
    private Node root;
    private final int minNumKeys;
    private final int maxNumKeys;
    private final int minNumLinks;
    private final int maxNumLinks;

    public BTree(int minNumKeys) {
	this.minNumKeys = minNumKeys;
	this.maxNumKeys =(minNumKeys*2) + 1;
	
	this.minNumLinks = minNumKeys + 1;
	this.maxNumLinks = maxNumKeys + 1;

	Node newNode = new Node(maxNumKeys);
	newNode.setLeaf(true);
	root = newNode;
	treeHeight = 0;
    }


    @Complexity (
         basic_operation = "insert()", 
         N = "Height", 
         number_of_steps = "N + 1",
         big_O = "N"
    )
    /*
     * If the root is full, perform the necessary adjustments before
     * calling insertNonFull
     */
    public void insert(T element, Comparator<? super T> comparator) {

	Node rootNode = root;

	if (rootNode.getNumberOfKeys() == maxNumKeys) {
	    Node sentinel = new Node(maxNumKeys);
	    root = sentinel;
	    treeHeight++;

	    Node[] sentinelNode = sentinel.diskReadNodes();
	    sentinelNode[0] = rootNode;
	    sentinel.diskWriteNodes(sentinelNode);

	    splitChild(sentinel, 0, rootNode);
	    insertNonFull(sentinel, element, comparator);
	} else {
	    insertNonFull(rootNode, element, comparator);
	}
    }

    @Complexity (
         basic_operation = "insert()", 
         N = "Height", 
         number_of_steps = "N + 1",
         big_O = "N"
    )
    /*
     * Takes care of splitting full nodes on the path down when looking
     * for the node.
     */
    public void insertNonFull(Node currentNode, T element, Comparator<? super T> comparator) {

	assert(!currentNode.isFull());

	int index = currentNode.getNumberOfKeys() - 1;
	
	T[] currentNodeKey = currentNode.diskReadKeys();
	
	// If it's a leaf insert it, otherwise recurse.
	if (currentNode.isLeaf()) {

	    while (index >= 0 && comparator.compare(element, currentNodeKey[index]) < 0) {
		currentNodeKey[index + 1] = currentNodeKey[index];
		index--;
	    }

	    currentNodeKey[index + 1] = element;
	    currentNode.incrementNumKeys();
	    
	    currentNode.diskWriteKeys(currentNodeKey);
	    
	} else {

	    while (index >= 0 && comparator.compare(element, currentNodeKey[index]) < 0) {
		index--;
	    }

	    index++;
	    
	    Node[] currentNodeLink = currentNode.diskReadNodes();

	    if (currentNodeLink[index].isFull()) {
		
		splitChild(currentNode, index, currentNodeLink[index]);
		currentNodeKey = currentNode.diskReadKeys();
		
		// Since now we have a new key, we check if we should
		// take the current link, or the new link.
		if (comparator.compare(element, currentNodeKey[index]) > 0) {
		    index++;
		}	
	    }
	    
	    insertNonFull(currentNodeLink[index], element, comparator);
	}
	
    }
    
    @Complexity (
         basic_operation = "delete()", 
         N = "Height", 
         number_of_steps = "T * minNumKeys + 1",
         big_O = "N"
    )
    public boolean delete(T element, Comparator<? super T> comparator) {
	
	boolean result = delete(element, comparator, root);

	if (rootIsSentinel()) {
	    Node[] link = root.diskReadNodes();
	    root = link[0];
	    this.treeHeight--;
	}
	
	return result;
    }
    
    @Complexity (
         basic_operation = "delete()", 
         N = "Height", 
         number_of_steps = "T * minNumKeys + 1",
         big_O = "N"
    )
    public boolean delete(T element, Comparator<? super T> comparator, Node currentNode) {
	
	T[] key = currentNode.diskReadKeys();
	Node[] link = currentNode.diskReadNodes();
	int keyIndex = currentNode.containsKey(element, comparator);

	if (currentNode.isLeaf()) {

	    if (keyIndex == -1) {
		return false;
	    } 
	    
	    // To overwrite and rearrange keys.
	    for (int j = keyIndex; j < currentNode.getNumberOfKeys() - 1; j++) {
		key[j] = key[j+1];
	    }
	    
	    currentNode.decrementNumKeys();
	    currentNode.diskWriteKeys(key);
	    return true;
	}
	
	// If the key was found in this node
	if (keyIndex >= 0) {
	    if (link[keyIndex].getNumberOfKeys() > minNumKeys) {
		T predecessor = findPredecessor(currentNode, keyIndex);
		key[keyIndex] = predecessor;
		currentNode.diskWriteKeys(key);
		return delete(predecessor, comparator, link[keyIndex]);
	    } else if (link[keyIndex + 1].getNumberOfKeys() > minNumKeys) {
		T successor = findSuccessor(currentNode, keyIndex);
		key[keyIndex] = successor;
		currentNode.diskWriteKeys(key);
		return delete(successor, comparator, link[keyIndex+1]);
	    } else {
		mergeWithChildren(currentNode, keyIndex, link[keyIndex], link[keyIndex + 1]);
		link = currentNode.diskReadNodes();
		return delete(element, comparator, link[keyIndex]);
	    }
	    
	} else {
	    
	    // Find the index of the link where we should descend
	    int descendIndex = currentNode.getNumberOfKeys() - 1;
	    while (descendIndex >= 0 && comparator.compare(element, key[descendIndex]) < 0) {
		descendIndex--;
	    }
	    descendIndex++;
   
	    // Fix it so that we can descend to this node
	    if (link[descendIndex].getNumberOfKeys() == minNumKeys) {
		
		// Try to borrow from left sibling
		if (descendIndex - 1 >= 0 && 
		    link[descendIndex - 1].getNumberOfKeys() > minNumKeys) {
		    Node child1 = link[descendIndex - 1];
		    Node child2 = link[descendIndex];
		    
		    T[] child1Key = child1.diskReadKeys();
		    T[] child2Key = child2.diskReadKeys();
		    
		    Node[] child1Link = child1.diskReadNodes();
		    Node[] child2Link = child2.diskReadNodes();
		    
		    // Move keys to the right to make space for incoming key
		    for (int j = child2.getNumberOfKeys() - 1; j >= 0; j--) {
			child2Key[j+1] = child2Key[j];
		    }
		        
		    // Move links to the right to make space for incoming link
		    for (int j = child2.getNumberOfKeys(); j >= 0; j--) {
			child2Link[j+1] = child2Link[j];
		    }
		    
		    // Bring key down
		    child2Key[0] = key[descendIndex - 1];
		    child2.incrementNumKeys();
		    
		    // Copy key up
		    key[descendIndex - 1] = child1Key[child1.getNumberOfKeys() - 1];
			
		    // Copy link to sibling
		    child2Link[0] = child1Link[child1.getNumberOfKeys()];
		    
		    // Remove key from child1
		    child1.decrementNumKeys();
		    
		    currentNode.diskWriteKeys(key);
		    child1.diskWriteKeys(child1Key);
		    child2.diskWriteKeys(child2Key);
		    
		    child1.diskWriteNodes(child1Link);
		    child2.diskWriteNodes(child2Link);
		    
		    // Try to borrow from right sibling
		} else if ((descendIndex + 1 <= currentNode.getNumberOfKeys()) && 
			   link[descendIndex + 1].getNumberOfKeys() > minNumKeys) {
		    Node child1 = link[descendIndex];
		    Node child2 = link[descendIndex + 1];
		    
		    T[] child1Key = child1.diskReadKeys();
		    T[] child2Key = child2.diskReadKeys();
		    
		    Node[] child1Link = child1.diskReadNodes();
		    Node[] child2Link = child2.diskReadNodes();
		    
		    // Bring key down
		    child1Key[minNumKeys] = key[descendIndex];
		    child1.incrementNumKeys();
		    
		    // Copy key up
		    key[descendIndex] = child2Key[0];
		    
		    // Copy link to sibling
		    child1Link[child1.getNumberOfKeys()] = child2Link[0];
		    
		    // Shift keys of child2 left
		    for (int j = 0; j < child2.getNumberOfKeys() - 1; j++) {
			child2Key[j] = child2Key[j + 1];
		    }
		    		    
		    // Shift links of child2 left
		    for (int j = 0; j < child2.getNumberOfKeys(); j++) {
			child2Link[j] = child2Link[j + 1];
		    }

		    child2.decrementNumKeys();
		    
		    currentNode.diskWriteKeys(key);
		    child1.diskWriteKeys(child1Key);
		    child2.diskWriteKeys(child2Key);
		    
		    child1.diskWriteNodes(child1Link);
		    child2.diskWriteNodes(child2Link);
		    
		    // Merge with either left or right siblings
		} else {
		    if (descendIndex  >= currentNode.getNumberOfKeys()) {
			descendIndex--;
			mergeWithChildren(currentNode, descendIndex, link[descendIndex],
					  link[descendIndex + 1]);
		    } else {    
			mergeWithChildren(currentNode, descendIndex, link[descendIndex],
					  link[descendIndex + 1]);
		    }
		} 	    
	    }
	    
	    // Descend
	    link = currentNode.diskReadNodes();
	    return delete(element, comparator, link[descendIndex]);
	}
    }

    /*
     * Will "bring down" the key at parent's keyIndex to child 1, and "add" child2
     * to child 1.
     */
    private void mergeWithChildren(Node parent, int keyIndex, Node child1, Node child2) {
	assert(child1.getNumberOfKeys() == minNumKeys &&
	       child2.getNumberOfKeys() == minNumKeys);

	T[] parentKeys = parent.diskReadKeys();
	T[] child1Keys = child1.diskReadKeys();
	T[] child2Keys = child2.diskReadKeys();

	Node[] parentLinks = parent.diskReadNodes();
       	Node[] child1Links = child1.diskReadNodes();
	Node[] child2Links = child2.diskReadNodes();

	// Copy keys from child2
	for (int j = minNumKeys + 1; j < maxNumKeys; j++) {
	    child1Keys[j] = child2Keys[j - minNumKeys - 1];
	    child1.incrementNumKeys();
	    child2.decrementNumKeys();
	}
		
	assert child2.getNumberOfKeys() == 0 : child2;
	
	// Copy links from child 2
	
	for (int j = 0; j <= minNumKeys; j++) {
	    child1Links[minNumKeys + 1 + j] = child2Links[j];
	}

	// Bring down key
	child1Keys[minNumKeys] = parentKeys[keyIndex];
	child1.incrementNumKeys();

	// Shift keys to the left
	for (int j = keyIndex; j < parent.getNumberOfKeys() - 1; j++) {
	    parentKeys[j] = parentKeys[j+1];
	}
	
	// Shift links to the left
	for (int j = keyIndex + 1; j < parent.getNumberOfKeys(); j++) {
	    parentLinks[j] = parentLinks[j+1];	    
	}

	parent.decrementNumKeys();

	parent.diskWriteKeys(parentKeys);
	child1.diskWriteKeys(child1Keys);
	child2.diskWriteKeys(child2Keys);
	parent.diskWriteNodes(parentLinks);
	child1.diskWriteNodes(child1Links);
	child2.diskWriteNodes(child2Links);

	
    }
    
    private boolean rootIsSentinel() {
	return (!root.isLeaf() && root.getNumberOfKeys() == 0);
    }
    
    @Complexity (
         basic_operation = "split()", 
         N = "1", 
         number_of_steps = "1",
         big_O = "1"
    )
    public void splitChild(Node parent, int index, Node child1) {
	
	// Because the parent should never be full
	assert(!parent.isFull());

	// Allocate Node
	Node child2 = new Node(maxNumKeys);
	child2.setLeaf(child1.isLeaf());
	child2.setNumberOfKeys(minNumKeys);

	// Simulate reading keys and links from disk
	T[] parentKey = parent.diskReadKeys();
	Node[] parentNode = parent.diskReadNodes();
	T[] child2Key = child2.diskReadKeys();
	Node[] child2Node = child2.diskReadNodes();
	T[] child1Key = child1.diskReadKeys();
	Node[] child1Node = child1.diskReadNodes();

	// Copy keys to the new child	
	for (int j = 0; j <= (minNumKeys - 1) ; j++) {
	    child2Key[j] = child1Key[(minNumKeys+j+1)];   
	}

	// Copy links to the new child
	if (!child1.isLeaf()) {
	    for (int j = 0; j < minNumLinks; j++) {
		child2Node[j] = child1Node[j + minNumLinks];
	    } 
	}

	// Shrink child1
	child1.setNumberOfKeys(minNumKeys);
	
	// Shift Links to the right
	for (int j = parent.getNumberOfKeys(); j >= index + 1; j--) {
	    parentNode[j+1] = parentNode[j];
	}

	// Set right link of parent to the new child.
	parentNode[index + 1] = child2;

	// Make space for key that will come up
	for (int j = parent.getNumberOfKeys() - 1; j >= index; j--) {
	    parentKey[j+1] = parentKey[j];
	}

	// Move key up
	parentKey[index] = child1Key[minNumKeys];
	parent.incrementNumKeys();

	// Simulate writing to disk
	parent.diskWriteKeys(parentKey);
	parent.diskWriteNodes(parentNode);
	child2.diskWriteKeys(child2Key);
	child2.diskWriteNodes(child2Node);
	child1.diskWriteKeys(child1Key);
	child1.diskWriteNodes(child1Node);
    } 
    
    /*
     * @return If key is found return a pair containing the node and the index, return null otherwise.
     */
    public NodeAndIndex findKey(T key, Comparator<? super T> comparator, Node currentNode) {
	
	if (currentNode == null) {
	    // Key not found
	    return null;
	}
	
	T[] keys = currentNode.diskReadKeys();
	Node[] links = currentNode.diskReadNodes();
	
	int j = currentNode.getNumberOfKeys() - 1;
	int equality;

	while (j >= 0 && (equality = comparator.compare(key, keys[j])) <= 0) {
	    if (equality == 0) {
		return new NodeAndIndex(currentNode, j);		
	    }
	    j--;
	}
	j++;

      	return findKey(key, comparator, links[j]);
    }
    
    /*
     * I don't see the point in doing this.
     */
    public T findMax(T k, Comparator<? super T> comparator) {

	List<T> list = getInfixOrder();
	T[] allKeysInOrder = (T[]) list.toArray();

	int i = allKeysInOrder.length - 1;
	int equality = 0;

	// Find when the key is greater than the key at i starting from the right.
	while (i >= 0 && (equality = comparator.compare(k, allKeysInOrder[i])) < 0) {
	    i--;
	}
	
	// If we found a match in the list return it's left key.
	// (The greatest element less than k). Otherwise return 
	// the key at i.
	if (equality == 0) {
	    i--;
	}

	// Check that we didn't go off bounds
	if (i < 0) {
	    return null;
	} 

	return allKeysInOrder[i];
    }

    /*
     * I don't see the point in doing this.
     */
    public T findMin(T k, Comparator<? super T> comparator) {

	List<T> list = getInfixOrder();
	T[] allKeysInOrder = (T[]) list.toArray();

	int i = 0;
	int equality = 0;

	// Find when the key is greater than the key at i starting from the right.
	while (i <= allKeysInOrder.length - 1 && (equality = comparator.compare(k, allKeysInOrder[i])) > 0) {
	    i++;
	}
	
	// If we found a match in the list return it's left key.
	// (The greatest element less than k). Otherwise return 
	// the key at i.
	if (equality == 0) {
	    i++;
	}

	// Check that we didn't go off bounds
	if (i > allKeysInOrder.length - 1) {
	    return null;
	} 

	return allKeysInOrder[i];	
    }

    private T findPredecessor(Node currentNode, int index) {

	if (currentNode.isLeaf()) {
	    return null;
	} else {
	    Node[] links = currentNode.diskReadNodes();
	    // If it's not a leaf it should always have a left
	    // and right link for any given key.
	    assert(links[index] != null);
	    return findMax(links[index]);
	}
    }

    /*
     * @param index of key in currentNode
     */
    private T findSuccessor(Node currentNode, int index) {
	
	if (currentNode.isLeaf()) {
	    return null;
	} else {
	    Node[] links = currentNode.diskReadNodes();
	    // If it's not a leaf it should always have a left
	    // and right link for any given key.
	    assert(links[index+1] != null);
	    return findMin(links[index+1]);
	}
    }
    
    private T findMin(Node root) {

	Node[] links = root.diskReadNodes();
	
	if (links[0] != null) {
	    return findMin(links[0]);
	} else {
	    T[] keys = root.diskReadKeys();
	    return keys[0];
	}
    }
    
    private T findMax(Node root) {
	
	Node[] links = root.diskReadNodes();
	
	if (links[root.getNumberOfKeys()] != null) {
	    return findMin(links[root.getNumberOfKeys()]);
	} else {
	    T[] keys = root.diskReadKeys();
	    return keys[root.getNumberOfKeys() - 1];
	}
    }
    
    public List<T> getInfixOrder() {
	return getInfixOrder(root);
    }

    /*
     * Will have to re-read the keys and links in each step but I prefer it that
     * way in the sake of not adding and iterative loop in a recursive function.
     */
    private List<T> getInfixOrder(Node currentNode) {
	
	List result = new ArrayList<T>();
	
	if (currentNode != null) {

	    T[] keys = currentNode.diskReadKeys();
	    Node[] link = currentNode.diskReadNodes();

	    for (int j = 0; j <= currentNode.getNumberOfKeys(); j++) {
	    	
		if (j < currentNode.getNumberOfKeys()) {
		    result.addAll(getInfixOrder(link[j]));
		    result.add(keys[j]);
		} else if (j == currentNode.getNumberOfKeys()) {
		     result.addAll(getInfixOrder(link[j]));
		}
	    }
	}

	return result;
    }

    public int height() {
	return treeHeight;
    }

    public String toString() {
	String result = "";
	LinkedList<Node> queue = new LinkedList<Node>();
	LinkedList<Integer> queueHeight = new LinkedList<Integer>();
	int height = height();
	Node currentNode = root;
	queue.add(currentNode);
	queueHeight.add(height);

	while (!queue.isEmpty()) {
	    
	    // Pop Node
	    currentNode = queue.getFirst();
	    queue.removeFirst();
	    
	    // Pop Height
	    height = queueHeight.getFirst();
	    queueHeight.removeFirst();

	    result += "(" + currentNode + ":" + height + ")";
	    
	    Node[] currentNodeLink = currentNode.diskReadNodes();

	    height--;
	    for (int j = 0; j < currentNode.getNumberOfKeys() + 1; j++) {
		// assert(currentNodeLink[j] != null);
		if (currentNodeLink[j] != null) {
		    queue.add(currentNodeLink[j]);
		    queueHeight.add(height);
		}
	    }
	}
	
	return result;
    }
}