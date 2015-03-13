package us.vergara.queues.impl;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import us.vergara.queues.api.GenericQueue;

public class CostOnEnQueueQueueTest {

	@Test(expected=IllegalArgumentException.class)
	public void testAddBack_null() {
		GenericQueue<Object> queue = new CostOnEnQueueQueue<Object>();
		queue.addBack(null);
	}
	
	@Test(expected=RuntimeException.class)
	public void testRemoveFront_empty() {
		GenericQueue<Object> queue = new CostOnEnQueueQueue<Object>();
		queue.removeFront();
	}
	
	@Test
	public void testAddBack_add2_sequential() {
		GenericQueue<Object> queue = new CostOnEnQueueQueue<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		queue.addBack(o1);
		queue.addBack(o2);
		assertThat(queue.removeFront(), is(equalTo(o1)));
		assertThat(queue.removeFront(), is(equalTo(o2)));
		// it be nice if queue interface would have a size method, so we can check how many are in the queue
		try {
			queue.removeFront();
			fail("Should throw an excection");
		}
		catch(Exception e) {
			assertTrue(e instanceof RuntimeException);
		}
	}

	@Test
	public void testAddBack_add2_interleaved() {
		GenericQueue<Object> queue = new CostOnEnQueueQueue<Object>();
		Object o1 = new Object();
		Object o2 = new Object();
		queue.addBack(o1);
		assertThat(queue.removeFront(), is(equalTo(o1)));
		queue.addBack(o2);
		assertThat(queue.removeFront(), is(equalTo(o2)));
		// it be nice if queue interface would have a size method, so we can check how many are in the queue
		try {
			queue.removeFront();
			fail("Should throw an excection");
		}
		catch(Exception e) {
			assertTrue(e instanceof RuntimeException);
		}
	}
}
