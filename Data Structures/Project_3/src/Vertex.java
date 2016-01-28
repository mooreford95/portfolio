/**
 * Represents a vertex in a graph.
 * 
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class Vertex {

	/** Array containing all the edges of the vertex */
	private GenericList<Vertex> adjVertices;
	/** ID of the vertex, an String*/
	private String id;
	/** Determines if the vertex is marked or not */
	private boolean marked;
	/** The popularity of the vertex */
	private double popularity;
	/** A pointer to this vertex's previous vertex in a path */
	private Vertex backPointer;
	/** Current popular BFS depth */
	private int level;

	/**
	 * Constructs the vertex with the given ID.
	 *
	 * @param id of vertex to be created.
	 */
	public Vertex(String id) {
		this.id = id;
		this.marked = false;
		adjVertices = new GenericList<Vertex>();
		this.backPointer = null;
		this.level = 0;
		this.popularity = 0.0;
	}

	/**
	 * Returns ID of this node.
	 *
	 * @return id of this node.
	 */
	public String getid() {
		return this.id;
	}

	/**
	 * Indicates if given node is attached to this node.
	 *
	 * @param node
	 *            the node to check against.
	 * @return true if the nodes have an edge.
	 */
	public boolean isAttached(Vertex vrt) {
		for (int i = 0; i < adjVertices.size(); i++) {
			if ( adjVertices.get(i).getid().equals(vrt.getid()) ) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if two nodes are the same.
	 * 
	 * @return true if the nodes have the same id.
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Vertex))
			return false;
		Vertex vrt = (Vertex) o;

		return (vrt.getid() == this.getid());
	}
	
	/**
	 * Basic override of default toString method.
	 * 
	 * @return string version of this node
	 */
	public String toString() {
		return "" + this.id;
	}
	
	/**
	 * Sets the node to a marked status 
	 * meaning that it has been visited. 
	 * 
	 * @param flag boolean variable to set vertex's marked to
	 */
	public void setMarked (boolean flag) {
		this.marked = flag;
	}
	
	/** 
	 * Checks if the vertex is marked (visited)
	 * 
	 * @return true if marked, false if unmarked
	 */
	public boolean isMarked() {
		return this.marked;
	}
	
	/**
	 * Returns the adjacency list of a vertex
	 * 
	 * @return adjacency list of current vertex
	 */
	public GenericList<Vertex> getAdjVertices() {
		return adjVertices;
	}

	/**
	 * Gets the popularity of a vertex in the graph
	 * 
	 * @return popularity of the current vertex
	 */
	public double getPopularity() {
		return popularity;
	}

	/**
	 * Sets the popularity of a vertex to a specified value
	 * 
	 * @param popularity to set vertex to
	 */
	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}
	
	/**
	 * Gets the back pointer of a vertex
	 * 
	 * @return previous vertex in a path
	 */
	public Vertex getBackPointer() {
		return this.backPointer;
	}
	
	/**
	 * Sets the back pointer of a vertex
	 * 
	 * @param back previous vertex in the path
	 */
	public void setBackPointer( Vertex back ) {
		this.backPointer = back;
	}
	
	/**
	 * Gets the current level of the vertex in a popular BFS
	 * 
	 * @return the level of the vertex in the popular BFS
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * Sets the level of the vertex in a popular BFS
	 * 
	 * @param level of the vertex in the popular BFS
	 */
	public void setLevel( int level ) {
		this.level = level;
	}

}
