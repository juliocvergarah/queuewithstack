package us.vergara.queues.impl;

import us.vergara.queues.api.GenericQueue;

/**
 * Provides a synchronizing wrapper around the {@link GenericQueue}.
 * 
 * @author jvergara
 *
 * @param <T> The type of objects to store
 */
public class SynchronizedGenericQueueWrapper<T> implements GenericQueue<T> {

	// Collection to be synchronized
	protected final GenericQueue<T> genericQueue;
	// Object on which to synchronize
	protected Object mutex;     
	
	public SynchronizedGenericQueueWrapper(GenericQueue<T> genericQueue) {
		this(genericQueue, new Object());
	}
	
	public SynchronizedGenericQueueWrapper(GenericQueue<T> genericQueue, Object mutex) {
		super();
		this.genericQueue = genericQueue;
		this.mutex = mutex;
	}
	
	public void addBack(T t) {
		synchronized (mutex) {
			genericQueue.addBack(t);
		}
	}
	
	public T removeFront() {
		synchronized (mutex) {
			return genericQueue.removeFront();
		}
	}

}
