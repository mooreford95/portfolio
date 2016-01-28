import org.junit.Assert.*;
import org.junit.Test;

public class TreeTest {

	
	@Test
	public void testCase1(){
		Tree tree = new Tree();
		Tree tree2 = new Tree();
		Ticket ticket1 = new Ticket(6, 1);
		Ticket ticket2 = new Ticket(3, 1);
		Ticket ticket3 = new Ticket(2, 1);
		Ticket ticket4 = new Ticket(1, 1);
		Ticket ticket5 = new Ticket(8, 1);
		Ticket ticket6 = new Ticket(11, 1);
		Ticket ticket7 = new Ticket(13, 1);
		
		Ticket ticket8 = new Ticket(5, 1);
		Ticket ticket9 = new Ticket(7, 1);
		Ticket ticket10 = new Ticket(10, 1);
		Ticket ticket11 = new Ticket(12, 1);
		
		
		tree.insert(ticket1);
		tree.insert(ticket2);
		tree.insert(ticket3);
		tree.insert(ticket4);
		tree.insert(ticket5);
		tree.insert(ticket6);
		tree.insert(ticket7);
		tree.insert(ticket8);
		tree.insert(ticket9);
		
		
		
		System.out.println( tree.query(13));
		
		//tree.remove(7);
		
		
		
		tree.insert(ticket10);
		tree.insert(ticket11);
		
		
		
		//
		//tree.printInorder();
		System.out.println();
		
		tree.remove(8);
		
		//tree.printInorder();
		System.out.println();
		
		System.out.println( tree.query(2));
		
		//tree.printInorder();
		System.out.println();
		
		tree.getHighest();
		
		System.out.println( "The heighest is: " + tree.getHighestTicket().getPriority());
		
		//tree.printInorder();
		System.out.println();
		
		
		//tree.remove(p)
		
		//tree.printInorder(tree.getRoot());
		//System.out.println();
		//tree.remove(3);
		//System.out.println(tree.query(1));
		//tree.printInorder(tree.getRoot());
		//System.out.println();
		
		//tree2.insert(ticket8);
		//tree2.insert(ticket9);
		//tree2.insert(ticket10);
		//System.out.println();
		//tree2.remove(100);
		//System.out.println();
		
		
		//tree.remove(1);
		//tree.printInorder(tree.getRoot());
		//System.out.println();
	}
	
}
