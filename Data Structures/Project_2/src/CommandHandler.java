import java.util.*;

/**
 * Wrapper class that handles the command input.
 * 
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class CommandHandler {
    /** tree map for use */
	private TreeMap<Integer, Integer> map; 
	/** tree object */
	private Tree tree; 
	/** incrementer for the id*/
	private int idCounter; 

	/**
	 * Constructor for the command handler class.
	 * @param tree the tree use as the data structure.
	 */
	public CommandHandler(Tree tree) {
		this.tree = tree;
		map = new TreeMap<Integer, Integer>();
		idCounter = 1;
	}

	/**
	 * Wrapper Method to insert into a list.
	 * @param priority the priority of the node to be inserted
	 */
	public void insert(int priority) {
		System.out.println("+ " + priority);
		if(!map.containsValue(priority)){
			Ticket t = new Ticket(priority, idCounter);// priority id descendants
			map.put(idCounter, priority); // id is key, priority is value
			tree.insert(t);
			System.out.println("    id = " + idCounter);
	
			idCounter++;
		} else {  
			throw new Warning("a ticket with priority " + priority + " is already in the queue");
		}
	}

	/**
	 * Wrapper method to remove any node given the id of that node
	 * @param id the id of the node to be removed
	 */
	public void remove(int id) {
		System.out.println("- " + id);
		if(tree.isEmpty()){
			throw new Warning("removal attempted when queue is empty");
		}
		
		if( map.containsKey(id) ){
			int priority = map.get(id);
			map.remove(id);
			
			System.out.println("    " + priority + ", pos = " + tree.query(priority)); 
			tree.remove(priority);
		} else {
			throw new Warning("there is no ticket with id = " + id + " in the queue");
		}
	}

	/**
	 * Wrapper method to remove the node with the highest key.
	 */
	public void removeHighest() {
		System.out.println("*");
		if(tree.isEmpty()){
			throw new Warning("removal attempted when queue is empty");
		}
		
		tree.getHighest();
		
		Ticket highest = tree.getHighestTicket();
		map.remove(highest.getId());
		System.out.println("    id = " + highest.getId() + ", " + highest.getPriority() );
		//map.remove(highest.getId());
		//tree.remove(highest.getPriority());	
	}

	/**
	 * Wrapper method to query about a given an id. This method
	 * gives the position of the node in the tree
	 * @param id the id of the node to query about
	 */
	public void query(int id) {
		System.out.println("? " + id);
		if(tree.isEmpty()){
			throw new Warning("removal attempted when queue is empty");
		}
		
		if( map.containsKey(id) ){ 
			int priority = map.get(id);
			System.out.println("    pos = " + tree.query(priority));
		} else {
			throw new Warning("there is no ticket with id = " + id + " in the queue");
		}
	}
}
