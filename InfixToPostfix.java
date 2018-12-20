import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * 
 * The InfixToPostfix class is called in order for each line's equation in the .txt file to be converted into postfix notation.
 * Each method aids in evaluating whether each character is an operator or operand.
 *  Other methods check if the string represents a valid equation.
 *
 */

public class InfixToPostfix 
{
	private PrintWriter pw;
	private String infix;
	
	/**
	 * InfixToPostfix constructor initializes a PrintWriter object derived from the one recieved from the main method
	 * and an infix object containing the String data from the infix.txt file contained in the main method.
	 */
	
	public InfixToPostfix(PrintWriter pw, String myInfix) 
	{
		this.pw = pw;
		infix = myInfix;
	}
	

	/**
	 * toPostfix method creates a stack containing the postfix notation derived from the infix notation provided in the infix.txt file
	 * Three ObjectStack objects are created in this method: an operator stack, an operand stack, and a stack which will house the postfix notation.
	 * While analyzing the string, if the character under consideration is to be an integer value, then it is pushed onto the operand stack.
	 * If the character under consideration is to be considered a character, then it is placed on the operator stack.
	 * Within the method exist loops to determine the precedence of each operator in the string; operators are pushed onto the operand stack depending on the precedence.
	 * The method has a countermeasure programmed into it to spot an erroneous equation with two operands standing next to each other.
	 * @return the method returns an ObjectStact stack containing the postfix conversion of the originial infix equation.
	 */
	
	public ObjectStack toPostfix() 
	{
		ObjectStack operator = new ObjectStack();
		ObjectStack operand = new ObjectStack();
		ObjectStack returnPostfix = new ObjectStack();
		
		pw.println("Infix: " + infix);
		System.out.println("Prefix " + infix);
		for (int i = 0; i < infix.length(); i++) 
		{
			char op = infix.charAt(i); 
			
			if(op >= '0' && op <= '9') 
			{
				if(!operand.isEmpty()) 
				{
					if((priority(infix.charAt(i-1)) == 0 && infix.charAt(i-2) >= '0' && infix.charAt(i-2) <= '9') || infix.charAt(i-1) >= '0' && infix.charAt(i-1) <= '9') 
					{
						pw.println("Adjacent Operand Error.");
						System.out.println("Adjacent Operand Error.");
						return returnPostfix;
					}
				}
				operand.push(new Integer(op-48));
			}
			
			else if(priority(op) != 0) 
			{
				if(priority(op) != 6 && !operator.isEmpty()) 
				{
					if(operatorErrorCheck(i) == false) return returnPostfix;
				}
				if(priority(op) == 7) 
				{
					if(extraParenthesisCheck(operator, operand) == false) return returnPostfix;
					
				}
				else if (!operator.isEmpty() && priority(((Character) operator.top()).charValue()) != 6 && (priority(op) <= priority(((Character) operator.top()).charValue())))
				{
						operand.push(((Character) operator.pop()).charValue());
						if(!operator.isEmpty() && priority(((Character)operator.top()).charValue()) != 6 && priority(((Character) operator.top()).charValue()) >= priority(op)) operand.push(operator.pop());
						operator.push(op);	
		        }
			
				else operator.push(op);
			}
		}
		
		if(missingParenthesisCheck(operator, returnPostfix) == false) return returnPostfix;
		formatPostfix(returnPostfix, operator, operand);
		printPostfix(returnPostfix, operator);
		return returnPostfix;
	}
	
	/**
	 * operatorErrorCheck method determines whether or not the string contains an erroneous equation as indicated by two operands standing next to each other.
	 * @return the method returns a boolean true or false - false if the equation is erroneous, true if it is valid.
	 * @param recieves the index variable from the toPostfix method to determine the contents of the infix string at certain sections
	 */
	
