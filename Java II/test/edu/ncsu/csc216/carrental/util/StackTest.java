package edu.ncsu.csc216.carrental.util;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * class to test stack class
 * @author mmackrell
 *
 */
public class StackTest {

	/**
	 * car for testing 
	 */
	private Car testDrive1;
	/**
	 * car for testing
	 */
	private Car testDrive2;
	/**
	 * car for testing
	 */
	private Car testDrive3;
	/**
	 * stack for testing
	 */
	private Stack<Car> pile;
	

	/**
	 * tests the isempty method
	 */
	@Test
	public void testIsEmpty() {
		pile = new Stack<Car>();
		assertEquals(true, pile.isEmpty());
	}

	/**
	 * tests the peek method
	 */
	@Test
	public void testPeek() {
		//set up the required objects
		try {
			testDrive1 = new Car("f0011", "Suzuki", "GXS-r 750", "Blue/White");
		} catch (InvalidIDException e) {
			return;
		}
		pile = new Stack<Car>();
		
		//push a car to the stack and see if peek lets you see it
		pile.push(testDrive1);
		assertEquals("f0011:  Suzuki GXS-r 750 (Blue/White)", pile.peek().toString());
	}

	/**
	 * tests the pop method
	 */
	@Test
	public void testPop() {
		
		//set up the required objects
		try {
			testDrive1 = new Car("f0011", "Suzuki", "GXS-r 750", "Blue/White");
			testDrive2 = new Car("f0022", "Yamaha", "R6", "Blue/Black");
			testDrive3 = new Car("f0033", "Ducati", "1199", "Red");
		} catch (InvalidIDException e) {
			return;
		}
		
		pile = new Stack<Car>();
		
		//push three cars to the stack
		pile.push(testDrive1);
		pile.push(testDrive2);
		pile.push(testDrive3);
		
		//pop off the top one and see if the second one is now at the top
		pile.pop();
		
		assertEquals("f0022:  Yamaha R6 (Blue/Black)", pile.peek().toString());
	}

	/**
	 * tester for the push method
	 */
	@Test
	public void testPush() {
		//set up the required objects
		try {
			testDrive1 = new Car("f0011", "Suzuki", "GXS-r 750", "Blue/White");
			testDrive2 = new Car("f0022", "Yamaha", "R6", "Blue/Black");
			testDrive3 = new Car("f0033", "Ducati", "1199", "Red");
		} catch (InvalidIDException e) {
			return;
		}
		
		pile = new Stack<Car>();
		
		//push three cars to the stack
		pile.push(testDrive1);
		pile.push(testDrive2);
		pile.push(testDrive3);
		
		//Make sure the top one is the last one that we pushed
		
		assertEquals("f0033:  Ducati 1199 (Red)", pile.peek().toString());
	}

}
