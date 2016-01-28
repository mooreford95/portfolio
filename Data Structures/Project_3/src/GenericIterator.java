import java.util.NoSuchElementException;

/**
 * Interface for the GenerticIterator inside the Genertic List class.
 * This code was mostly written by Martty Stepp, the author of the Building Java
 * Programs 3rd Edition. This book was used in CSC 116 and CSC 216 at NCSU.
 * The code was in the 16th chapter of the book on linked lists. The code is
 * also on the textbook's website for chapter 16's supplements.
 * Textbook website citation: http://www.buildingjavaprograms.com/
 * @author Marty Stepp
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public interface GenericIterator<E> {
	
	/**
	 * Determines if the list has another element
	 * @return true if there is another element, false otherwise
	 */
	public boolean hasNext();
	
	/**
	 * Get the next element in the list
	 * @return the next element in the iteration
	 * @throws NoSuchElementException() if no other element
	 */
	public E next();
	
	/**
	 * Removes the last element returned by the iterator
	 * pre : next() has been called without a call on remove (i.e., at most
	 * one call per call on next)
	 * post: removes the last element returned by the iterator
	 * @throws IllegalStateException if removing is not okay
	 */
	public void remove();
	
	
	
	
}
