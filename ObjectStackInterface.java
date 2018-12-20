	/**
	 * ObjectStackInterface is the masterclass of the ObjectStack class.
	 * The interface determines which methods the ObjectStack class can implement and what their return values and parameters are. 
	 */
public interface ObjectStackInterface 
{
	/**
	 * ObjectStack class must implement a isEmpty method.
	 * @return isEmpty method must return a boolean value.
	 */
	
	public boolean isEmpty();
	
	/**
	 * ObjectStack class must implement a isFull method.
	 * @return isEmpty method must return a boolean value.
	 */
	
	public boolean isFull();
	
	/**
	 * ObjectStack class must implement a clear method.
	 */
	
	public void clear();
	
	/**
	 * ObjectStack class must implement a push method.
	 * @param push method must have an object as its parameter.
	 */
	
	public void push(Object o);
	
	/**
	 * ObjectStack class must implement a resize method.
	 * @param push method must have an integer as its parameter.
	 */
	
	public void resize(int size);
	
	/**
	 * ObjectStack class must implement a pop method.
	 * @return pop method must return an Object.
	 */
	
	public Object pop();
	
	/**
	 * ObjectStack class must implement a top method.
	 * @return pop method must return an Object.
	 */
	
	public Object top();
}
