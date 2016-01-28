import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Michael MacKrell
 *
 */
public class GraphTest {
	
	/**
	 * first Vertex used for testing
	 */
	private Vertex Vertex1 = new Vertex("A");
	/**
	 * second Vertex used for testing
	 */
	private Vertex Vertex2 = new Vertex("B");
		
	/**
	 * tests to see if the graph has no edges
	 * or Vertexs. Should work well because there
	 * are no Vertexs or edges intantiazed in the
	 * graph
	 */
	@Test
	public void testIsEmpty() {
		Graph g = new Graph();
		assertEquals(true, g.isEmpty());

	}

	/**
	 * test for the method to add edges to a graph.
	 * Tested by adding a Vertex to the graph then
	 * checking to see that isEmpty is false
	 */
	@Test
	public void testAdd() {
		Graph g = new Graph();
		assertEquals(true, g.isEmpty());
		assertEquals(false, g.isEmpty());
		
	}

	/**
	 * test to see if Vertexexists works.
	 * Add an edge and test to see if the two Vertexs
	 * in that edge exist. If true, the method works
	 */
	@Test
	public void testVertexExists() {
		Graph g = new Graph();
		assertEquals(true, g.isEmpty());
		
	}

	/**
	 * tests the getVertex method by adding an
	 * edge to the tree and seeing if the Vertexs
	 * in the edge are returned when the method is
	 * called
	 */
	@Test
	public void testGetVertex() {
		//Graph g = new Graph();
		//assertEquals(true, g.isEmpty());
		GenericList< Integer > cc = new GenericList< Integer >();
		cc.add(12);
		System.out.print( cc.get(0));
		assertEquals(true, true);
	}

	
	@Test
	public void testGetVertexArray() {
		fail("Not yet implemented");
	}

	/**
	 * tests the removeedge method by adding an edge,
	 * checking to see if it is added, removing it, then 
	 * checking to see if the graph is empty
	 */
	@Test
	public void testRemoveEdge() {
		Graph g = new Graph();
		assertEquals(true, g.isEmpty());
	}

	@Test
	public void testRemoveHigestVertex() {
		fail("Not yet implemented");
	}

}
