package edu.ncsu.csc216.carrental.model;

import edu.ncsu.csc216.carrental.model.state.RentalState;

/**
 * car class
 * @author mmackrell
 *
 */
public class Car {

	/**
	 * fleet number of car
	 */
	private String fleetNum;
	/**
	 * make of the car
	 */
	private String make;
	/**
	 * model of car
	 */
	private String model;
	/**
	 * color of car
	 */
	private String color;
	/**
	 * the current state of the car
	 */
	private RentalState currentState;


	/**
	 * constructor for car object
	 * 
	 * @param fleetNumber
	 *            fleet num of car
	 * @param makeOfCar
	 *            make of car
	 * @param carModel
	 *            model of car
	 * @param carColor
	 *            color of car
	 * @throws InvalidIDException
	 *             if needed
	 */
	public Car(String fleetNumber, String makeOfCar, String carModel,
			String carColor) {

		if (!checkFormat(fleetNumber)) {
			throw new InvalidIDException();
		} else {
			this.fleetNum = fleetNumber;
		}

		this.make = makeOfCar;
		this.model = carModel;
		this.color = carColor;

	}

	/**
	 * hashcode method
	 * @return fleetnums hashcode
	 */
	@Override
	public int hashCode() {
		return fleetNum.hashCode();
	}

	/**
	 * equals method checks to see if objects are equal
	 * @return true or false
	 * @param obj the object
	 */
	@Override
	public boolean equals(Object obj) {

		Car car = null;
		if (!(obj instanceof String)) {

			car = (Car) obj;
		}
		if (this != null && car != null) {
 
			return this.getFleetNum().equals(car.getFleetNum());
			 
		} else {
			return false;
		}
	}

	private boolean checkFormat(String form) {
		if (form.length() != 5) {
			return false;
		}
		char format = form.charAt(0);
		char floorMatt = form.charAt(1);
		char doorMatt = form.charAt(2);
		char moorMatt = form.charAt(3);
		char poorMatt = form.charAt(4);

		if (!(Character.isAlphabetic(format))) {
			return false;
		}

		return !(!(Character.isDigit(poorMatt)) || !(Character.isDigit(moorMatt)) || !(Character.isDigit(floorMatt)) || !(Character.isDigit(doorMatt)));
			
		
		
	}

	/**
	 * getter for fleet number of the car
	 * 
	 * @return the fleet number of the car
	 */
	public String getFleetNum() {
		return fleetNum;
	}

	/**
	 * getter for make of the car
	 * 
	 * @return the make of the car
	 */
	public String getMake() {
		return make;
	}

	/**
	 * getter for model of the car
	 * 
	 * @return the model of the car
	 */
	public String getModel() {
		return model;
	}

	/**
	 * getter for color of car
	 * 
	 * @return color of the car
	 */
	public String getColor() {
		return color;
	}

	/**
	 * returns the rental state of the car
	 * 
	 * @return the rental state
	 */
	public RentalState getState() {
		return currentState;
	}

	/**
	 * sets the rental state of the car
	 * @param rState the rental state of the car
	 */
	public void setState(RentalState rState) {
		currentState = rState;
	}

	/**
	 * toString method for car
	 * 
	 * @return s the string of the car
	 */
	public String toString() {
		String s = "";
		String num = fleetNum.trim();
		String carMake = make.trim();
		String mod = model.trim();
		String col = color.trim();
		s += num + ":  " + carMake + " " + mod + " " + "(" + col + ")";
		return s;
	}

}
