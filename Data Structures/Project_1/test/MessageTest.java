import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author mmackrell
 *
 */
public class MessageTest {
	
	public static final int MESSNUM = 1;

	
	/**
	 * Test method for {@link Message#Message(int, int, java.lang.String)}.
	 */
	@Test
	public void testMessage() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link Message#getMessageNum()}.
	 */
	@Test
	public void testGetMessageNum() {
		Message whatAMess = new Message(MESSNUM, 2, "Curtis cant find his constructor");
		
		int num = whatAMess.getMessageNum();
		
		assertEquals(MESSNUM, num);
	}

	/**
	 * Test method for {@link Message#add(int, java.lang.String)}.
	 */
	@Test
	public void testAdd() {
		Packet packYourThingsWereLeaving = new Packet(1, "Curtis is getting really close");
		PacketList packYourBags = new PacketList(packYourThingsWereLeaving);
		
		assertEquals("Curtis is getting really close\n", packYourBags.toString(1));
		Message mess = new Message(MESSNUM, 2, "Curtis has a book");
		mess.add(2, "Add this packet");
		assertEquals("still an inf loop", mess.toString());
	}

	/**
	 * Test method for {@link Message#toString()}.
	 */
	@Test
	public void testToString() {
		Message whatAMess = new Message(MESSNUM, 1, "Curtis cant find his constructor");
		assertEquals("--- Message 1\nCurtis cant find his constructor\n\n--- End Message 1", whatAMess.toString());
		
	}

	

}
