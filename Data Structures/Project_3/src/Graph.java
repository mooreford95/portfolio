/**
 * Representation of a Graph object. Stored as an adjacency list.
 * 
 * @author Thomas Ortiz
 * @author Michael Mackrell
 * @author Jacob Stone
 * @author Curtis Moore
 *
 */

public class Graph {

	/**
	 * list of vertices in this graph
	 */
	private GenericList<Vertex> vertices;

	/**
	 * The popular calculation for this graph, stored to increase efficiency on
	 * multiple runs.
	 */
	private String popular;

	/**
	 * The notConnected calculation for this graph
	 */
	private int notConnected;

	/**
	 * Constructs the Graph.
	 */
	public Graph() {
		vertices = new GenericList<Vertex>();
		popular = null;
		notConnected = -1;
	}

	/**
	 * Determines if this graph is empty
	 * 
	 * @return true if the graph is empty.
	 */
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	/**
	 * Returns the array of nodes. Used in printing.
	 * 
	 * @return GenericList of nodes.
	 */
	public GenericList<Vertex> getVertexArray() {
		return vertices;
	}

	/**
	 * Determines if person1 is a friend of person2
	 * 
	 * @param person1 first person to check against
	 * @param person2 second person to check against
	 * @return true if friends, false otherwise.
	 */
	public boolean isFriend(Vertex person1, Vertex person2) {
		return person1.isAttached(person2);
	}

	/**
	 * Method to get how vertices are related to one another
	 * 
	 * @param start the first vertex to start at
	 * @param goal the final vertex to end at
	 * @return string the string representation of the path from start to finish
	 */
	public String relation(Vertex start, Vertex goal) {
		if (start.isAttached(goal)) {
			return "" + start.toString() + "\n" + goal.toString() + "\n";
		}

		Queue<Vertex> q = new Queue<Vertex>();
		q.add(start);
		start.setMarked(true);
		boolean pathFound = false;

		while (!(q.isEmpty())) {
			Vertex current = q.remove();

			if (current.getid().equals(goal.getid())) {
				// path is found
				pathFound = true;
				break;
			}

			GenericIterator<Vertex> e = current.getAdjVertices().iterator();

			while (e.hasNext()) {
				Vertex adj = e.next();

				if (!(adj.isMarked())) {
					q.add(adj);
					adj.setMarked(true);
					adj.setBackPointer(current);
				}

			}
		}

		if (pathFound) {
			String shortestPath = "";
			boolean foundStart = false;
			Vertex current = goal;
			Queue<Vertex> answer = new Queue<Vertex>();
			while (!foundStart) {

				if (current.getid().equals(start.getid())) {
					foundStart = true;
					answer.add(current);
				} else {
					answer.add(current);
					current = current.getBackPointer();
				}

			}

			while (!(answer.isEmpty())) {
				shortestPath = answer.peek().getid() + "\n" + shortestPath;
				answer.remove();
			}

			return shortestPath;
		}

		return "";
	}

	/**
	 * Method to return all the mutual friends between two people
	 * 
	 * @param person1 the first person to check
	 * @param person2 the second person to check
	 * @return string of all mutual friends.
	 */
	public String mutual(Vertex person1, Vertex person2) {
		String s = "";
		GenericIterator<Vertex> e = person1.getAdjVertices().iterator();
		while (e.hasNext()) {
			Vertex next = e.next();
			if (person2.getAdjVertices().contains(next)) {
				s += next + "\n";
			}
		}

		return s;
	}

	/**
	 * Wrapper for popular. Only calls popular if it has not yet been evaluated.
	 * 
	 * @return the result of calling popular
	 */
	public String getPopular() {
		if (this.popular == null) {
			return popular();
		} else {
			return this.popular;
		}
	}

	/**
	 * Wrapper method for notConnected. Only calls it if it has not yet been
	 * evaluated.
	 * 
	 * @return the result of notConnected
	 */
	public int getNotConnected() {
		if (this.notConnected == -1) { // if we haven't done not connected yet.
			return notConnected();
		} else {
			return this.notConnected;
		}
	}

