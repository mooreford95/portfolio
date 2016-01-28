package edu.ncsu.csc216.carrental.util;

import java.util.EmptyStackException;

/**
 * stack class
 * @author mmackrell
 *
 * @param <E> the things in the stack
 */
public class Stack<E> implements SimpleStack<E> {
	
	/**
	 * top of the stack
	 */
	private Node topStack; 
	   
	 
	/**
	 * node class
	 * @author mmackrell
	 *
	 */
	  private class Node {
	     private E info;
	     private Node next;
	      
	     public Node(E info, Node next) {
	        this.info = info;
	        this.next = next;
	     }
	  }
	
	/**
	 * constructor for the home made stack
	 */
	public Stack(){
		topStack = null;
		
	}
	
	/**
	 * is empty method for the stack 
	 * @return true/false depending on value
	 */
	public boolean isEmpty(){
		return topStack == null;
		
	}
	
	/**
	 * method to peek at the top element of the stack
	 * @return the info at the top of the stack
	 */
	public E peek(){
		if (topStack == null){
			throw new EmptyStackException();
		}
		return topStack.info;
		
	}
	
	/**
	 * method to pop off the top element of the stack
	 * @return val the value
	 */
	public E pop(){
		if (topStack == null){
			throw new EmptyStackException();
		}
		E val = topStack.info;
		topStack = topStack.next;
		return val;
	}
	/**
	 * pushes the element to the stack
	 * @param capitalE the element we are pushing
	 */
	public void push(E capitalE){
		topStack = new Node(capitalE, topStack);
		
	}

}
