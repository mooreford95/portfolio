import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MessageListTest {
    MessageList messyList;
    Message mes1;
    Message mes2;
    Message mes3;
    Packet goPack;
    
    @Before
    public void setUp() throws Exception {
        messyList = new MessageList();
        mes1 = new Message(10);
        mes2 = new Message(3);
        mes3 = new Message(17);
        goPack = new Packet(1, "123");
    }

    @Test
    public void testAdd() {
        messyList.add(mes1);
        messyList.add(mes2);
        messyList.add(mes3);
        String s = "--- Message 3\n";
        	  s += "--- End Message 3\n\n"; 
        	  s += "--- Message 10\n";
        	  s += "--- End Message 10\n\n"; 
        	  s += "--- Message 17\n";
        	  s += "--- End Message 17\n\n"; 
        	  
        //System.out.println(messyList.toString());
        assertEquals(s, messyList.toString());
        
    }
    
    @Test
    public void testAddTwo() {
    	mes1 = new Message(1, 1, "testing");
        mes2 = new Message(2, 1, "test2");
        Message mes3 = new Message(3, 1, "three");
    	
    	
        messyList.add(mes1);
        messyList.add(mes2);
        messyList.add(mes3);
        
        String s = "1 2 3 ";
        //System.out.println(messyList.toStringNodes());  
        assertEquals(s, messyList.toStringNodes());
    }
    
    @Test
    public void testAddThree() {
    	MessageList list = new MessageList();
    	mes1 = new Message(1, 1, "testing");
        mes2 = new Message(2, 1, "test2");
    	
        list.add(mes2);
        list.add(mes1);
        
        String s = "1 2 ";
        //System.out.println(list.toStringNodes());  
        assertEquals(s, list.toStringNodes());
    }
    
    @Test
    public void testAddFour() {
    	MessageList list = new MessageList();
    	mes1 = new Message(3, 1, "testing");
        mes2 = new Message(10, 1, "test2");
        Message mes3 = new Message(17, 1, "three"); 
    	
        list.add(mes1);
        list.add(mes3);
        list.add(mes2);
        
        String s = "3 10 17 ";
        //System.out.println(list.toStringNodes());  
        assertEquals(s, list.toStringNodes());
    }
    
    @Test
    public void testToString(){
    	MessageList list = new MessageList();
    	PacketList packetList = new PacketList();
    	
    	mes1 = new Message(1,1,"testing");
        
        Packet a = new Packet(1,"testing");
        Packet b = new Packet(2, "test2");
        
        String s = "--- Message 1\n";
	  		s += "testing\n";
	  		s += "test2\n";
	  		s += "--- End Message 1\n\n"; 
        
        packetList.add(a);
        packetList.add(b);
        
        list.add(mes1);
        list.getHead().data.setPacketList(packetList);
        
        assertEquals(s, list.toString());
    }
    
    @Test
    public void containsTest(){
        mes1 = new Message(1, 1, "testing");
        mes2 = new Message(2, 1, "test2");
        Message mes3 = new Message(3, 1, "three");
        
        
        messyList.add(mes1);
        messyList.add(mes2);
        messyList.add(mes3);
        
        assertEquals(messyList.contains(1), mes1);
        assertEquals(messyList.contains(2), mes2);
        assertEquals(messyList.contains(4), null);
        assertEquals(messyList.contains(3), mes3);
    }

}