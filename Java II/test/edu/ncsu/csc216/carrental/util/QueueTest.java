package edu.ncsu.csc216.carrental.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * queue tester class
 * @author mmackrell
 *
 */
public class QueueTest {

	/**
	 * car for testing
	 */
	private Car car1;

	/**
	 * car for testing
	 */
	private Car car2;

	/**
	 * car for testing
	 */
	private Car car3;

	/**
	 * queue for testing
	 */
	private Queue<Car> q;

	/**
	 * tester for the add method
	 */
	@Test
	public void testAdd() {
		// set up the required objects
		q = new Queue<Car>();
		try {
			car1 = new Car("k8701", "supafast", "car", "lightningbolt yellow");
		} catch (InvalidIDException e) {
			return;
		}

		// add a car and see if it gets added
		q.add(car1);
		assertEquals("k8701:  supafast car (lightningbolt yellow)", q.peek()
				.toString());

	}

	/**
	 * tester for the isempty method
	 */
	@Test
	public void testIsEmpty() {
		// set up the required objects
		try {
			car2 = new Car("s0222", "supahot", "car", "hotfiya red");
		} catch (InvalidIDException e) {
			return;
		}
		q = new Queue<Car>();

		// make sure the queue is empty
		assertEquals(true, q.isEmpty());

		// add a car and see if it is not empty
		q.add(car2);
		assertEquals(false, q.isEmpty());

	}

	/**
	 * tester for the remove method
	 */
	@Test
	public void testRemove() {
		// set up the required objects
		try {
			car1 = new Car("k8701", "supafast", "car", "lightningbolt yellow");
			car2 = new Car("s0222", "supahot", "car", "hotfiya red");
			car3 = new Car("t3333", "supacool", "car", "chillchillin blue");
		} catch (InvalidIDException e) {
			return;
		}

		q = new Queue<Car>();

		// add the cars to the queue
		q.add(car1);
		q.add(car2);
		q.add(car3);

		// remove one and make sure the next one is correct
		assertEquals("k8701:  supafast car (lightningbolt yellow)", q.peek()
				.toString());
		q.remove();
		assertEquals("s0222:  supahot car (hotfiya red)", q.peek().toString());
	}

	/**
	 * tester for the peek method
	 */
	@Test
	public void testPeek() {
		// set up the required objects
		try {
			car2 = new Car("s0222", "supahot", "car", "hotfiya red");
		} catch (InvalidIDException e) {
			return;
		}
		q = new Queue<Car>();

		// add a car and make sure the peek method works properly
		q.add(car2);
		assertEquals("s0222:  supahot car (hotfiya red)", q.peek().toString());

	}

}
