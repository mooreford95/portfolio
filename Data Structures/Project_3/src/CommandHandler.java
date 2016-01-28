import java.util.HashMap;

/**
 * Wrapper class that handles the command input by calling Graph.java methods.
 * 
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 */
public class CommandHandler {

	/** graph that holds all vertices */
	private Graph graph;
	/** hashGraph to quickly link a name with a vertex object */
	private HashMap<String, Vertex> hashGraph;
	
	/**
	 * Constructor for the command handler class.
	 * @param graph that holds all vertices 
	 * @param hashGraph to quickly link a name with a vertex object
	 */
	public CommandHandler(Graph graph, HashMap<String, Vertex> hashGraph) {
		this.graph = graph;
		this.hashGraph = hashGraph;
	}
	
	/**
	 * This method handles isfriend input by calling Graph.java's isFriend method.
	 * 
	 * @param name1 the name of the first person
	 * @param name2 the name of the second person
	 */
	public void isFriend(String name1, String name2){
		if( graph.isFriend(hashGraph.get(name1), hashGraph.get(name2)) ){
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
	}
	
	/**
	 * This method handles mutual input by calling Graph.java's mutual method.
	 * 
	 * @param name1 the name of the first person
	 * @param name2 the name of the second person
	 */
	public void mutual(String name1, String name2){
		System.out.print( graph.mutual(hashGraph.get(name1), hashGraph.get(name2)) );
	}
	
	/**
	 * This method handles relation input by calling Graph.java's relation method.
	 * 
	 * @param name1 the name of the first person
	 * @param name2 the name of the second person
	 */
	public void relation(String name1, String name2){
		System.out.print( graph.relation(hashGraph.get(name1), hashGraph.get(name2)) );
		graph.unmark();
	}
	
	/**
	 * This method handles notconnected input by calling Graph.java's notConnected method.
	 */
	public void notConnected(){
		System.out.println( graph.getNotConnected() );
		graph.unmark();
	}
	
	/**
	 * This method handles popular input by calling Graph.java's popular method.
	 */
	public void popular(){
		System.out.print( graph.getPopular() );
	}
}
