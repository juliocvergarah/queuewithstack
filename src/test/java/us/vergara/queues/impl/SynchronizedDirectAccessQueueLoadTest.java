package us.vergara.queues.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import us.vergara.queues.api.GenericQueue;
import us.vergara.queues.impl.SynchronizedCostOnEnQueueQueueLoadTest.TestRunnable;

public class SynchronizedDirectAccessQueueLoadTest {

	public static int TOTAL_ITEMS = 10000;
	public static int NUMBER_OF_THREADS = 10;

	@Test
	public void test() {
		Long startTime = System.currentTimeMillis();
		GenericQueue<Object> queue = new SynchronizedGenericQueueWrapper<Object>(new DirectAccessQueue<Object>());
		int itemsConsumed = 0;
		for(int i = 0; i < TOTAL_ITEMS; i++) {
			queue.addBack(new Object());
			// consume every fifth for testing
			if ((i%5) == 0) {
				queue.removeFront(); itemsConsumed++;
			}
		}
		// consume rest until empty
		while (itemsConsumed < TOTAL_ITEMS) {
			queue.removeFront(); itemsConsumed++;
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("SynchronizedDirectAccessQueueLoadTest total milliseconds: " + (endTime-startTime));
	}
	@Test
	public void test_threads() throws InterruptedException {
		Long startTime = System.currentTimeMillis();
		GenericQueue<Object> queue = new SynchronizedGenericQueueWrapper<Object>(new DirectAccessQueue<Object>());
		final AtomicInteger itemsConsumed = new AtomicInteger(0);
		Thread threads[] = new Thread[NUMBER_OF_THREADS];
		for (int i = 0; i < threads.length; i++) {
			int numberOfItems = TOTAL_ITEMS / NUMBER_OF_THREADS;
			threads[i] = new Thread(new TestRunnable(numberOfItems, itemsConsumed, queue));
			threads[i].start();
			threads[i].join();
		}
		Long endTime = System.currentTimeMillis();
		System.out.println("SynchronizedDirectAccessQueueLoadTest total milliseconds (" + NUMBER_OF_THREADS + " threads): " + (endTime-startTime));
	}
	
	public class TestRunnable implements Runnable {
		private int numberOfItems;
		private AtomicInteger itemsConsumed;
		private GenericQueue<Object> queue; 

		public TestRunnable(int numberOfItems, AtomicInteger itemsConsumed, GenericQueue<Object> queue) {
			super();
			this.numberOfItems = numberOfItems;
			this.itemsConsumed = itemsConsumed;
			this.queue = queue;
		}

		@Override
		public void run() {
			for(int i = 0; i < numberOfItems; i++) {
				queue.addBack(new Object());
				// consume every fifth for testing
				if ((i%5) == 0) {
					queue.removeFront(); itemsConsumed.incrementAndGet();
				}
			}
			while (itemsConsumed.get() < numberOfItems) {
				queue.removeFront(); itemsConsumed.incrementAndGet();
			}
		}
		
	}

}
