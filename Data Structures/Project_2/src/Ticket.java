/**
 * Ticket class represents a help ticket request 
 * that has a priority, id, and number of descendant.
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class Ticket {

	/** priority of the ticket */
	private int priority;
	
	/** id of the ticket */
	private int id;

	/**
	 * Constructor for the ticket.
	 * 
	 * @param priority
	 *            the priority of the ticket
	 * @param id
	 *            the id of the ticket
	 */
	public Ticket(int priority, int id) {
		this.priority = priority;
		this.id = id;
	}

	/**
	 * Returns the ID of the ticket.
	 * 
	 * @return id the id of the ticket.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id field to newId.
	 * @param newId to set id to.
	 */
	public void setId(int newId) {
		id = newId;
	}

	/**
	 * Returns the priority of the ticket.
	 * 
	 * @return priority the priority of the ticket.
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the priority field to newPriority.
	 * @param newPriority to set priority to.
	 */
	public void setPriority(int newPriority) {
		priority = newPriority;
	}
}