	/**
	 * Method to report how many people are not connected in the social network
	 * 
	 * @return the number of non-connected people
	 */
	private int notConnected() {
		// The sum we will return
		int overallSum = 0;
		// A list of integers, each entry will be the number of vertices
		// in each connected component
		GenericList<Integer> cc = new GenericList<Integer>();
		// An iterator over the entire list of vertices in the graph
		GenericIterator<Vertex> vrtList = vertices.iterator();
		// For each vertex in the graph, find it's connected component and
		// calculate how many vertices are in that particular connected
		// component
		while (vrtList.hasNext()) {
			// the current vertex we are looking at
			Vertex current = vrtList.next();
			// if we have not seen this vertex before, it means that this is a
			// new connected component we have not seen yet.
			if (!current.isMarked()) {
				// add this connected component to our list of integers. That
				// is, add it as
				// the number of vertices in that connect component
				cc.add(findComponentNodes(current));
			}
		}

		// now sum up all the node that are not connected to each other
		// note, this may could be done in just one for loop, but even so
		// it is not directly affected by input unless no vertices are connected
		// in the graph at all
		for (int i = 0; i < cc.size(); i++) {
			int currentSum = 0;
			for (int j = i + 1; j < cc.size(); j++) {
				currentSum += cc.get(i) * cc.get(j);
			}
			overallSum += currentSum;
		}
		this.notConnected = overallSum;
		return overallSum;
	}

	/**
	 * Finds the number of component nodes each person has, effectively telling
	 * us how many "friends" they have
	 * 
	 * @param person the person we are finding the nodes from
	 * @return the number of component nodes that person has
	 */
	private int findComponentNodes(Vertex person) {
		int count = 0;
		Queue<Vertex> q = new Queue<Vertex>();
		q.add(person);
		person.setMarked(true);
		count++;

		while (!q.isEmpty()) {
			Vertex current = q.remove();
			GenericIterator<Vertex> e = current.getAdjVertices().iterator();

			while (e.hasNext()) {
				Vertex adj = e.next();
				if (!adj.isMarked()) {
					adj.setMarked(true);
					q.add(adj);
					count++;
				}
			}
		}

		return count;
	}

	/**
	 * Unmarks all vertices in the graph.
	 */
	public void unmark() {
		for (int i = 0; i < vertices.size(); i++) {
			vertices.get(i).setMarked(false);
		}
	}

	/**
	 * Method to print out the graph
	 * 
	 * @return a string representation of the graph
	 */
	public String printGraph() {
		return this.vertices.printGenericList();
	}

	/**
	 * Method to report the most popular people in the social network.
	 * popularity is determined by the number of relations that a node has to
	 * other nodes
	 * 
	 * @return a string of the most popular people in the social network
	 */
	private String popular() {
		double max = 0;
		GenericIterator<Vertex> e = vertices.iterator();

		while (e.hasNext()) {
			Vertex current = e.next();
			current.setPopularity(popularBFS(current));
			unmark();
			if (current.getPopularity() > max) {
				max = current.getPopularity();
			}
		}

		// build the popular String to use in the program
		String popularString = "";
		e = vertices.iterator();
		while (e.hasNext()) {
			Vertex current = e.next();
			// If the current person's popularity is one of the
			// most popular people, add them to the string
			if (current.getPopularity() == max)
				popularString += current.getid() + "\n";
		}
		this.popular = popularString;
		return popularString;
	}

	/**
	 * Method to search for popularity using a breadth first search.
	 * Returns the number of connected nodes vs the depth at which they are connected.
	 * 
	 * @param start the node to begin the search at
	 * @return the representation of the popularity as a double
	 */
	private double popularBFS(Vertex start) {
		int lengthSum = 0;
		int vertCounter = 0;

		Queue<Vertex> q = new Queue<Vertex>();
		q.add(start);
		start.setMarked(true);
		start.setLevel(0);

		while (!(q.isEmpty())) {
			Vertex current = q.remove();

			GenericIterator<Vertex> e = current.getAdjVertices().iterator();

			while (e.hasNext()) {
				Vertex adj = e.next();
				if (!(adj.isMarked())) {
					q.add(adj);
					adj.setMarked(true);
					adj.setLevel(current.getLevel() + 1);
					vertCounter++;
					lengthSum += adj.getLevel();
				}
			}
		}

		if (vertCounter == 0) {
			return 0.0;
		} else {
			return (double) vertCounter / lengthSum;
		}

	}

}