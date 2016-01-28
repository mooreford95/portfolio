package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Test;
import edu.ncsu.csc216.carrental.model.Car;
/**
 * class for testing available
 * @author mmackrell
 *
 */
public class AvailableTest {
	
	/**
	 * car for testing
	 */
	private Car mCar;
	/**
	 * rental state unit for testing
	 */
	private RentalState avail = new Available();
	/**
	 * rental state unit for testing
	 */
	private Rented rent;
	/**
	 * rental state manager tester
	 */
	private RentalStateManager r; // null pointer probably

	/**
	 * tests the rent car method
	 */
	@Test
	public void testRentCar() {

		mCar = new Car("f0923", "Mikes", "Car", "Red");
		mCar.setState(rent);
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
		mCar.setState(rent);
		assertEquals(rent, mCar.getState());
	}

	/**
	 * tests the return car method
	 */
	@Test
	public void testReturnCar() {

		mCar = new Car("f0923", "Mikes", "Car", "Red");
		mCar.setState(avail);

		try {
			avail.returnCar(r);

		} catch (IllegalStateException i) {
			assertEquals(avail, mCar.getState());
		}
	}

	/**
	 * tests the report problem method
	 */
	@Test
	public void testReportProblem() {

		mCar = new Car("f0923", "Mikes", "Car", "Red");
		mCar.setState(avail);

		try {
			avail.reportProblem(r);

		} catch (IllegalStateException i) {
			assertEquals(avail, mCar.getState());
		}

	}

	/**
	 * tests the detail done method
	 */
	@Test
	public void testDetailDone() {

		mCar = new Car("f0923", "Mikes", "Car", "Red");
		mCar.setState(avail);

		try {
			avail.detailDone(r);

		} catch (IllegalStateException i) {
			assertEquals(avail, mCar.getState());
		}

	}

	/**
	 * tests the repair done method
	 */
	@Test
	public void testRepairDone() {

		mCar = new Car("f0923", "Mikes", "Car", "Red");
		mCar.setState(avail);

		try {
			avail.repairDone(r);

		} catch (IllegalStateException i) {
			assertEquals(avail, mCar.getState());
		}

	}

	/**
	 * tests the rental info method
	 */
	@Test
	public void testRentalInfo() {

		mCar = new Car("f0923", "Mikes", "Car", "Red");
		mCar.setState(avail);

		try {
			avail.rentalInfo();

		} catch (IllegalStateException i) {
			assertEquals(avail, mCar.getState());
		}

	}

}
