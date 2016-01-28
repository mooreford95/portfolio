package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;

/**
 * class to test the out for repair state
 * @author mmackrell
 *
 */
public class OutForRepairTest {
	/**
	 * car for testing
	 */
	private Car lCar;
	/**
	 * rental state unit for testing
	 */
	private RentalState rep = new OutForRepair();
	/**
	 * rental state unit for testing
	 */
	private RentalState det = new OutForDetail();
	/**
	 * rental state manager tester
	 */
	private RentalStateManager r; // null pointer probably
	

	

	/**
	 * tester for the rent car method
	 */
	@Test
	public void testRentCar() {
		lCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		lCar.setState(rep);

		try {
			rep.rentCar(r);

		} catch (IllegalStateException i) {
			assertEquals(rep, lCar.getState());
		}
	}

	/**
	 * tester for the return car method
	 */
	@Test
	public void testReturnCar() {
		lCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		lCar.setState(rep);

		try {
			rep.returnCar(r);

		} catch (IllegalStateException i) {
			assertEquals(rep, lCar.getState());
		}
	}

	/**
	 * tester for the report problem method
	 */
	@Test
	public void testReportProblem() {
		lCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		lCar.setState(rep);

		try {
			rep.reportProblem(r);

		} catch (IllegalStateException i) {
			assertEquals(rep, lCar.getState());
		}
	}

	/**
	 * tester for the detail done method
	 */
	@Test
	public void testDetailDone() {
		lCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		lCar.setState(rep);

		try {
			rep.detailDone(r);

		} catch (IllegalStateException i) {
			assertEquals(rep, lCar.getState());
		}
	}

	/**
	 * tester for the repair done method
	 */
	@Test
	public void testRepairDone() {
		lCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		lCar.setState(rep);

		r = new RentalStateManager() {

			@Override
			public void processReturn(boolean problem) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processRepaired() {
				// TODO Auto-generated method stub

			}

			@Override
			public void processRental() {
				// TODO Auto-generated method stub

			}

			@Override
			public void processNewCar(String id, String make, String model,
					String color) {
				// TODO Auto-generated method stub

			}

			@Override
			public void processDetailed() {
				// TODO Auto-generated method stub

			}
		};

		rep.repairDone(r);
		lCar.setState(det);
		assertEquals(det, lCar.getState());

	}

	/**
	 * tester for the rental info method
	 */
	@Test
	public void testRentalInfo() {
		lCar = new Car("f0923", "Mikezilla", "Firefox", "Red");
		lCar.setState(rep);

		try {
			rep.rentalInfo();

		} catch (IllegalStateException i) {
			assertEquals(rep, lCar.getState());
		}
	}

}
