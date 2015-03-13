package us.vergara.queues.api;

public interface GenericQueue<T> {

	/**
	 * Add object to the tail of the queue
	 */
	public void addBack(T t);
	/**
	 * Gets' and Removes the object that is the head of the queue
	 * @return
	 */
	public T removeFront();
}
