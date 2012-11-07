import java.util.Comparator;

public class tester {
    
    public static void main(String[] args) {
	AVLTree<Integer> myTree = new AVLTree<Integer>();
	final Comparator<Integer> cmp = new Comparator<Integer>() {
	    public int compare(Integer arg0, Integer arg1) {
		return arg0.compareTo(arg1);
	    }
	};
	
	myTree.insert(9, cmp);
	myTree.insert(3, cmp);
	myTree.insert(2, cmp);
	myTree.insert(1, cmp);
	myTree.insert(12, cmp);
	myTree.insert(10, cmp);
	myTree.insert(21, cmp);
	myTree.insert(16, cmp);
	myTree.insert(11, cmp);
	myTree.insert(13, cmp);
	myTree.insert(16, cmp);
	System.out.println(myTree.toString());
    }
}