package edu.ncsu.csc216.carrental.model;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.carrental.model.state.OutForDetail;

/**
 * test for the car class
 * 
 * @author mmackrell
 *
 */
public class CarTest {
	/**
	 * car for testing
	 */
	private Car mikesCar;
	/**
	 * car for testing
	 */
	private Car testDummy;

	/*
	 * @Test public void testGetFleetNum() { fail("Not yet implemented"); }
	 */
	/**
	 * tester for the get Make method
	 */
	@Test
	public void testGetMake() {
		mikesCar = new Car("D1828", "Dodge", "Viper", "Silver");
		try{
			mikesCar = new Car("break", "broke", "broken", "wrong");
		}
		catch (InvalidIDException f) {
			assertEquals("Dodge", mikesCar.getMake());
		}
		
	}

	/**
	 * tester for the get model method
	 */
	@Test
	public void testGetModel() {
		// make sure the correct model is returned
		mikesCar = new Car("D1828", "Dodge", "Viper", "Silver");
		assertEquals("Viper", mikesCar.getModel());
	}

	/**
	 * tester for the get color method
	 */
	@Test
	public void testGetColor() {
		// make sure the correct color is returned
		mikesCar = new Car("D1828", "Dodge", "Viper", "Silver");
		assertEquals("Silver", mikesCar.getColor());
	}

	/**
	 * tester for the get state method
	 */
	@Test
	public void testGetState() {
		// make a car and set its state
		mikesCar = new Car("D1828", "Dodge", "Viper", "Silver");
		OutForDetail j = new OutForDetail();
		mikesCar.setState(j);
		assertEquals(j, mikesCar.getState());
	}

	/**
	 * tester for the toString Method
	 */
	@Test
	public void testToString() {
		// make a car and make sure the string of it is correct
		mikesCar = new Car("D1828", "Dodge", "Viper", "Silver");

		testDummy = new Car("D1827", "Dodge", "Viper", "Silver");
		assertEquals(false, mikesCar.equals(testDummy));
		// assertEquals("D1827","Dodge","Viper","Silver", mikesCar.toString());

	}

}
