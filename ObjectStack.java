
	/**
	 * The ObjectStack class is a subclass of the ObjectStackInterface interface.
	 *This class will carry objects to help store and manipulate data pertaining to the infix and postfix notations.
	 */

public class ObjectStack implements ObjectStackInterface 
{
	private Object[] item; 
	private int top; 
	

	/**
	 * ObjectStack constructor creates an ObjectStack object.
	 * Initializes an object array and the bottom of the stack, with a top of -1
	 */
	
	public ObjectStack() {
		item = new Object[1];
		top = -1; 
	}
	
	/**
	 * isEmptry method determines whether the top is the bottom value of -1
	 * @return returns a boolean true or false depending on where the top is in the stack.
	 */
	
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * isFull determines whether or not the stack is full depending on if the Object array in the constructor is full or not.
	 * @return returns a boolean true or false depending on whether the Object array is full or not
	 */
	
	public boolean isFull() {
		return top == item.length-1; 
	}
	
	/**
	 * Clear method initializes a new Object stack and turns its top value to -1 in order to 
	 * "clear" the contents of the stack being used currently.
	 */
	
	public void clear() {
		item = new Object[1];
		top = -1; 
	}
	
	/**
	 * push method stores an object in the stack and increments the value of the top integer by one.
	 * @param an object that stores an entity of any data type is to be stored in the Object array from the constructor
	 */
	
	public void push(Object o) {
		if (isFull()) resize(2 * item.length);
		item[++top] = o;
	}
	
	/**
	 * resize method contains an integer value from on of the other methods to create a new Object (it can be doubled or halved)
	 * @param integer value from push or pop method to initialize the size of a new Object array to be refilled with values.
	 */
	
	public void resize(int size) {
		Object[] temp = new Object[size];
		
		for (int i = 0; i <= top; i++) temp[i] = item[i];
		item = temp;
	}
	
	/**
	 * pop method deletes the value on the top of the stack and decrements the top value by one.
	 * If the top value is currently -1, then the system returns a message saying so and exits the program.
	 * If the Object array is 1/4 full, then the method resizes the array by sending a value half the current length of the array to the resize method.
	 */
	
	public Object pop() {
		if (isEmpty()) {
			System.out.println("Stack Underflow");
			System.exit(1);
		}
		Object temp = item[top];
		item[top--] = null;
		if (top == item.length / 4) resize(item.length / 2);
		return temp;
	}
	
	/**
	 * top determines whether or not the top value is -1; if it is, then the system notifies the user that the stack is empty and exits the system.
	 * If the stack is not empty, then the value stored in the top object is returned to the user.
	 * @return the object stored on the top of the Object array is returned.
	 */
	
	public Object top() {
		if (isEmpty()) {
			System.out.println("Stack Underflow");
			System.exit(1);
		}
		return item[top];
	}
}
