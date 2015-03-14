package us.vergara.queues.impl;

import org.junit.Test;

import us.vergara.queues.api.GenericQueue;

public class CostOnDeQueueQueueLoadTest {

	public static int TOTAL_ITEMS = 10000;

	@Test
	public void test() {
		Long startTime = System.currentTimeMillis();
		GenericQueue<Object> queue = new CostOnDeQueueQueue<Object>();
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
		System.out.println("CostOnDeQueueQueueLoadTest total milliseconds: " + (endTime-startTime));
	}
}
