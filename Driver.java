/**
 * Project Title: Stacks Lab
 * Project Description: This program reads equations from a .txt file in String format and converts the individual equations into their postfix counterparts.
 * 						The program also evaluates the postfix conversions and prints the infix, postfix, and solutions to the monitor and a different .txt file.
 * 						Countermeasures have been coded to take into account erroneous equations.
 * 
 *   Version or Date: May 9, 2018.
 *   How to Start the Project: Press run driver.
 *   Author: Forest Jenkins 
 *   Palomar ID: 010418184
 *   User Instructions: Enter the desired value at the opening menu. The value you enter will determine the conversion process. From there, enter a desired 
 *   					value for the data type you entered. 
 */

import java.util.*;
import java.io.*;

/**
 * CSIS 210
 * @author Forest Jenkins
 * The Driver class stores the main method, a PrintWriter object and a Scanner Object.
 * Further, its function is to scan each line from the .txt file and send its contents to another class to be converted into postfix notation.
 * After the postfix notation is returned to the main method, the notation is send to another class to be evaluated.
 */

public class Driver 
{
	
	/**
	 * The main method initializes a PrintWriter object that will write data to a .txt file, a Scanner object that will read data from a .txt file, 
	 * and two other objects that are derived from the InfixToPostfix and EvalPostfix classes.
	 * Each line of the infix file is read individually and manipulated in other classes.
	 * In the while loop, if the InfixToPostfix method recieves an erroneous equation, then the loop will skip evaluating it.
	 */
	
	public static void main(String[] args) throws IOException
	{
		PrintWriter pw = new PrintWriter(new FileWriter("csis_infix.txt"));
		File file = new File("infix.txt");
		Scanner fileScan = new Scanner(file);
		System.out.println(file.getAbsoluteFile());
		
		while(fileScan.hasNext()) {
			String buf = fileScan.nextLine();
			InfixToPostfix myInfix = new InfixToPostfix(pw, buf);
			ObjectStack postfix = new ObjectStack();
			postfix = myInfix.toPostfix();
			if(!postfix.isEmpty()) {
			EvalPostfix myPostfix = new EvalPostfix(pw, postfix);
			myPostfix.Evaluate();
			}
		}
		pw.close();
		fileScan.close();
	}	
}
