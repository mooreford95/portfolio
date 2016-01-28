package story;

import java.util.Scanner;

/**
 * As of this build, mostly a set of tools for user interaction. Extended by the
 * opening dialog and possibly later an NPC dialog class.
 * 
 * @author Curtis
 *
 */
public class Dialog {

	protected String header;
	protected Scanner input;

	/**
	 * Creates a dialog.
	 * 
	 * @param header
	 *            header for the dialog.
	 * @param console
	 *            scanner. Name may not be appropriate; I suppose
	 *            it could be directed to a file.
	 */
	public Dialog(String header, Scanner console) {
		this.header = header;
		input = console;
	}

	/**
	 * Takes in yes or no
	 * 
	 * @return boolean based on user input.
	 */
	public boolean takeInYesNo() {
		System.out.print("(y/n) ");
		String response = takeInTrimmedString();
		if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes")) {
			return true;
		} else if (response.equals("n") || response.equalsIgnoreCase("no")) {
			return false;
		} else
			return takeInYesNo();

	}

	/**
	 * ...It displays the header. Javadoc feels a little futile sometimes, no?
	 */
	public void displayHeader() {
		System.out.println(header);
	}

	/**
	 * Takes in a string from the scanner passed into the dialog, trims it and
	 * 
	 * @return trimmed string
	 */
	public String takeInTrimmedString() {
		String response = input.next();
		return response.trim();
	}

}
