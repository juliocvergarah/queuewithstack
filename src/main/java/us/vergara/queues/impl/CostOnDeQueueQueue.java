package us.vergara.queues.impl;

import java.util.Stack;

import us.vergara.queues.api.GenericQueue;

/**
 * Queue implementation where the en-queueing is expensive, but de-queueing is cheap 
 * 
 * @author jvergara
 *
 * @param <T> the type of obejct required
 */
public class CostOnDeQueueQueue<T> implements GenericQueue<T>{

	/**
	 * Stack contains all items in reverse order
	 */
	private Stack<T> stack1 = new Stack<T>();
	
	/**
	 * Stack contains use for reversing order and only when it's empty
	 */
	private Stack<T> stack2 = new Stack<T>();
	
	/**
	 * @see GenericQueue#addBack(Object)
	 */
	public void addBack(T t) {
		if (t == null) {
			throw new IllegalArgumentException("Inserting nulls is not allowed");
		}
		stack1.add(t);
	}

	/**
	 * @see GenericQueue#removeFront()
	 */
	public T removeFront() {
		if (stack1.isEmpty() && stack2.isEmpty()) {
			throw new RuntimeException("No more items in queue");
		}
		while (stack2.isEmpty() || !stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		return stack2.pop();
	}

}
