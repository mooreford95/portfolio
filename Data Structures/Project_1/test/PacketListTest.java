import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Jacob
 *
 */
public class PacketListTest {
	
	private Packet pack1;
	
	private Packet pack2;
	
	private Packet pack3;
	
	private Packet pack4;
	
	private Packet pack5;
	
	private Packet pack6;
	
	private Packet pack7;
	
	private Packet pack8;
	
	private String testString;
	
	private PacketList sixPack;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(4, "Seven");
		pack4 = new Packet(7, "yasss");
		testString = "Good\n";
		testString += "Morning\n";
		testString += "WARNING: packet 3 of message 3 is missing\n";
		testString += "Seven\n";
		testString += "WARNING: packet 5 of message 3 is missing\n";
		testString += "WARNING: packet 6 of message 3 is missing\n";
		testString += "yasss\n";
		
		sixPack = new PacketList();
		
		
	}

	/**
	 * Test method for {@link PacketList#add(Packet)}.
	 */
	@Test
	public void testAdd() {
		sixPack.add(pack1);
		sixPack.add(pack2);
		sixPack.add(pack3);
		sixPack.add(pack4);
		assertEquals(testString, sixPack.toString(3));
		
		
	}

	/**
	 * Test method for {@link PacketList#toString(int)}.
	 */
	@Test
	public void testToStringInt() {
		sixPack.add(pack1);
		sixPack.add(pack2);
		sixPack.add(pack3);
		sixPack.add(pack4);
		assertEquals(testString, sixPack.toString(3));
	}
	
	/**
	 * Test method for {@link PacketList#toString(int)}.
	 */
	@Test
	public void testToStringTwo() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		
		
		sixPack.add(pack6);
		sixPack.add(pack7);
		sixPack.add(pack8);
		sixPack.add(pack1);
		sixPack.add(pack3);
		sixPack.add(pack2);
		sixPack.add(pack5); // 1 2 3 5 6 7 8
		
		testString = "Good\n";
		testString += "Morning\n";
		testString += "Seven\n";
		testString += "WARNING: packet 4 of message 3 is missing\n";
		testString += "Bad\n";
		testString += "Afternoon\n";
		testString += "Four\n";
		testString += "Nuuuu\n";
		assertEquals(testString, sixPack.toString(3));
	}
	
	/**
	 * Test method for {@link PacketList#toString(int)}.
	 */
	@Test
	public void testToStringThree() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		
		sixPack.add(pack6);
		sixPack.add(pack8);
		sixPack.add(pack1);
		sixPack.add(pack3);
		sixPack.add(pack2);
		sixPack.add(pack5); // 1 2 3 5 6 8
		
		testString = "Good\n";
		testString += "Morning\n";
		testString += "Seven\n";
		testString += "WARNING: packet 4 of message 3 is missing\n";
		testString += "Bad\n";
		testString += "Afternoon\n";
		testString += "WARNING: packet 7 of message 3 is missing\n";
		testString += "Nuuuu\n";
		assertEquals(testString, sixPack.toString(3));
	}
	
	/**
	 * Test method for {@link PacketList#toString(int)}.
	 */
	@Test
	public void testToStringFour() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		
		sixPack.add(pack8); // 8
		
		testString = "WARNING: packet 1 of message 3 is missing\n";
		testString += "WARNING: packet 2 of message 3 is missing\n";
		testString += "WARNING: packet 3 of message 3 is missing\n";
		testString += "WARNING: packet 4 of message 3 is missing\n";
		testString += "WARNING: packet 5 of message 3 is missing\n";
		testString += "WARNING: packet 6 of message 3 is missing\n";
		testString += "WARNING: packet 7 of message 3 is missing\n";
		testString += "Nuuuu\n";
		assertEquals(testString, sixPack.toString(3));
	}
	
	/**
	 * Test method for {@link PacketList#toString(int)}.
	 */
	@Test
	public void testToStringFive() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		
		sixPack.add(pack1); // 1
		
		testString = "Good\n";
		assertEquals(testString, sixPack.toString(3));
	}
	
	/**
	 * Test method for {@link PacketList#toStringNodes(int)}.
	 */
	@Test
	public void testToStringNodes() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		Packet pack9 = new Packet(1, "asdf");
		
		sixPack.add(pack6);
		sixPack.add(pack8);
		sixPack.add(pack1);
		sixPack.add(pack3);
		sixPack.add(pack2);
		sixPack.add(pack5); // 1 2 3 5 6 8
		sixPack.add(pack9);  // should replace 1 but doesnt.
		
		testString = "asdf\n";
		testString += "Morning\n";
		testString += "Seven\n";
		testString += "Bad\n";
		testString += "Afternoon\n";
		testString += "Nuuuu\n";
		System.out.println(sixPack.toStringNodes());
		assertEquals(testString, sixPack.toStringNodes());
	}
	
	/**
	 * Test method for {@link PacketList#toStringNodes(int)}.
	 */
	@Test
	public void testToStringNodesTwo() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		Packet pack9 = new Packet(1, "asdf");
		Packet pack10 = new Packet(2,"jklp");
		
		sixPack.add(pack6);
		sixPack.add(pack8);
		sixPack.add(pack1);
		sixPack.add(pack3);
		sixPack.add(pack2);
		sixPack.add(pack5); // 1 2 3 5 6 8
		sixPack.add(pack9);  // should replace 1 
		sixPack.add(pack10); // should replace 2 
		
		testString = "asdf\n";
		testString += "jklp\n";
		testString += "Seven\n";
		testString += "Bad\n";
		testString += "Afternoon\n";
		testString += "Nuuuu\n";
		System.out.println(sixPack.toStringNodes());
		assertEquals(testString, sixPack.toStringNodes());
	}
	
	/**
	 * Test method for {@link PacketList#toStringNodes(int)}.
	 */
	@Test
	public void testToStringNodesThree() {
		pack1 = new Packet(1, "Good");
		pack2 = new Packet(2, "Morning");
		pack3 = new Packet(3, "Seven");
		pack4 = new Packet(4, "yasss");
		pack5 = new Packet(5, "Bad");
		pack6 = new Packet(6, "Afternoon");
		pack7 = new Packet(7, "Four");
		pack8 = new Packet(8, "Nuuuu");
		Packet pack9 = new Packet(1, "asdf");
		Packet pack10 = new Packet(8,"jklp");
		
		sixPack.add(pack6);
		sixPack.add(pack8);
		sixPack.add(pack1);
		sixPack.add(pack3);
		sixPack.add(pack2);
		sixPack.add(pack5); // 1 2 3 5 6 8
		sixPack.add(pack9);  // should replace 1 
		sixPack.add(pack10); // should replace 8 
		
		testString = "asdf\n";
		testString += "Morning\n";
		testString += "Seven\n";
		testString += "Bad\n";
		testString += "Afternoon\n";
		testString += "jklp\n";
		System.out.println(sixPack.toStringNodes());
		assertEquals(testString, sixPack.toStringNodes());
	}
}
