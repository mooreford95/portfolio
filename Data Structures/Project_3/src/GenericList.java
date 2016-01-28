import java.util.*;

/**
 * This class can be used to store a list of values of type E. This code
 * was mostly written by Martty Stepp, the author of the Building Java
 * Programs 3rd Edition. This book was used in CSC 116 and CSC 216 at NCSU.
 * The code was in the 16th chapter of the book on linked lists. The code is
 * also on the textbook's website for chapter 16's supplements.
 * Textbook website citation: http://www.buildingjavaprograms.com/
 * 
 * @author Marty Stepp
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class GenericList<E> {
	
	/** first value in the list */
	private ListNode<E> front;
	
	/** last value in the list */
	private ListNode<E> back;
	
	/** current number of elements */
	private int size;

	/**
	 * Constructs an empty list
	 */
	public GenericList() {
		front = new ListNode<E>(null);
		back = new ListNode<E>(null);
		clear();
	}

	/**
	 * Gives the current size of the list
	 * @return the size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Gets the element at the given index
	 * if 0 <= index < size() (throws IndexOutOfBoundsException if not)
	 * @param index the index to check
	 * @return the value at the given index in the list
	 */
	public E get(int index) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index);
		return current.data;
	}
	
	/**
	 * creates a comma-separated, bracketed version of the list
	 * @return a string representation of the list
	 */
	public String printGenericList() {
		if (size == 0) {
			return "[]";
		} else {
			String result = "[" + front.next.data;
			ListNode<E> current = front.next.next;
			while (current != back) {
				result += ", " + current.data;
				current = current.next;
			}
			result += "]";
			return result;
		}
	}

	/**
	 * Gives the index of the given value
	 * @param value value to search with
	 * @return the position of the first occurrence of the given
	 * value (-1 if not found)
	 */
	public int indexOf(E value) {
		int index = 0;
		ListNode<E> current = front.next;
		while (current != back) {
			if (current.data.equals(value)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
	}

	/**
	 * Checks if the list is empty
	 * @return true if list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Checks if the given value is in the list
	 * @param value value to each with
	 * @return true if the given value is contained in the list,
	 * false otherwise
	 */
	public boolean contains(E value) {
		return indexOf(value) >= 0;
	}

	/**
	 * Appends the given value to the end of the list
	 * @param value the value to add
	 */
	public void add(E value) {
		add(size, value);
	}

	/**
	 *  inserts the given value at the given index, shifting
	 *  subsequent values right,
	 *  If 0 <= index <= size() (throws IndexOutOfBoundsException if not)
	 *  @param index the index to add at
	 *  @param value the value to add
	 *  @throws IndexOutOfBoundsException if the index is not in the list
	 */
	public void add(int index, E value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index: " + index);
		}
		ListNode<E> current = nodeAt(index - 1);
		ListNode<E> newNode = new ListNode<E>(value, current.next, current);
		current.next = newNode;
		newNode.next.prev = newNode;
		size++;
	}

	/**
	 * appends all values in the given list to the end of this list
	 * @param other the other list to add with
	 */
	public void addAll(List<E> other) {
		for (E value : other) {
			add(value);
		}
	}

	/**
	 * removes value at the given index, shifting subsequent values left,
	 * if 0 <= index < size() (throws IndexOutOfBoundsException if not)
	 * @param index the index to remove at
	 */
	public void remove(int index) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index - 1);
		current.next = current.next.next;
		current.next.prev = current;
		size--;
	}

	/**
	 * replaces the value at the given index with the given value, if
	 * 0 <= index < size() (throws IndexOutOfBoundsException if not)
	 * @param index the index of the value to set
	 * @param value the new value of the given index
	 */
	public void set(int index, E value) {
		checkIndex(index);
		ListNode<E> current = nodeAt(index);
		current.data = value;
	}

	/**
	 * Makes the current list empty
	 */
	public void clear() {
		front.next = back;
		back.prev = front;
		size = 0;
	}

	/**
	 * Makes an iterator for this list
	 * @return an iterator for this list
	 */
	public GenericIterator<E> iterator() {
		return new LinkedIterator();
	}

	/**
	 * Gives the node at a specific index. Uses the fact that the list
	 * is doubly-linked to start from the front or the back, whichever
	 * is closer.
	 * @param index the index of the node to find
	 * @return the node at the given index
	 */
	private ListNode<E> nodeAt(int index) {
		ListNode<E> current;
		if (index < size / 2) {
			current = front;
			for (int i = 0; i < index + 1; i++) {
				current = current.next;
			}
		} else {
			current = back;
			for (int i = size; i >= index + 1; i--) {
				current = current.prev;
			}
		}
		return current;
	}

	/**
	 * checks the index passed in to see if it is in the list
	 * @throws an IndexOutOfBoundsException if the given index is
	 * not a legal index of the current list
	 * @param index the index to check
	 */
	private void checkIndex(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("index: " + index);
		}
	}
	
	/**
	 * This inner class is used to make nodes in the outer classes linked list of
	 * type E. This code was mostly written by Martty Stepp, the author of the Building Java
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
	private static class ListNode<E> {
		public E data; // data stored in this node
		public ListNode<E> next; // link to next node in the list
		public ListNode<E> prev; // link to previous node in the list

		/**
		 * constructs a node with given data and null links
		 * @param data the data of this node
		 */
		public ListNode(E data) {
			this(data, null, null);
		}

		/**
		 * constructs a node with given data and given links
		 * @param data the data of this node
		 * @param a reference to the next node for this node
		 * @param prev a reference to the previous node for this node
		 */
		public ListNode(E data, ListNode<E> next, ListNode<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}
	
	/**
	 * This inner class is used to make iterators of the outer class list of
	 * type E. This code was mostly written by Martty Stepp, the author of the Building Java
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
	private class LinkedIterator implements GenericIterator<E> {
		/** location of next value to return */
		private ListNode<E> current;
		
		/** whether it's okay to remove now */
		private boolean removeOK;

		/**
		 * Constructs an iterator for the given list
		 */
		public LinkedIterator() {
			current = front.next;
			removeOK = false;
		}

		/**
		 * Determines if the list has another element
		 * @return true if there is another element, false otherwise
		 */
		public boolean hasNext() {
			return current != back;
		}

		/**
		 * Get the next element in the list
		 * @return the next element in the iteration
		 * @throws NoSuchElementException() if no other element
		 */
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			E result = current.data;
			current = current.next;
			removeOK = true;
			return result;
		}

		/**
		 * Removes the last element returned by the iterator
		 * pre : next() has been called without a call on remove (i.e., at most
		 * one call per call on next)
		 * post: removes the last element returned by the iterator
		 * @throws IllegalStateException if removing is not okay
		 */
		public void remove() {
			if (!removeOK) {
				throw new IllegalStateException();
			}
			ListNode<E> prev2 = current.prev.prev;
			prev2.next = current;
			current.prev = prev2;
			size--;
			removeOK = false;
		}
	}
}