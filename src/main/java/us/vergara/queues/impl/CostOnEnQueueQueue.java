package us.vergara.queues.impl;

import java.util.Stack;

import us.vergara.queues.api.GenericQueue;

/**
 * Queue implementation where the en-queueing is expensive, but de-queueing is cheap. 
 * Moves items twice on enqueue.
 * 
 * @author jvergara
 *
 * @param <T> the type of obejct required
 */
public class CostOnEnQueueQueue<T> implements GenericQueue<T>{

	/**
	 * Stack allways hold all the data in it.
	 */
	private Stack<T> stackAsQueue = new Stack<T>();
	private Stack<T> tempStack = new Stack<T>();
	
	/**
	 * @see GenericQueue#addBack(Object)
	 */
	public void addBack(T t) {
		if (t == null) {
			throw new IllegalArgumentException("Inserting nulls is not allowed");
		}
		while (!stackAsQueue.isEmpty()) {
			tempStack.push(stackAsQueue.pop());
		}
		stackAsQueue.push(t);
		while (!tempStack.isEmpty()) {
			stackAsQueue.push(tempStack.pop());
		}
	}

	/**
	 * @see GenericQueue#removeFront()
	 */
	public T removeFront() {
		if (stackAsQueue.isEmpty()) {
			throw new RuntimeException("No more items in queue");
		}
		return stackAsQueue.pop();
	}

}
