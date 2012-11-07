import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.lang.IndexOutOfBoundsException;
import java.util.PriorityQueue;

public class UndirectedWeightedGraph extends UWGraph {

    public class Node implements Comparable<Node> {
	
	public final int position;
	public final int cost;

	public Node(int position, int cost) {
	    this.position = position;
	    this.cost = cost;
	}
	
	public int compareTo(Node other) {
	    if (this.cost < other.cost) return -1;
	    if (this.cost > other.cost) return 1;
	    return 0;
	}
    }

    private int[][] adjacencyMatrix;
    private int numberOfVertices;
    private int root = -1;
    
    /*
     * Using Adjacency Matrix
     */
    public UndirectedWeightedGraph(int numberOfVertices) {
	super(numberOfVertices);
	this.numberOfVertices = numberOfVertices;
	adjacencyMatrix = new int[numberOfVertices + 1][numberOfVertices + 1];
    }

    /* Adds an edge connecting the source vertex to the destination vertex. 
     * @param source The first vertex of this edge
     * @param destination The second vertex of this edge
     * @param distance The length of this edge
     */
    public void addEdge(int source, int destination, int distance) throws IndexOutOfBoundsException {
	if (root < 0) root = source;

	// Check that value is in-bounds
	if (source > numberOfVertices || source < 0 
	    || destination > numberOfVertices || destination < 0) {
	    throw new IndexOutOfBoundsException("Can't add edge: Vertex is out of bounds");
	}
	
	// Add to Adjacency Matrix
	adjacencyMatrix[source][destination] = distance;
	adjacencyMatrix[destination][source] = distance;
    }
  
    /* Performs a Bredth-First Search, returning a new Graph that
     * contains only the edges searched from the source to all other
     * connected vertices..
     * @param source The source vertex that becomes the root of the BFS tree
     * @return A Graph containing only the edges searched during the
     * task of performing a Bredth-First Search.
     */
    @Complexity (
         basic_operation = "Comparison", 
         N = "Number of Vertices, K number of Edges", 
         number_of_steps = "N^2 + N + K",
         big_O = "N^2"
		 )
    public UWGraph breadthFirstSearch(int source) throws IndexOutOfBoundsException {
	if (source < 0 || source > numberOfVertices) {
	    String err = "The requested vertex (" + source 
		+ ") is not contained within this graph";
	    throw new IndexOutOfBoundsException(err);
	    
	}

	boolean[] visited = new boolean[numberOfVertices];
	// Using linked list as FIFO
	LinkedList<Integer> queue = new LinkedList<Integer>();
	UWGraph BFS = new UndirectedWeightedGraph(numberOfVertices);

	queue.add(source);
	while(!queue.isEmpty()) {
	    int currentVertex = queue.poll();
	    visited[currentVertex] = true;
	    for (int j = 0; j < adjacencyMatrix[currentVertex].length; j++) {
		if (adjacencyMatrix[currentVertex][j] != 0) {
		    if (!visited[j]) {
			// Add it to queue
			queue.add(j);
			// Set Color black (Visited)
			visited[j] = true;
			// Add connection with currentVertex to resulting graph.
			BFS.addEdge(currentVertex, j, getDistance(currentVertex, j));
		    }
		}
	    }

	}

	return BFS;
    }
    
    /* Performs a Depth-First Search, returning a new Graph that
     * contains only the edges searched from the source to all other
     * connected vertices.
     * @param source The source vertex that becomes the root of the DFS tree
     * @return A Graph containing only the edges searched during the
     * task of performing a Depth-First Search.
     */
    public UWGraph depthFirstSearch(int source) throws IndexOutOfBoundsException {
	
	if (source < 0 || source > numberOfVertices) {
	    String err = "The requested vertex (" + source 
		+ ") is not contained within this graph";
	    throw new IndexOutOfBoundsException(err);
	    
	}
	
	UWGraph result = new UndirectedWeightedGraph(numberOfVertices);
	boolean[] visited = new boolean[numberOfVertices];
	depthFirstSearch(source, result, visited);
	return result;
    }

    @Complexity (
		 basic_operation = "search", 
		 N = "Number of Vertices, K = Number of Edges", 
		 number_of_steps = "N + K",
		 big_O = "N"
		 )
    public void depthFirstSearch(int current, UWGraph DFS, boolean[] visited) {
	// Mark as visited
	visited[current] = true;

	for (int j = 0;j < adjacencyMatrix[current].length; j++) {
		if (adjacencyMatrix[current][j] != 0) {
		    if (!visited[j]) {
			// Add connection with currentVertex to resulting graph.
			DFS.addEdge(current, j, getDistance(current, j));
			// Add it to stack (implicitly)
			depthFirstSearch(j, DFS, visited);
		    }
		}
	}
    }
    
