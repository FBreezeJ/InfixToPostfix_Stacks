import java.lang.*;
import java.io.*;

/**
 * The EvalPostfix class manipulates the data from each stack of the returnPostfix stack that was returned in the InfixToPostfix class.
 * The class finds the solution of the postfix notation. 
 */

public class EvalPostfix 
{
	private PrintWriter pw;
	private ObjectStack postfix;
	
	/**
	 * EvalPostfix constructor initializes a PrintWriter object derived from the one recieved from the main method
	 * and a new postfix Object containing the data from the returnPostfix Object sent from the main method.
	 */
	
	public EvalPostfix(PrintWriter pw, ObjectStack postfix) {
		this.pw = pw;
		this.postfix = postfix;
	}
	
	/**
	 * The Evaluate method solves the equation contained in the postfix notation.
	 * If the value contained in the uppermost object of the infix stack is an integer, then the value is pushed onto a new operand stack.
	 * If the value contained in the uppermost object of the infix stack is a character, then the two uppermost integer values on the operand stack are manipulated depending on the character.
	 * From there, the new value is placed back on top of the operand stack.
	 * However, a counter value of type integer determines whether the uppermost integer values on the stack were the first two operands in the postfix equation or not.
	 * If they were, then the top value operates on the next top operand; if they are not the first two operands in the equation, then the second highest value operates on the top value. 
	 * This only applies to subtraction, division, and exponentiation, however. 
	 */
	
	public void Evaluate() {
		ObjectStack newPostfix = new ObjectStack();
		ObjectStack operand = new ObjectStack();
		int postfix1 = 0, 
			postfix2 = 0, 
			counter = 0;
		
		while(!postfix.isEmpty()) newPostfix.push(postfix.pop());
		while(!newPostfix.isEmpty()) {
			if(newPostfix.top() instanceof Integer) {
				operand.push(newPostfix.pop());
				counter++;
			}
			else if(((Character) newPostfix.top()).charValue() == '-') {
				newPostfix.pop();
				postfix1 = (int) operand.pop();
				postfix2 = (int) operand.pop();
				if(counter > 2) operand.push(new Integer(postfix2 - postfix1));
				else operand.push(new Integer(postfix1 - postfix2));
			
			}
			else if(((Character) newPostfix.top()).charValue() == '+') {
				newPostfix.pop();
				postfix1 = ((Integer) operand.pop()).intValue();
				postfix2 = ((Integer) operand.pop()).intValue();
				operand.push(postfix1 + postfix2);
			}
			else if(((Character) newPostfix.top()).charValue() == '/') {
				newPostfix.pop();
				postfix1 = (int) operand.pop();
				postfix2 = (int) operand.pop();
				if(counter > 2) operand.push(new Integer(postfix2 / postfix1));
				else operand.push(new Integer(postfix1 / postfix2));
			}
			else if(((Character) newPostfix.top()).charValue() == '*') {
				newPostfix.pop();
				postfix1 = ((Integer) operand.pop()).intValue();
				postfix2 = ((Integer) operand.pop()).intValue();
				operand.push(new Integer(postfix1 * postfix2));
			}
			else if(((Character) newPostfix.top()).charValue() == '^') {
				newPostfix.pop();
				postfix1 = (int) operand.pop();
				postfix2 = (int) operand.pop();
				if(counter > 2) operand.push((int)Math.pow(postfix2, postfix1));
				else operand.push((int)Math.pow(postfix1, postfix2));
			}
		
		
		}
		System.out.println("Solution: " + operand.top());
		pw.println("Solution: " + operand.top());
	}
}
