public class PolishCalculator implements PostfixCalculator {
    
    public class Stack {

	public class Node {
	    private double data = 0;
	    private Node next = null;

	    public Node(double data) {
		this.data = data;
		next = null;
	    }
	    
	    public void setNext(Node nextNode) {
		this.next = nextNode;
	    }

	    public Node getNext() {
		return this.next;
	    }
	    
	    public Double getData() {
		return this.data;
	    }
	}

	private Node top;
	private int size;
	
	public Stack() {
	    top = null;
	    size = 0;
	}
	
	public void push(double data){
	    Node newNode = new Node(data);
	    if (top == null) {
		top = newNode;
	    } else {
		newNode.setNext(top);
		top = newNode;
	    }
	    size++;
	}

	public double pop(){
	    Node result = top;
	    top = top.getNext();
	    return result.getData();
	}

	public int size() {
	    return this.size;
	}
    }

    @Complexity (
         basic_operation = "Push/Pop", 
         N = "expression.length", 
         number_of_steps = "N * 2",
         big_O = "N"
    )
    public double evaluate (String postfix_notation_expression) throws IllegalArgumentException {
	Stack yourStack = new Stack();
	String[] expression = postfix_notation_expression.split(" ");
	for (int j = 0; j < expression.length; j++) {

	    if (expression[j].equals("+")) {
		if (yourStack.size() < 2) {
		    throw new IllegalArgumentException(" need more tokens");
		} 
		yourStack.push(yourStack.pop() + yourStack.pop());
	    } else if (expression[j].equals("-")) {
		if (yourStack.size() < 2) {
		    throw new IllegalArgumentException(" need more tokens");
		} 
		double a = yourStack.pop();
		double b = yourStack.pop();
		yourStack.push(b - a);

	    } else if (expression[j].equals("*")) {
		if (yourStack.size() < 2) {
		    throw new IllegalArgumentException(" need more tokens");
		} 
		yourStack.push(yourStack.pop() * yourStack.pop());

	    } else if (expression[j].equals("/")) {
		if (yourStack.size() < 2) {
		    throw new IllegalArgumentException(" need more tokens");
		} 
		double a = yourStack.pop();
		double b = yourStack.pop();
		yourStack.push(b / a);

	    } else {
		try {
		    double number = Double.parseDouble(expression[j]);
		    yourStack.push(number);
		} catch (NumberFormatException e) {
		    throw new IllegalArgumentException(" illegal tokens");
		}
	    }
	}
	
	if (yourStack.size() != 1) {
	    throw new IllegalArgumentException(" too many tokens");
	}

	return yourStack.pop();
    }

}