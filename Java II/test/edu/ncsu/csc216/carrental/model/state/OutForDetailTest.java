package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;

/**
 * class to test the out for detail finite state
 * 
 * @author mmackrell
 *
 */
public class OutForDetailTest {

	/**
	 * car for testing
	 */
	private Car jCar;
	/**
	 * rental state unit for testing
	 */
	private RentalState det = new OutForDetail();
	/**
	 * rental state unit for testing
	 */
	private RentalState ava = new Available();
	/**
	 * rental state manager tester
	 */
	private RentalStateManager r; // null pointer probably


	/**
	 * tests to see if the proper exception is thrown
	 */
	@Test
	public void testRentCar() {
		jCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		jCar.setState(det);

		try {
			det.rentCar(r);

		} catch (IllegalStateException i) {
			assertEquals(det, jCar.getState());
		}
	}

	/**
	 * tests the return car method
	 */
	@Test
	public void testReturnCar() {
		jCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		jCar.setState(det);

		try {
			det.returnCar(r);

		} catch (IllegalStateException i) {
			assertEquals(det, jCar.getState());
		}
	}

	/**
	 * tests the report problem method
	 */
	@Test
	public void testReportProblem() {
		jCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		jCar.setState(det);

		try {
			det.reportProblem(r);

		} catch (IllegalStateException i) {
			assertEquals(det, jCar.getState());
		}
	}

	/**
	 * tests the detail done method
	 */
	@Test
	public void testDetailDone() {
		jCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		jCar.setState(det);

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
					String color)  {
				// empty method


			}

			@Override
			public void processDetailed() {
				// empty method

			}
		};

		det.detailDone(r);
		jCar.setState(ava);
		assertEquals(ava, jCar.getState());

	}

	/**
	 * tests the repair done method
	 */
	@Test
	public void testRepairDone() {
		jCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		jCar.setState(det);

		try {
			det.repairDone(r);

		} catch (IllegalStateException i) {
			assertEquals(det, jCar.getState());
		}
	}

	/**
	 * tests the rental info method
	 */
	@Test
	public void testRentalInfo() {
		jCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		jCar.setState(det);

		try {
			det.rentalInfo();

		} catch (IllegalStateException i) {
			assertEquals(det, jCar.getState());
		}
	}

}
