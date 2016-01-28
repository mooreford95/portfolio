package edu.ncsu.csc216.carrental.model;

/**
 * exception class
 * 
 * @author mmackrell
 *
 */
@SuppressWarnings("serial")
public class InvalidIDException extends RuntimeException {

	/**
	 * constructor for exception
	 */
	public InvalidIDException() {
		super("Invalid ID!");
	}

	/**
	 * constructor for exception with a string
	 * 
	 * @param s
	 *            the string
	 */
	public InvalidIDException(String s) {
		super(s);
	}

}
