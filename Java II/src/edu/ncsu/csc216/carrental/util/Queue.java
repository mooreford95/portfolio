package edu.ncsu.csc216.carrental.util;

import java.util.NoSuchElementException;
/**
 * queue class
 * @author mmackrell
 *
 * @param <E> the thing that its a queue of
 */
public class Queue<E> implements SimpleQueue<E> {

	/**
	 * node for the front of the queue
	 */
	private Node head; // Points to the front of the queue
	/**
	 * node for the back of the queue
	 */
	private Node tail; // Points to the rear of the queue

	/**
	 * node class
	 * @author mmackrell
	 *
	 */
	private class Node {
		public E data;
		public Node link;

		public Node(E data, Node link) {
			this.data = data;
			this.link = link;
		}
	}

	/**
	 * constructor for the queue object
	 */
	public Queue() {

		head = null;
		tail = null;

	}

	/**
	 * add method for queue
	 * @param data the data of the node
	 * @param theUpperCaseE
	 */
	public void add(E data) {
		if (head == null) {
			head = new Node(data, null);
			tail = head;
		} else {
			tail.link = new Node(data, null);
			tail = tail.link;
		}

	}

	/**
	 * is empty method for the queue
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * remove method for the queue
	 * 
	 * @return the removed item
	 * @throws NoSuchElementException
	 *             if there is no element
	 */
	public E remove() {

		if (head == null) {
			throw new NoSuchElementException();
		}
		E item = head.data;
		head = head.link;
		if (head == null) {
			tail = null;
			return item;
		}
		return item; 
	}

	/**
	 * peek method for the queue
	 * @return the data at the head
	 */
	public E peek() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		return head.data;

	}
}
