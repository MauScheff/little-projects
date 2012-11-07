public interface PostfixCalculator {

   /**
    * Evaluates the given postfix notation expression string. 
    * @param postfix_notation_expression
    * @return value of the given expression.
    * @throws IllegalArgumentException if the given string
    *   is not a valid postfix notation expression.
    */  
   double evaluate (String postfix_notation_expression) throws IllegalArgumentException;
}
