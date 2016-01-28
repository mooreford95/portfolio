/**
 * Message class of the project.
 * Contains the number of the message and
 * the packets for the message.
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class Message {
	
	/**
	 * The number for the message
	 */
	private int messNum;
	
	/**
	 * The packet to get returned
	 */
	private PacketList sixPack;
	
	/**
	 * Constructor for the message
	 * @param messNum the message number
	 * @param packNum the packet number
	 * @param message the message to be printed
	 */
	public Message(int messNum, int packNum, String message){
		this.messNum = messNum;
		sixPack = new PacketList();
		Packet packet = new Packet(packNum, message);
		sixPack.add(packet);
	}
	
	/**
	 * Constructor for message.
	 * @param messNum the message number.
	 */
	public Message(int messNum) {
		this.messNum = messNum;
		sixPack = new PacketList(); 
	}
	
	/**
	 * Getter for the message number
	 * @return the current message number
	 */
	public int getMessageNum(){
		return messNum;
		
	}
	
	/**
	 * adds a packet to the packet list
	 * @param packetNum the number of the packet
	 * @param message the message to be added
	 */
	public void add(int packetNum, String message){
		Packet pack = new Packet(packetNum, message);
		sixPack.add(pack);
	}
	
	/**
	 * toString method that prints the packet number and message
	 * @param messNum the number of the message
	 * @return s the string to be printed
	 */
	public String toString(){
		String s = "";
		
		s = "--- Message " + messNum + "\n";
		s+= sixPack.toString(messNum);
		s+= "--- End Message " + messNum + "\n\n";
		
		return s;
	}
	
	/**
	 * Simple toString to check order of message without packets.
	 * @return toString of message.
	 */
	public String toStringNodes(){
		String s = "";
		s += messNum + " ";
		return s;
	}
	
	/**
	 * Returns the packet list
	 * @return sixpack the packet list
	 */
	public PacketList getPacketList() {
		return sixPack;
	}
	
	/**
	 * Sets a message's packet list to the new one.
	 * @param newPacketList new packetlist
	 */
	public void setPacketList(PacketList newPacketList){
		this.sixPack = newPacketList;
	}

	
}
