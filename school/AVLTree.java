/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab08
 */

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class AVLTree<T> implements BST<T> {
    
    private class Node {
	
	private T data = null;
	private Node leftNode = null;
	private Node rightNode = null;
	private Node parent = null;
	private int height = 0;

	public Node(T data) {
	    this.data = data;
	}

	public Node(){}; // Used for sentinel

	public T getData() {
	    return this.data;
	}

	public void setData(T data) {
	    this.data = data;
	}

	public Node getLeft() {
	    return this.leftNode;
	}

	public Node getRight() {
	    return this.rightNode;
	}

	public void setLeft(Node left) {
	    this.leftNode = left;
	    if (this.leftNode != null) { 
		this.leftNode.setParent(this);
	    }
	}

	public void setRight(Node right) {
	    this.rightNode = right;
	    if (this.rightNode != null) {
		this.rightNode.setParent(this);
	    }
	}

	public void setParent(Node node) {
	    this.parent = node;
	}

	public Node getParent() {
	    return this.parent;
	}

	public int getBalance() {
	    int leftHeight = (this.getLeft() == null) ? -1 : this.getLeft().getHeight();
	    int rightHeight = (this.getRight() == null) ? -1 : this.getRight().getHeight();
	    return leftHeight - rightHeight;
	}

	public void setHeight(int height) {
	    this.height = height;
	}

	public int getHeight() {
	    return this.height;
	}

	public boolean isLeaf() {
	    return this.getLeft() == null && this.getRight() == null;
	}

	public boolean isLeftChild() {
	    return this != null && this.getParent().getLeft() == this;
	}
    }
    
    private Node root;
    
    public AVLTree() {
	root = null;
    }
    
    /**
     * Inserts the specified element to this Binary Search Tree according to the
     * specified comparator.
     * 
     * @param element
     *           the element to be inserted, if not present.
     * @param comparator
     */
    public void insert(T element, Comparator<? super T> comparator) {
	insert(element, comparator, root);
    }
    
    @Complexity (
         basic_operation = "Traversing", 
         N = "root.getHeight()", 
         number_of_steps = "2 * Log(N)",
         big_O = "Log(N)"
    )
    public void insert(T element, Comparator<? super T> comparator, Node node) {
	if (root == null) {
	    root = new Node(element);
	} else {	    
	    int cmp = comparator.compare(element, node.getData());
	    
	    if (cmp == -1) {
		if (node.getLeft() == null) {
		    
		    // Insert
		    Node newNode = new Node(element);
		    node.setLeft(newNode);

		} else {
		    
		    // Recurse
		    insert(element, comparator, node.getLeft());
		}
			
	    } else if (cmp == 1) {
		if (node.getRight() == null) {
		
		    // Insert
		    Node newNode = new Node(element);
		    node.setRight(newNode);

		} else {
		    
		    // Recurse
		    insert(element, comparator, node.getRight());
		}
	    }

	    updateNodeHeight(node);
	    balance(node);
	}
    }
    
    @Complexity (
         basic_operation = "setHeight()", 
         N = "root.getHeight", 
         number_of_steps = "1",
         big_O = "1"
    )
    private void updateNodeHeight(Node node) {
	if (node.isLeaf()) {
	    node.setHeight(0);
	} else if (node.getLeft() == null) {
	    node.setHeight(1 + node.getRight().getHeight());
	} else if (node.getRight() == null) {
	    node.setHeight(1 + node.getLeft().getHeight());
	} else {
	    node.setHeight(1 + Math.max(node.getLeft().getHeight()
					, node.getRight().getHeight()));
	}
    }
    

    @Complexity (
         basic_operation = "Comparison", 
         N = "root.getSize()", 
         number_of_steps = "4",
         big_O = "1"
    )
    private void balance(Node node) {
        //DB System.out.println("Balancing node " + node.getData() + " with balance factor" + node.getBalance());
        if (node.getBalance() == -2) {
            if (node.getRight().getBalance() <= 0) {
                // If both children have equal height (balance == 0) you can do a single left
                // rotation or a double left rotation, the double left comes out more balanced, but
                // it's not the one used in the sample output.
                leftRotation(node);
            } else {
                doubleLeftRotation(node);
            }
        } else if (node.getBalance() == 2) {
            if (node.getLeft().getBalance() >= 0) {
                // If both children have equal height (balance == 0) you can do a single right
                // rotation or a double right rotation, the double right come out more balanced, but
                // it's not the one used in the sample output.
                rightRotation(node);
            } else {
                doubleRightRotation(node);
            }
        }
    }
    
    @Complexity (
         basic_operation = "Assignment", 
         N = "node.getHeight()", 
         number_of_steps = "4",
         big_O = "1"
    )
    private void rightRotation(Node node) {
	//DB System.out.println("Before right rotation: " + toString(root));
	Node parent = null;
	if (node == root) {
	    // Sentinel Node
	    parent = new Node();
	    parent.setLeft(node);
	    root = node.getLeft();
	} else {
	    parent = node.getParent();
	}

	Node newSubRoot = node.getLeft();

	if (parent.getLeft() == node) {
	    parent.setLeft(newSubRoot);
	} else {
	    parent.setRight(newSubRoot);
	}
	if (newSubRoot.getRight() == null) {
	    node.setLeft(null);
	} else {
	    node.setLeft(newSubRoot.getRight());
	}
	
	newSubRoot.setRight(node);
	
	//DB System.out.println("After right rotation: " + toString(root));
	updateNodeHeight(node);
	updateNodeHeight(newSubRoot);
	balance(node);
	balance(newSubRoot);
    }


    @Complexity (
         basic_operation = "Assignment", 
         N = "node.getHeight()", 
         number_of_steps = "4",
         big_O = "1"
    )
    private void leftRotation(Node node) {
	//DB System.out.println("Before left rotation: " + toString(root));
	Node parent = null;
	
	// If node is root, use sentinel for parent node
	if (node == root) {
	    parent = new Node();
	    parent.setRight(node);
	    root = node.getRight();
	} else {
	    parent = node.getParent();
	}
	
	Node newSubRoot = node.getRight();
	
	if (parent.getLeft() == node) {
	    parent.setLeft(newSubRoot);
	} else {
	    parent.setRight(newSubRoot);
	}
	
	if (newSubRoot.getLeft() == null) {
	    node.setRight(null);
	} else {
	    node.setRight(newSubRoot.getLeft());
	}

	newSubRoot.setLeft(node);
	
	//DB System.out.println("After left rotation: " + toString(root));
	updateNodeHeight(node);
	updateNodeHeight(newSubRoot);
	balance(node);
	balance(newSubRoot);
    }

    private void doubleRightRotation(Node node) {
	leftRotation(node.getLeft());
	rightRotation(node);
    }
    
    private void doubleLeftRotation(Node node) {
	rightRotation(node.getRight());
	leftRotation(node);
    }
    
    /**
    * Deletes the specified element from this Binary Search Tree according to
    * the specified comparator.
    * 
    * @param element
    *           the element to be deleted, if present.
    * @param comparator
    * @return <tt>true</tt> if the binary search tree contained the specified
    *         element.
    */
    public boolean delete(T element, Comparator<? super T> comparator) {
	return delete(element, comparator, root);
    }
    
    /**
     * @param element The element to be inserted
     * @param current The root of the tree
     * @return true If the operation succeeded
     */
    @Complexity (
         basic_operation = "Traversing", 
         N = "root.getHeight()", 
         number_of_steps = "2 * Log(N)",
         big_O = "Log(N)"
    )
    public boolean delete(T element, Comparator<? super T> comparator, Node current) {
	if (current == null) return false;
	
	int cmp = comparator.compare(element, current.getData());
	
	if (cmp == -1) {
	    return delete(element, comparator, current.getLeft());
	} else if (cmp == 1) {
	    return delete(element, comparator, current.getRight());
	} else {

	    if (current.isLeaf()) {
		if (current == root) root = null;
		else {
		    Node parent = current.getParent();
		    if (parent.getLeft() == current) {
			parent.setLeft(null);
		    } else {
			parent.setRight(null);
		    }
		    
		    // Update height of ancestors
		    Node temp = parent;
		    temp.setHeight(0);   
		    while (temp != null) {
			updateNodeHeight(temp);
			balance(temp);
			temp = temp.getParent();
		    }
		}
	    } else if (current.getLeft() == null) {
		assert current.getRight().isLeaf();
		current.setData(current.getRight().getData());
		current.setRight(null);

		// Update height of ancestors
		Node temp = current;
		while (temp != null) {
		    updateNodeHeight(temp);
		    balance(temp);
		    temp = temp.getParent();
		}
		
	    } else if (current.getRight() == null) {
		assert current.getLeft().isLeaf();
		current.setData(current.getLeft().getData());
		current.setLeft(null);

		// Update height of ancestors
		Node temp = current;
		while (temp != null) {
		    updateNodeHeight(temp);
		    balance(temp);
		    temp = temp.getParent();
		}
		
	    } else {
		assert current.getRight() != null;
		Node successor = findMin(current.getRight());
		current.setData(successor.getData());
		if (successor.isLeftChild()) {
		    successor.getParent().setLeft(successor.getRight());
		} else {
		    successor.getParent().setRight(successor.getRight());
		}

		// Update height of ancestors
		Node temp = successor.getParent();
		while (temp != null) {
		    updateNodeHeight(temp);
		    balance(temp);
		    temp = temp.getParent();
		}
		
	    }
	}

	return true;
    }

    /**
     * @return the left most leaf of a Node. 
     */
    private Node findMin(Node node) {
	if (node.getLeft() == null) return node;
	else return findMin(node.getLeft());
    }
    
    /**
     * Returns the height of this Binary Search Tree.
     * 
     * @return the height of the binary search tree.
     */
    public int height() {
	return root.getHeight();
    }

    /**
     * Returns a list containing all of the elements in this Binary Search Tree
     * using tree inOrder traversal.
     * 
     * @return a list containing all of the elements in this binary search tree
     *         in inorder.
     */
    public List<T> getInfixOrder() {
	List<T> result = new ArrayList<T>();
	inOrder(result, root);
	return result;
    }
    
    @Complexity (
         basic_operation = "list.add", 
         N = "No. elements in tree", 
         number_of_steps = "N",
         big_O = "N"
    )
    private void inOrder(List<T> list, Node current) {
	if (current != null) {
	    inOrder(list, current.getLeft());
	    list.add(current.getData());
	    inOrder(list, current.getRight());
	}
    }
    
    public String toString() {
	return toString(root); 
    }
    
    @Complexity (
         basic_operation = "current.getData() / current.getHeight()", 
         N = "No. of elements in tree", 
         number_of_steps = "N",
         big_O = "N"
    )
    private String toString(Node current) {
	if (current == null) return "_";
	else return "(" + toString(current.getLeft()) + "<" + 
		 current.getData() + ":" + current.getHeight() + ">" +
		toString(current.getRight()) + ")";
    }
}