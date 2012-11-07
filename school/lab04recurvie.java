public class PolishCalculator implements PostfixCalculaor {

	/**
	 * Evaluates the given postfix notation expression string. 
	 * @param postfix_notation_expression
	 * @return value of the given expression.
	 * @throws IllegalArgumentException if the given string
	 *   is not a valid postfix notation expression.
	 */  
    double evaluate (String postfix_notation_expression) throws IllegalArgumentException {
	return Double.parseDouble(evaluate(postfix_notation_expression));
    }
    
    String evaluate (String postifx_notation_expression) throws IllegalArgumentException {
	String[] expression = postfix_notation_expression.split(" ");
	if (expression.length == 1) { // Base Case
	    return expression[0];
	} else if (expression.length == 3) {
	    if (expression[2].equals("+")) {
		return expression[0] + expression[1];
	    } else if (expression[2].equals("-")) {
		return expression[0] - expression[1];
	    } else if (expression[2].equals("/")) {
		return expression[0] / expression[1];
	    } else if (expression[2].equals("*")) {
		return expression[0] * expression[1];
	    } else {
	      
	}else if (post.split(" ").length < 3) {
	    throw new IllegalArgumentException();
	} else {
	    int opIndex = Math.min(postfix_notation_expression.indexOf("+"));
	    return 
	}
    }
    
}