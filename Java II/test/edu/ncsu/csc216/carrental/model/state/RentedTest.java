package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
/**
 * class to test the rented method
 * 
 * @author mmackrell
 *
 */
public class RentedTest {

	/**
	 * customer used for testing
	 */
	private Customer jay;
	/**
	 * car for testing
	 */
	private Car kCar;
	/**
	 * rental state unit for testing
	 */
	private RentalState rep = new OutForRepair();
	/**
	 * rental state unit for testing
	 */
	private Rented rent;
	/**
	 * rental state manager tester
	 */
	private RentalStateManager r; // null pointer probably
	/**
	 * rental state unit for testing
	 */
	private RentalState det = new OutForDetail();

	/**
	 * tests the rent car method
	 */
	@Test
	public void testRentCar() {
		rent = new Rented(jay);
		kCar = new Car("f0923", "Mikes", "Car", "Red");
		kCar.setState(rent);

		try {
			rent.rentCar(r);

		} catch (IllegalStateException i) {
			assertEquals(rent, kCar.getState());
		}
	}

	/**
	 * tests the return car method
	 */
	@Test
	public void testReturnCar() {
		rent = new Rented(jay);
		kCar = new Car("f0923", "Mikes", "Car", "Red");
		kCar.setState(rent);
		r = new RentalStateManager() {

			@Override
			public void processReturn(boolean problem) {
				// empty method
			}

			@Override
			public void processRepaired() {
				// empty method

			}

			@Override
			public void processRental() {
				// empty method

			}

			@Override
			public void processNewCar(String id, String make, String model,
					String color) {
				// empty method

			}

			@Override
			public void processDetailed() {

				// empty method
			}
		};

		r.processReturn(false);
		kCar.setState(det);
		assertEquals(det, kCar.getState());
	}

	/**
	 * tests the report problem method
	 */
	@Test
	public void testReportProblem() {
		rent = new Rented(jay);
		kCar = new Car("f0923", "Mikes", "Car", "Red");
		kCar.setState(rent);
		r = new RentalStateManager() {

			@Override
			public void processReturn(boolean problem) {
				// empty method

			}

			@Override
			public void processRepaired() {

				// empty method
			}

			@Override
			public void processRental() {
				// empty method

			}

			@Override
			public void processNewCar(String id, String make, String model,
					String color) {
				// empty method

			}

			@Override
			public void processDetailed() {
				// empty method

			}
		};

		r.processReturn(true);
		kCar.setState(rep);
		assertEquals(rep, kCar.getState());
	}

	/**
	 * tests the detail done method
	 */
	@Test
	public void testDetailDone() {
		rent = new Rented(jay);
		kCar = new Car("f0923", "Mikes", "Car", "Red");
		kCar.setState(rent);

		try {
			rent.detailDone(r);

		} catch (IllegalStateException i) {
			assertEquals(rent, kCar.getState());
		}
	}

	/**
	 * tests the repair done method
	 */
	@Test
	public void testRepairDone() {
		rent = new Rented(jay);
		kCar = new Car("f0923", "Mikes", "Car", "Red");
		kCar.setState(rent);

		try {
			rent.repairDone(r);

		} catch (IllegalStateException i) {
			assertEquals(rent, kCar.getState());
		}
	}

	/**
	 * tests the rental info method
	 */
	@Test
	public void testRentalInfo() {

		jay = new Customer("jay", "cutler", "22-2174");
		rent = new Rented(jay);
		assertEquals("(j cutler)", rent.rentalInfo());
	}

	/**
	 * tests the get customer method
	 */
	@Test
	public void testGetCustomer() {

		jay = new Customer("jay", "cutler", "22-2174");
		rent = new Rented(jay);
		assertEquals("22-2174:  jay cutler", rent.getCustomer().toString());
	}

}