    /* Creates a Minimum Spanning Tree from the source to all other
     * connected vertices.
     * @param source The source vertex that becomes the root of the MST tree
     * @return A Graph containing only the edges contained in the
     * Minimum Spanning Tree of the original Graph.
     */
    @Complexity (
		 basic_operation = "Comparison", 
		 N = "Number of Vertices, K number of Edges", 
		 number_of_steps = "N^2 + N + K",
		 big_O = "N^2"
		 )
    public UWGraph minimumSpanningTree(int source) throws IndexOutOfBoundsException {
	if (source < 0 || source > numberOfVertices) {
	    String err = "The requested vertex (" + source 
		+ ") is not contained within this graph";
	    throw new IndexOutOfBoundsException(err);
	    
	}
	
	boolean[] visited = new boolean[numberOfVertices];
	int[] parent = new int[numberOfVertices];
	PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
	UWGraph MST = new UndirectedWeightedGraph(numberOfVertices);

	priorityQueue.add(new Node(source, 0));

	while(!priorityQueue.isEmpty()) {

	    // Add connection with currentVertex to resulting graph.
	    Node currentNode = priorityQueue.poll();
	    MST.addEdge(currentNode.position, parent[currentNode.position], 
			getDistance(currentNode.position, parent[currentNode.position]));

	    if (!visited[currentNode.position]) {
		visited[currentNode.position] = true;
		
		for (int j = 0; j < adjacencyMatrix[currentNode.position].length; j++) {
		    if (adjacencyMatrix[currentNode.position][j] != 0
			&& !visited[j]) {
			// Add it to queue
			priorityQueue.add(new Node(j, adjacencyMatrix[currentNode.position][j]));		
			parent[j] = currentNode.position;
		    }
		}
	    }
	    
	}
	
	return MST;
    }
    
    /* Solves the Single-Source Shortest Path problem, returning a new
     * graph containing only the edges from the source to all other
     * connected vertices.
     * contains negative edges
     * @param source The source vertex that becomes the root of the SSSP tree
     * @return A Graph containing only the edges for the Single Source 
     * Shortest Path of the original Graph.
     * @throw java.lang.UnsupportedOperationException if the Graph 
     */
    
    @Complexity (
		 basic_operation = "comparison", 
		 N = "Number of Vertices", 
		 number_of_steps = "N^2+N+K",
		 big_O = "N^2"
		 )
    public UWGraph singleSourceShortestPath(int source)
	throws UnsupportedOperationException, IndexOutOfBoundsException {
	if (source < 0 || source > numberOfVertices) {
	    String err = "The requested vertex (" + source 
		+ ") is not contained within this graph";
	    throw new IndexOutOfBoundsException(err);
	    
	}

	boolean[] visited = new boolean[numberOfVertices];
	int[] parent = new int[numberOfVertices];
	PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
	UWGraph SSSP = new UndirectedWeightedGraph(numberOfVertices);
	int[] distance = new int[numberOfVertices];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;

	priorityQueue.add(new Node(source, 0));

	while(!priorityQueue.isEmpty()) {

	    Node currentNode = priorityQueue.poll();
	        
	    // If it's not been visited
	    if (!visited[currentNode.position]) {

		// Add connection to resulting graph. Note that the best distance and 
		// best parent will be picked as they are read according to parent[]
		// and distance[], which un turn contains the best choice.
		SSSP.addEdge(currentNode.position, parent[currentNode.position], 
			     distance[currentNode.position]);
		// Mark it
		visited[currentNode.position] = true;
		
		// For all it's neighbors
		for (int j = 0; j < adjacencyMatrix[currentNode.position].length; j++) {
		    // If they are not visited
		    if (adjacencyMatrix[currentNode.position][j] != 0
			&& !visited[j]) {
			// Assume the best distance is the distance we already have.
			// If we don't have one then its equal to Integer.MAX_VALUE.
			int bestDistance = distance[j];
			// If this distance is greater than what would be 
			// the distance we are calculating. We want it to be smaller,
			// So we pick the new distance. And also modify which parent we
			// are picking.
			if (bestDistance > distance[currentNode.position] 
			    + getDistance(currentNode.position, j)) {
			    bestDistance = distance[currentNode.position]
				+ getDistance(currentNode.position, j);
			    if (bestDistance < 0) {
				String error = "Cannot find shortest path: the graph contains negative weights";
				throw new UnsupportedOperationException(error);
			    }
			    distance[j] = bestDistance;
			    parent[j] = currentNode.position;
			}
			// Add it to the queue. Note that a vertex can be added twice,
			// But only the best distance will be picked and the that vertex
			// will be marked as visited, so the duplicate won't be added.
			priorityQueue.add(new Node(j, bestDistance));
		    }
		}
	    }
	    
	}
	
	return SSSP;
    }
    
    /* Returns a boolean value determining whether this Graph is a tree. 
     * If this Graph is a tree, then there are no cycles and there exists
     * a path from any source to any destination.
     * @return true if this graph is a tree, false otherwise.
     */
    public boolean isTree() {
	
	// Using DFS
	return isTree(this.root, new boolean[numberOfVertices]);
    }

