package edu.ncsu.csc216.carrental.model.management;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;

/**
 * tester for nux class
 * @author mmackrell, cfmoore2
 *
 */
public class NuxCarRentalTest {

	/**
	 * nux car rental
	 */
	private NuxCarRental nuxtuals;
	/**
	 * car for testing
	 */
	private Car fordernetExplorer;
	/**
	 * car for testing
	 */
	private Car falcon;
	/**
	 * customer for testing
	 */
	private Customer nessie;
	/**
	 * customer for testing
	 */
	private Customer madison;

	/**
	 * the set up method
	 * @throws Exception if something breaks
	 */
	@Before
	public void setUp() throws Exception {
		nessie = new Customer("Janessa", "Hall", "00-8316");
		madison = new Customer("Madison", "Chandler", "00-5321");
		nuxtuals = new NuxCarRental();
		fordernetExplorer = new Car("f7404", "Microsoft", "IE", "pukeBlue");
		falcon = new Car("Y1300", "Ford", "Falcon", "blasterScarredGrey");
	}

	/**
	 * tester for NuxCarRentalScanner
	 */
	@Test
	public void testRentCar() {
		nuxtuals.addCustomer(nessie);
		nuxtuals.addCar(fordernetExplorer);
		assertEquals(nuxtuals.availableCars(),
				"f7404:  Microsoft IE (pukeBlue)\n");
		nuxtuals.rentCar();
		assertEquals("f7404:  Microsoft IE (pukeBlue) (J Hall)\n", nuxtuals.rentedCars());
	}

	/**
	 * test the return car method
	 */
	@Test
	public void testReturnCar() {
		//add appropriate objects to rent a car
		nuxtuals.addCustomer(madison);
		nuxtuals.addCar(fordernetExplorer);
		
		//rent and return the car and check
		nuxtuals.rentCar();
		nuxtuals.processReturn(false);
		
		assertEquals("f7404:  Microsoft IE (pukeBlue)\n", nuxtuals.detailingCars());
	}

	/**
	 * tester for the report problem method
	 */
	@Test
	public void testReportProblem() {
		nuxtuals.addCar(fordernetExplorer);

		nuxtuals.processRental();
		nuxtuals.processReturn(true);
		// System.out.println(nuxtuals.repairingCars());
		assertFalse(nuxtuals.hasRepairingCars());
		assertEquals("f7404:  Microsoft IE (pukeBlue)\n",
				nuxtuals.availableCars());
	}

	/**
	 * tester for the complete detailing method
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testCompleteDetailing() throws FileNotFoundException {

		  Customer c = new Customer("George", "Clooney", "22-2222");
		  nuxtuals.addCustomer(c);
		  nuxtuals.addCar(fordernetExplorer);
		  
		  nuxtuals.processRental();
		  
		  nuxtuals.processReturn(false);
		 
		  assertEquals("f7404:  Microsoft IE (pukeBlue)\n",
		  nuxtuals.detailingCars()); assertTrue(nuxtuals.hasDetailingCars());
		  nuxtuals.processDetailed();
		 
		  assertEquals("f7404:  Microsoft IE (pukeBlue)\n",
		  nuxtuals.availableCars());
		 
		Scanner scan = new Scanner(new File("TestData"));
		NuxCarRental nux = new NuxCarRental(scan);
		Car breaking = new Car("A2011", "Andro", "Sparkle", "Silver");
		nux.addCar(breaking);
		assertTrue(nux.hasRepairingCars());
		
	}

	/**
	 * tester for the complete repairs method
	 */
	@Test
	public void testCompleteRepairs() {
		nuxtuals.addCustomer(nessie);
		nuxtuals.addCar(falcon);
		
		nuxtuals.processRental();
		nuxtuals.addCustomer(nessie);
		
		nuxtuals.processReturn(true);
		assertEquals("Y1300:  Ford Falcon (blasterScarredGrey)\n", nuxtuals.repairingCars());
		nuxtuals.completeRepairs();
		
		assertFalse(nuxtuals.hasRepairingCars());

	}

	/**
	 * tester for the customers waiting method
	 */
	@Test
	public void testCustomersWaiting() {
		// add one customer to the waiting list and see if they print properly
		nuxtuals.addCustomer(madison);
		assertEquals("00-5321:  Madison Chandler\n",
				nuxtuals.customersWaiting());
		assertTrue(nuxtuals.hasWaitingCustomers());
	}


	
	/**
	 * tester for the add customer method
	 */
	@Test
	public void testAddCustomer() {
		nuxtuals.addCustomer(madison);
		assertTrue(nuxtuals.hasWaitingCustomers());
	}

	/**
	 * tester for the add car method
	 */
	@Test
	public void testAddCar() {
		nuxtuals.processNewCar("f9000", "f", "9", "000");
		assertEquals("f9000:  f 9 (000)\n", nuxtuals.availableCars());
	}

	/**
	 * tests the write data method
	 * @throws IOException 
	 */
	@Test
	public void testWriteData() throws IOException {
		nuxtuals.addCustomer(madison);
		nuxtuals.addCar(falcon);
		nuxtuals.addCar(fordernetExplorer);
		nuxtuals.addCustomer(nessie);
		assertTrue(nuxtuals.hasAvailableCars());
		nuxtuals.rentCar();
		nuxtuals.rentCar();
		nuxtuals.returnCar();
		Writer writer = new FileWriter("output.txt");
		nuxtuals.writeData(writer);
		writer.close();
		
	}

}
