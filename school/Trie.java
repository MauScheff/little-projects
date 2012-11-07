import java.util.ArrayList;
import java.util.List;

public class Trie {

    private class Node {
	
	String word;
	int instances;
	Node[] children;

	/*
	 * Creates a new Node
	 */
	public Node() {

	    this.word = "";
	    this.instances = 0;
	    this.children = new Node[26];
	}

	private void increment() {
	    this.instances++;
	}

	private void decrement() {
	    this.instances--;
	}

	private void setWord(String word) {

	    if (this.word.length () == 0) {
		this.word = word;
	    }
	}
	
	public String getWord() {
	    return this.word;
	}

	public int getInstances() {
	    return this.instances;
	}
	
	/*
	 * @param firstLetter The letter specifying the node we want to descend to.
	 * @return The newly created node, or the child node if it was already there.
	 */
	public Node setChild(char firstLetter) {

	    firstLetter = Character.toUpperCase(firstLetter);

	    if (this.children[firstLetter - 'A'] == null) {
		this.children[firstLetter - 'A'] = new Node();
	    }

	    return this.children[firstLetter - 'A'];
	}

	/*
	 * @param firstLetter the letter specifing the child we want to go to.
	 * @return the Corresponding child
	 */
	public Node getChild(char firstLetter) {
	    if (!Character.isLetter(firstLetter)) return null;
	    firstLetter = Character.toUpperCase(firstLetter);
	    return children[firstLetter - 'A'];
	}

	/*
	 * @return A List containing all the word formed by children of this node.
	 */
	public List<Node> getChildWords() {

	    return getChildWords(new ArrayList<Node>());
	}
	
	/*
	 * Helper method for getChildWords()
	 */
	private List<Node> getChildWords(List<Node> result) {

	    for (int j = 0; j < this.children.length; j++) {

		Node current = children[j];
		if (current != null) {

		    if (current.getInstances() > 0) {
			result.add(current);
		    }
		    
		    result.addAll(this.children[j].getChildWords());
		}
	    }
	    
	    return result;
	}
	
	/*
	 * @return the number of words ending in this node, and the word itself.
	 */
	public String toString() {
	    return "(" + this.word + " : " + this.instances + ")"; 
	}
    }
    
    Node root;
    
    /*
     * Creates a new Trie
     */
    public Trie() {
	root = new Node();
    }

    /*
     * @param word The word to be inserted in the Trie
     */
    public void insert(String word) {

	Node lastNode = insert(root, word);
	lastNode.increment();
	lastNode.setWord(word);
    }
    
    /*
     * Helper method for insert(word)
     */
    private Node insert(Node current, String word) {
	
	if (word.length() == 0) return current;
	return insert(current.setChild(word.charAt(0)), 
		      word.substring(1));
    }

    /*
     * @return true if the deletion was succesful, false otherwise.
     */
    public boolean delete(String word) {

	return delete(root, word);
    }

    /*
     * Helper method for delete(word)
     */
    private boolean delete(Node current, String word) {

	Node result = find(root, word);
	if (result == null) return false;

	result.decrement();
	return true;
    }

    /*
     * @return The number of times a word was found
     */
    public int find(String word) {
	
	Node finalNode = find(root, word);
	if (finalNode == null) return 0;
	return finalNode.getInstances();
    }

    /*
     * Helper method for find(word)
     */
    private Node find(Node current, String word) {
	
	if (word.length() == 0) {
	    return current;
	}
	
	Node child = current.getChild(word.charAt(0));
	if (child == null) {
	    return null;
	}
	
	return find(child, word.substring(1));
    }

    /*
     * Can be used for auto-complete, returns children
     * sorted lexicographicaly.
     * @param prefix the prefix of a word of wich you want to know it's children.
     * @return A String[] containing the children of the prefix.
     */
    public String[] getChildren(String prefix) {

	Node finalNode = find(root, prefix);
	if (finalNode == null) return new String[0];
	List<Node> list = finalNode.getChildWords();
	String[] result = new String[list.size()];

	int i=0;
	for (Node node : list) {
	    result[i++] = node.toString();
	}

	return result;
    }

    /*
     * @return A Strign representation of the tree.
     */
    public String toString() {

	String result = "";
	List<Node> list = root.getChildWords();

	for (Node current : list) {
	    result += current + "\n";
	}

	return result;
    }

}