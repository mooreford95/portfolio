/**
 * A linked list of Message Objects
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class MessageList {

	/** The front of the list. */
	private MessageNode head;
	
	/**
	 * Constructor for MessageList.
	 */
	public MessageList(){
		head = null;
	}
	
	/**
	 * Adds a message to the list in ascending order
	 * @param messageToAdd the packet to add to the list.
	 */
	public void add(Message messageToAdd) {
		if (head == null) {
			head = new MessageNode(messageToAdd, null);
		} else if (messageToAdd.getMessageNum() == head.data.getMessageNum()) {
			head = new MessageNode(messageToAdd, head.next);
		} else {
			this.addHelper(messageToAdd);
		}
	}	
	
	/**
	 * Adds a message to the rest of the list
	 * PRE: the front of the list is not null
	 * @param messageToAdd the packet to add to the list
	 */
	public void addHelper(Message messageToAdd) {
		if (messageToAdd.getMessageNum() < head.data.getMessageNum()) {
			head = new MessageNode(messageToAdd, head);
		} else {
			this.addToRest(messageToAdd);
		}
	}
	
	/**
	 * Adds a message to the rest of the list
	 * PRE: Message does not need to enter the front of the list
	 * @param messageToAdd the packet to add to the list
	 */
	public void addToRest(Message messageToAdd) {
		MessageNode current = head;
		MessageNode previous = head;
		
		while (current != null) {
			int currentNum = current.data.getMessageNum();
			int toAddNum = messageToAdd.getMessageNum();
			if (toAddNum >= currentNum) {
				if (current.next == null){
					current.next = new MessageNode(messageToAdd, null);
					break;
				} else {
					previous = current;
					current = current.next;
				}
			} else {
				previous.next = new MessageNode(messageToAdd, current);
				break;
				
			}
		}
	}
	
	/**
	 * Returns the front of the list.
	 * @return message at front of list.
	 */
	public MessageNode getHead() {
		return head;
	}

	/**
	 * Converts the MessageList to a to string.
	 */
	public String toString(){
		String s = "";
		MessageNode current = head;
		while(current != null){
			s += current.data.toString();
			current = current.next;
		}
		return s;
	}
	
	/** 
	 * Simple toString to check order of nodes.
	 * @return string of only node messages.
	 */
	public String toStringNodes(){
		String s = "";
		MessageNode current = head;
		while(current != null){
			s += current.data.toStringNodes();
			current = current.next;
		}
		return s;
		
	}
	
	/**
	 * Checks if Message is already in the list.
	 * @param messToFind message to search for.
	 * @return null if not found, message if found.
	 */
	public Message contains (int messToFind) {
		return recContains(messToFind, head);
	}
	
	/**
	 * Checks if Message is already in the list.
	 * @param messToFind message to search for.
	 * @param curr current MessageNode
	 * @return null if not found, message if found.
	 */
	private Message recContains(int messToFind, MessageNode curr) {
		if(curr == null) {
			return null;
		}
		if(messToFind > curr.data.getMessageNum()) {
			return recContains(messToFind, curr.next);
		}
		if(curr.data.getMessageNum() == messToFind){
			return curr.data;
		}
		return null;
	}

	/**
	 * Inner class to create nodes for lists.
	 * 
	 * @author Curtis
	 *
	 */
	public class MessageNode {
		/** A message. */
		public Message data;
		/** The next MessageNode */
		public MessageNode next;

		/**
		 * Constructor for MessageNode.
		 * @param data message for node.
		 */
		public MessageNode(Message data) {
			this(data, null);
		}

		/**
		 * Constructor for MessageNode.
		 * @param data Message for node
		 * @param next the next MessageNode
		 */
		public MessageNode(Message data, MessageNode next) {
			this.data = data;
			this.next = next;
		}
	}

}