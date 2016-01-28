package edu.ncsu.csc216.carrental.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * class to test the customer class
 * 
 * @author mmackrell
 *
 */
public class CustomerTest {

	/**
	 * customer for testing
	 */
	private Customer sharp;

	/**
	 * customer for testing
	 */
	private Customer equals;

	/**
	 * the set up method
	 * 
	 * @throws Exception
	 *             if something breaks
	 */
	@Before
	public void setUp() throws Exception {
		sharp = new Customer("Zac", "Sharp", "69-6969");

	}

	/**
	 * tests the toString method of Customer
	 */
	@Test
	public void testToString() {
		sharp = new Customer("Zac", "Sharp", "69-6069");
		equals = new Customer("Zac", "Sharp", "69-6969");

		try {
			@SuppressWarnings("unused") // this will create an invalidIDexception.
			//so it can't be used.
			Customer broken = new Customer("", "", "69-6969");
		} catch (InvalidIDException f) {
			assertEquals(false, sharp.equals(equals));
		}

		try {
			Customer broken = new Customer("87542#@(%(", "#*$*#52", "69-6969");
			Customer broke = new Customer("Zac", "Sharp@#", "69-6969");
			Customer broked = new Customer("Zac", "Sharp", "6976969");
			broken.toString();
			broke.toString();
			broked.toString();

		} catch (IllegalArgumentException j) {
			assertEquals(false, sharp.equals(equals));
		}

	}

}
