import java.util.InputMismatchException;

/**
 * Class that handles all warnings in SocialNetwork.java
 *  
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class Warning extends InputMismatchException {

	/** Serial Version for this Warning */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for warning.
	 * 
	 * @param message to be printed as a warning message
	 */
	public Warning(String message){
		super(message);
	}
}
