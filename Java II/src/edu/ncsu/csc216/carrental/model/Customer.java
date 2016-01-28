package edu.ncsu.csc216.carrental.model;

/**
 * customer class
 * @author mmackrell
 *
 */
public class Customer {

	/**
	 * first name of customer
	 */
	private String firstName;

	/**
	 * last name of customer
	 */
	private String lastName;

	/**
	 * id of the customer
	 */
	private String id;

	/**
	 * constructor for customer object
	 * 
	 * @param first
	 *            first name of customer
	 * @param last
	 *            last name of customer
	 * @param identification
	 *            id of customer
	 * @throws InvalidIDException
	 */
	public Customer(String first, String last, String identification){
			

		this.firstName = first;
		this.lastName = last;

		if (!(checkFormat(identification))) {
			throw new InvalidIDException();
		}

		this.id = identification;
		if (!(id instanceof String)) {

			throw new InvalidIDException();

		}
		if (firstName.trim().length() == 0) {
			throw new InvalidIDException("dont leave it blank");
		} 

		if (lastName.trim().length() == 0) {
			throw new InvalidIDException();
		}

		for (int i = 0; i < firstName.length(); i++) { 
			char format = first.charAt(i);
			if (!(Character.isAlphabetic(format)) && format != '"' && format != '-' && format != '\'') {
				throw new IllegalArgumentException();
			}
			
		}

		for (int i = 0; i < lastName.length(); i++) { 
			char format = last.charAt(i);
			if (!(Character.isAlphabetic(format)) && format != '"'
					&& format != '-' && format != '\'') {
				throw new IllegalArgumentException();
			}

		}

	}

	/**
	 * getter for the first name of the customer
	 * 
	 * @return the first name of the customer
	 */
	public String getFirstName() {
		return firstName;
	}

	private boolean checkFormat(String form) {
		if (form.length() != 7) {
			return false;
		}
		char format = form.charAt(0);
		char floorMatt = form.charAt(1);
		char doorMatt = form.charAt(2);
		char moorMatt = form.charAt(3);
		char poorMatt = form.charAt(4);
		char goreMatt = form.charAt(6);
		char choreMatt = form.charAt(6);

		if (!(Character.isDigit(format))) {
			return false;
		}

		return !(!(Character.isDigit(poorMatt)) || (doorMatt) != '-' || !(Character.isDigit(floorMatt)) || !(Character.isDigit(moorMatt)) || !(Character.isDigit(goreMatt)) || !(Character.isDigit(choreMatt))); 
			
		
	}

	/**
	 * getter for the last name of the customer
	 * 
	 * @return the last name of the customer
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * getter for the id of the customer
	 * 
	 * @return the id of the customer
	 */
	public String getId() {
		return id;
	}

	/**
	 * toString method for customer
	 * 
	 * @return s the string for the customer
	 */
	public String toString() {
		String s = "";
		String iden = id.trim();
		String lName = lastName.trim();
		String fName = firstName.trim();
		s += iden + ":  " + fName + " " + lName;
		return s;

	}

	/**
	 * hash code method
	 * @return the hashcode
	 */
	@Override
	public int hashCode() {
		return id.hashCode(); 
	}

	/**
	 * equals method checks to see if the object are equal
	 * @return true or false
	 * @param obj the object that we are checking
	 */
	@Override
	public boolean equals(Object obj) {
		Customer devonsPoynter = null;
		Customer other = null;
		if (!(obj instanceof String)) {
			other = (Customer) obj;
		}
		if (obj != null && !(obj instanceof String)) {
			devonsPoynter = (Customer) obj;
		}
		if (this != null && devonsPoynter != null) {
			return this.getId().equals(other.getId());
			
		} else {
			return false;
		}
	}

}
