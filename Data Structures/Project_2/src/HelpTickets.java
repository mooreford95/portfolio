import java.util.Scanner;

/**
 * The main program, reads in input from standard input, or in our case, a file,
 * and parses through each command passing them to the CommandHandler class.
 * HelpTickets also handles all exceptions. Note, some code was adapted and used
 * from Dr. Stallmann's dummy solution.
 * 
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class HelpTickets {

	/**
	 * The main program parses commands from a file, and then calls methods
	 * corresponding to each command — methods of the wrapper class — and also
	 * catches exceptions.
	 * 
	 * @param args
	 *            command line arguments
	 */
	public static void main(String[] args) {
		Tree tree = new Tree(); // Simple binary search tree
		CommandHandler handler = new CommandHandler(tree);
	    Scanner scanInput = new Scanner( System.in );
	    
	    boolean keepGoing = true;
	    while ( keepGoing ){
	        try {
	          keepGoing = nextCommand(handler, scanInput);
	        } catch ( Warning w ) {
	          System.out.println(w);
	        }
	    }
	}
	
	/**
	 * Determines the next command to handle
	 * @param handler the CommandHandler object for the program
	 * @param input the input from the user
	 * @return true if the command was successful, false otherwise
	 */
	public static boolean nextCommand(CommandHandler handler, Scanner input){
		if ( ! input.hasNextLine() ) return false;
		
	    String line = input.nextLine();
	    Scanner lineScanner = new Scanner( line );
	    
	    // check for blank line
	    if ( ! lineScanner.hasNext() ) throw new Warning("blank line");

	    // command is the first token of the line; look at it and decide what to do next
	    String command = lineScanner.next();
	    if ( command.equals( "+" ) ) handleAdd(handler, lineScanner);
	    else if ( command.equals( "-" ) ) handleRemove(handler,lineScanner);
	    else if ( command.equals( "?" ) ) handleQuery(handler,lineScanner);
	    else if ( command.equals( "*" ) ) handleRemoveHighest(handler);
	    else throw new Warning("invalid command " + command);
	    
	    if ( lineScanner.hasNext() )
	      throw new Warning("Extra stuff on command line, starting with " + lineScanner.next());
	   
	    return true;
	}
	
	/**
	 * Sets up the add command
	 * @param handler the CommandHandler object for the program
	 * @param lineScanner Scanner for the current user input
	 * @throws Warning if the input is incorrect for this command
	 */
	 private static void handleAdd(CommandHandler handler, Scanner lineScanner) throws Warning {
	    if ( ! lineScanner.hasNext() ) throw new Warning( "missing priority for +" );
	    
	    int priority;
	    String pString = "";
	    
	    try {
	      pString = lineScanner.next();
	      priority = Integer.parseInt( pString );
	    }
	    catch ( NumberFormatException nfe ) {
	      throw new Warning("priority " + pString + " is not an integer");
	    }
	    
	    handler.insert(priority);
	  }
	 
	 /**
	  * Sets up the remove command
	  * @param handler the CommandHandler object for the program
	  * @param lineScanner Scanner for the current user input
	  * @throws Warning if the input is incorrect for this command
	  */
	 private static void handleRemove(CommandHandler handler, Scanner lineScanner) throws Warning {
	    if ( ! lineScanner.hasNext() ) throw new Warning( "missing id for -" );
	    
	    int id;
	    String idString = "";
	    try {
	      idString = lineScanner.next();
	      id = Integer.parseInt( idString );
	    }
	    catch ( NumberFormatException nfe ) {
	      throw new Warning("id " + idString + " is not an integer");
	    }
	    
	    handler.remove( id );
	  }

	 /**
	  * Sets up the query command
	  * @param handler the CommandHandler object for the program
	  * @param lineScanner Scanner for the current user input
	  * @throws Warning if the input is incorrect for this command
	  */
	 private static void handleQuery(CommandHandler handler, Scanner lineScanner) throws Warning {
	    if ( ! lineScanner.hasNext() ) throw new Warning( "missing id for ?" );
	    
	    int id;
	    String idString = "";
	    
	    try {
	      idString = lineScanner.next();
	      id = Integer.parseInt( idString );
	    }
	    catch ( NumberFormatException nfe ) {
	      throw new Warning("id " + idString + " is not an integer");
	    }
	    
	    handler.query(id);
	  }

	 /**
	  * Sets up the remove highest command
	  * @param handler the CommandHandler object for the program
	  * @throws Warning if the input is incorrect for this command
	  */
	 private static void handleRemoveHighest(CommandHandler handler) throws Warning {
		 handler.removeHighest();
	 }
}