	public boolean operatorErrorCheck(int i)
	{
		int subtrahend = 1;
		while(i - subtrahend != 0) 
		{
			if(infix.charAt(i-subtrahend) == ' ') subtrahend += 1;
			else if(priority(infix.charAt(i-subtrahend)) == 0) break;
			else if(infix.charAt(i-subtrahend) == ')') subtrahend += 1;
			else if(priority(infix.charAt(i-subtrahend)) > 0) 
			{
				pw.println("Adjacent Operator Error");
				System.out.println("Adjacent Operator Error.");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * extraParenthesisCheck method determines whether or not the string contains an erroneous equation as indicated by too many ")" characters.
	 * @return the method returns a boolean true or false - false if the equation is erroneous, true if it is valid.
	 * @param the operator and operand stacks are received from the toPostfix method to check if the string contains and erroneous equation or not.
	 */
	
	public boolean extraParenthesisCheck(ObjectStack operator, ObjectStack operand)
	{
		if(operator.isEmpty()) 
		{
			pw.println("Extra Parenthesis Error");
			System.out.println("Extra Parenthesis Error.");
			return false;
		}
		while(priority((char) operator.top()) != 6) 
		{
			operand.push(((Character) operator.pop()).charValue());
			if(operator.isEmpty()) 
			{
				pw.println("Extra Parenthesis Error");
				System.out.println("Extra Parenthesis Error.");
				return false;
			}
		}
		operator.pop();
		return true;
	}
	
	/**
	 * missingParenthesisCheck method determines whether or not the string contains an erroneous equation as indicated by too many "(" characters.
	 * @return the method returns a boolean true or false - false if the equation is erroneous, true if it is valid.
	 * @param the method receives the operator and returnPostfix stacks from the toPostfix method to determine if the string has an erroneous equation or not.
	 */
	
	public boolean missingParenthesisCheck(ObjectStack operator, ObjectStack returnPostfix)
	{
		while(!operator.isEmpty()) 
		{
			if(priority((((Character) operator.top()).charValue())) == 6) 
			{
				returnPostfix.clear();
				pw.println("Missing Parenthesis Error");
				System.out.println("Missing Parenthesis Error.");
				return false;
			}
			else returnPostfix.push(operator.pop());
		}
		return true;
	}
	
	/**
	 * formatPostfix method reformats the postfix string after going through the toPostfix method and the error checks
	 * @param the method is to receive all three ObjectStack objects from the toPostfix method.
	 */
	
	public void formatPostfix(ObjectStack returnPostfix, ObjectStack operator, ObjectStack operand) 
	{
		while(!returnPostfix.isEmpty()) operator.push(returnPostfix.pop());
		if(!operator.isEmpty() && priority(((Character) operator.top()).charValue()) >= 3) operand.push(operator.pop());
		while(!operator.isEmpty()) returnPostfix.push(operator.pop());
		while(!returnPostfix.isEmpty()) operand.push(returnPostfix.pop());
		while(!operand.isEmpty()) operator.push(operand.pop());
	}
	
	/**
	 * printPostfix method pushes all of the values from the operator stack onto the returnPostfix stack and prints its contents to the monitor and csis.txt file.
	 * @param the method is to receive the returnPostfix and operator stacks.
	 */
	
	public void printPostfix(ObjectStack returnPostfix, ObjectStack operator) 
	{
		pw.print("Postfix: ");
		System.out.print("Postfix: ");
		
		while(!operator.isEmpty()){
			returnPostfix.push(operator.top());
			pw.print(operator.top());
			System.out.print(operator.pop());
		}
		
		pw.println();
		System.out.println();
	}
	

	/**
	 * Each character from the string is given a specific priority based on the PEMDAS hierarchy.
	 * @return the method returns an integer value that represents the character's priority; the greater the value, the greater the precedence. 
	 * @param op represents an individual character from the equation of the string from the .txt file. 
	 */
	
	private int priority(char op) {
		switch(op) {
		case ')': return 7;
		case '(': return 6;
		case '^': return 5;
		case '*': return 4;
		case '/': return 3;
		case '+': 
		case '-': return 1;
		default : return 0;
		}
	}
}
	
