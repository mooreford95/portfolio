import java.util.NoSuchElementException;

/**
 * Generic Queue class. This code is re-used code from Jacob Stone's
 * past CSC 215 projects from NCSU. The code was also influenced
 * by the Building Java Programs Book for CSC 116 AND CSC 216.
 * Citation from textbook website http://www.buildingjavaprograms.com/code-files/3ed/
 * @param <E> Generic Type of an Queue.
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class Queue<E> implements SimpleQueue<E> {
	
	private Node head;
	
	//--------------------Inner Class------------------------//
	
   /**
    * Inner Node class for the Queue
    */
    private class Node {
		
		/** The next node in the queue*/
		public Node next;
		
		/** element for this node */
		public E value;
		
		/**
		 * Constructor of the Node Object
		 * @param value the value for the node
		 * @param next the location of next element
		 */
		public Node(E value, Node next) {
			this.value = value;
			this.next = next;
		}
		
	}

	//---------------------------- End Inner Class---------------------//
    
    /**
	 * Constructor for the Queue class
	 */
	public Queue() {
		head = null;
	}

	/**
	 * Adds the Object in the Queue
	 * @param item Generic item to add to the queue
	 */
	public void add(E item) {
		Node current = head;
		if(head == null) {
			head = new Node(item, null);
			
		}
		else {
			while(current.next != null) {
				current = current.next;
			}
			current.next = new Node(item, current.next);
		}
	}

	/**
	 * Removes the first Object from the Queue
	 * @return the Object that was removed
	 * @throws NoSuchElementException if object is not in the queue
	 */
	@Override
	public E remove() {
		Node current = head;
		
		if(head == null) {
			throw new NoSuchElementException();
		}
		
		head = head.next;
		return current.value;
	}

	
	/**
	 * Retrieves, but does not remove the
	 * first object in the Queue
	 * @return Object that was retrieved
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E peek() {
		if(head == null) {
			throw new NoSuchElementException();
		}
		return head.value;
	}

	/**
	 * Checks to see if the Queue is Empty or not
	 * @return true if the Queue is Empty, false if its not.
	 */
	@Override
	public boolean isEmpty() {
		if(head == null){
			return true;
		}
		return false;
	}

}
