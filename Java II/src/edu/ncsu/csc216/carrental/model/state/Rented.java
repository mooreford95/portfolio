package edu.ncsu.csc216.carrental.model.state;

import edu.ncsu.csc216.carrental.model.Customer;

/**
 * rented finite state
 * @author mmackrell
 *
 */
public class Rented implements RentalState {
	
	private Customer michael;
	
	/**
	 * constructor
	 * @param mike the customer who has rented the car
	 */
	public Rented(Customer mike){
		
		this.michael = mike;
	}
	
	/**
	 * method for renting a car
	 * @param theRentStatMan manager for rental behavior
	 */
	public void rentCar(RentalStateManager theRentStatMan){
		throw new IllegalStateException();
		
	}
	
	/**
	 * method for returning a car
	 * @param theRSM manager for rental behavior
	 */
	public void returnCar(RentalStateManager theRSM){
		theRSM.processReturn(false);
	}
	
	/**
	 * method for reporting a problem with the car
	 * @param manager the manager for rental behavior
	 */
	public void reportProblem(RentalStateManager manager){
		manager.processReturn(true);
	}
	
	/**
	 * method for finishing the detailing of a car
	 * @param rentManage the manager for rental behavior
	 */
	public void detailDone(RentalStateManager rentManage){
		throw new IllegalStateException();
	}
	
	/**
	 * method for finishing the repairing of a car
	 * @param r the manager for rental behavior
	 */
	public void repairDone(RentalStateManager r){
		throw new IllegalStateException();
	}
	
	/**
	 * string of rental information
	 * @return the string of rental info
	 */
	public String rentalInfo(){
		String s = "";
			char one = michael.getFirstName().charAt(0);
			s = "(" + one + " " + michael.getLastName() + ")";
		
		
		return s;
	}
	
	/**
	 * getter for the customer object
	 * @return the customer
	 */
	public Customer getCustomer(){
		if (michael != null){
			return michael;
		}
		else {
			return null;
		}
		
	}

}
