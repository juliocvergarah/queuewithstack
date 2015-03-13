package us.vergara.queues.api;

public interface Queue extends GenericQueue<Object> {

	/**
	 * Add object to the tail of the queue
	 */
	public void addBack(Object object);
	
	/**
	 * Gets' and Removes the object that is the head of the queue
	 * @return
	 */
	public Object removeFront();
}