    @Complexity (
		 basic_operation = "search", 
		 N = "Number of Vertices, K = Number of Edges", 
		 number_of_steps = "N + K",
		 big_O = "N"
		 )
    public boolean isTree(int current, boolean[] visited) {
	// Mark as visited
	visited[current] = true;

	for (int j = 0; j < adjacencyMatrix[current].length; j++) {
		if (adjacencyMatrix[current][j] != 0) {
		    // If we find a node that was already visited then
		    // it means we have a cycle.
		    if (visited[j]) return false; 
		    // Add it to stack (implicitly)
		    return isTree(j, visited);
		}
	}

	return true;
    }
    
    /* Returns a boolean value determining whether the source and destination
     * vertices are connected by a path in this Graph.
     * @param source The source vertex
     * @param destination The end vertex
     * @return true if a path exists connecting source and destination, false otherwise
     */
    public boolean isConnected(int source, int destination) throws IndexOutOfBoundsException {
	System.out.print(toString());
	
	return search(source, destination, new boolean[numberOfVertices]);
    }

    @Complexity (
		 basic_operation = "search", 
		 N = "Number of Vertices, K = Number of Edges", 
		 number_of_steps = "N + K",
		 big_O = "N"
		 )
    public boolean search(int current, int searchTerm, boolean[] visited) {
	
	// Found!
	if (current == searchTerm) return true;

	// Mark as visited
	visited[current] = true;

	for (int j = 0; j < adjacencyMatrix[current].length; j++) {
		if (adjacencyMatrix[current][j] != 0) {
		    if (!visited[j]) { 
			// Add it to stack (implicitly)
			if (search(j, searchTerm, visited)) return true;
		    }
		}
	}

	return false;
    }
    
    /* Return the distance between two vertices source and destination.
     * @param source The source vertex
     * @param destination The end vertex
     * @return Distance between source and destination vertices
     */
    @Complexity (
		 basic_operation = "comparison", 
		 N = "Number of Vertices", 
		 number_of_steps = "N^2+N+K",
		 big_O = "N^2"
		 )
    public int distance(int source, int destination) throws IndexOutOfBoundsException {
	
	// Dijkstras
	boolean[] visited = new boolean[numberOfVertices];
	PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
	UWGraph SSSP = new UndirectedWeightedGraph(numberOfVertices);
	int[] distance = new int[numberOfVertices];
	Arrays.fill(distance, Integer.MAX_VALUE);
	distance[source] = 0;
	int result = -1;

	priorityQueue.add(new Node(source, 0));

	while(!priorityQueue.isEmpty()) {

	    Node currentNode = priorityQueue.poll();
	    if (currentNode.position == destination) {
		result = distance[destination];
		break;
	    }
		
	    // If it's not marked as visited
	    if (!visited[currentNode.position]) {
		// Mark it
		visited[currentNode.position] = true;
		
		// For all it's neighbors
		for (int j = 0; j < adjacencyMatrix[currentNode.position].length; j++) {
		    // If they are not visited
		    if (adjacencyMatrix[currentNode.position][j] != 0
			&& !visited[j]) {
			// Assume the best distance is the distance we already have.
			// If we don't have one then its equal to Integer.MAX_VALUE.
			int bestDistance = distance[j];
			// If this distance is greater than what would be 
			// the distance we are calculating. We want it to be smaller,
			// So we pick the new distance. And also modify which parent we
			// are picking.
			if (bestDistance > distance[currentNode.position] 
			    + getDistance(currentNode.position, j)) {
			    bestDistance = distance[currentNode.position]
				+ getDistance(currentNode.position, j);
			    if (bestDistance < 0) {
				String error = "Cannot find shortest path: the graph contains negative weights";
				throw new UnsupportedOperationException(error);
			    }
			    distance[j] = bestDistance;
			}
			// Add it to the queue. Note that a vertex can be added twice,
			// But only the best distance will be picked and the that vertex.
			// Will be marked as visited, so the duplicate won't be added.
			priorityQueue.add(new Node(j, bestDistance));
		    }
		}
	    }
	    
	}
	
	return result;
    }
    
    /* Returns a String-representation of this Graph that, when printend,
     * can be read by a human to verify and recreate the Graph.
     *     source: (dest1,dist1) (dest2,dist2)
     *     source: (dest1,dist1) (dest2,dist2)
     * @return A human-readable String representation of this Graph.
     */
    public String toString() {
	int numberOfVertices = adjacencyMatrix.length;
	String result = "";

	// Print only half of the matrix. Eg. if A is connected
	// to B, then B is also connected to A because the graph is
	// undirected. Only print the connection from A to B.
	for (int j = 0; j < numberOfVertices; j++) {
	    result += j + ":";
	    for (int k = j; k < numberOfVertices; k++) {
		if (adjacencyMatrix[j][k] != 0) {
		    result += " (" + k + "," + adjacencyMatrix[j][k] + ")";
		}
	    }
	    result += "\n";
	}
	
	return result;
    }

    public int getDistance(int source, int destination) {
	return this.adjacencyMatrix[source][destination];
    }
}