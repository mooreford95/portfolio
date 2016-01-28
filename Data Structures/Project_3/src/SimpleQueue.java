import java.util.NoSuchElementException;

/**
 * Interface for the Queue class. This code is re-used code from Jacob Stone's
 * past CSC 215 projects from NCSU. The code was also influenced
 * by the Building Java Programs Book for CSC 116 AND CSC 216.
 * Citation from textbook website http://www.buildingjavaprograms.com/code-files/3ed/
 * @param <E> Generic Type of an Queue.
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public interface SimpleQueue<E> {

	/**
	 * Adds the Object in the Queue
	 * @param item Generic item to add to the queue
	 */
	public void add(E item);
	
	/**
	 * Removes the first Object from the Queue
	 * @return the Object that was removed
	 * @throws NoSuchElementException if object is not in the queue
	 */
	public E remove();
	
	/**
	 * Retrieves, but does not remove the
	 * first object in the Queue
	 * @return Object that was retrieved
	 * @throws NoSuchElementException if the queue is empty
	 */
	public E peek();
	
	/**
	 * Checks to see if the Queue is Empty or not
	 * @return true if the Queue is Empty, false if its not.
	 */
	public boolean isEmpty();
}