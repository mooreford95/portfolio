package edu.ncsu.csc216.carrental.model.state;

/**
 * out for repair fsm class
 * @author mmackrell
 *
 */
public class OutForRepair implements RentalState {
	
	
	/**
	 * constructor
	 */
	public OutForRepair(){
		//empty constructor
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
		throw new IllegalStateException();
	
		
	}
	
	/**
	 * method for reporting a problem with the car
	 * @param manager the manager for rental behavior
	 */
	public void reportProblem(RentalStateManager manager){
		throw new IllegalStateException();
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
		r.processRepaired();
	}
	
	/**
	 * string of rental information
	 * @return an exception
	 */
	public String rentalInfo(){
		throw new IllegalStateException();
	}
}
