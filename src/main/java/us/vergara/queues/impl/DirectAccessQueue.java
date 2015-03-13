package us.vergara.queues.impl;

import java.util.Stack;

import us.vergara.queues.api.GenericQueue;

/**
 * Queue implementation where we are using the inherited methods from the List interface
 * 
 * @author jvergara
 *
 * @param <T> the type of object required
 */
public class DirectAccessQueue<T> implements GenericQueue<T>{

	private Stack<T> stack = new Stack<T>();
	
	/**
	 * @see GenericQueue#addBack(Object)
	 */
	public void addBack(T t) {
		if (t == null) {
			throw new IllegalArgumentException("Inserting nulls is not allowed");
		}
		stack.add(t);
	}

	/**
	 * @see GenericQueue#removeFront()
	 */
	public T removeFront() {
		if (stack.isEmpty()) {
			throw new RuntimeException("No more items in queue");
		}
		T t2 = stack.get(0);
		stack.remove(0);
		return t2;
	}

}
