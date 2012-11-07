import java.util.List;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.lang.IndexOutOfBoundsException;

public class UndirectedWeightedGraph extends UWGraph {
    
    /*
     * For use with adjacency list.
     */
    public class Node {
	
	private int vertex;
	private int weight;
	
	public Node throws UnsupportedOperationException(int vertex, int weight) {
	    this.vertex = vertex;
	    this.weight = weight;
	}
    }

    int[][] adjacencyMatrix;
    List[] adjacencyList;
    
    /*
     * Using Adjacency Matrix and Adjacency List
     */
    public UndirectedWeightedGraph(int numberOfVertices) {
	adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
	adjacencyList = new ArrayList<Node>[numberOfVertices];
    }
  
    /* Adds an edge connecting the source vertex to the destination vertex. 
     * @param source The first vertex of this edge
     * @param destination The second vertex of this edge
     * @param distance The length of this edge
     */
    public void addEdge(int source, int destination, int distance) throws IndexOutOfBoundsException {
	
	int numberOfVertices = adjacencyList.length; 

	if (source > (numberOfVertices - 1) || source < 0 
	    || destination > (numberOfVertices - 1) || destination < 0) {
	    throw new IndexOutOfBoundsException();
	}

	// Add to Adjacency List
	adjacencyList[source].add(new Node(destination, distance));
	adjacencyList[destination].add(new Node(source, distance));
	
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
    public UWGraph breadthFirstSearch(int source) throws IndexOutOfBoundsException {
	;
    }
    
    /* Performs a Depth-First Search, returning a new Graph that
     * contains only the edges searched from the source to all other
     * connected vertices.
     * @param source The source vertex that becomes the root of the DFS tree
     * @return A Graph containing only the edges searched during the
     * task of performing a Depth-First Search.
     */
    public UWGraph depthFirstSearch(int source) throws IndexOutOfBoundsException {
	;
    }
    
    /* Creates a Minimum Spanning Tree from the source to all other
     * connected vertices.
     * @param source The source vertex that becomes the root of the MST tree
     * @return A Graph containing only the edges contained in the
     * Minimum Spanning Tree of the original Graph.
     */
    public UWGraph minimumSpanningTree(int source) throws IndexOutOfBoundsException {
	;
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
    public UWGraph singleSourceShortestPath(int source)
	throws UnsupportedOperationException, throws IndexOutOfBoundsException {
	;
    }
    
    /* Returns a boolean value determining whether this Graph is a tree. 
     * If this Graph is a tree, then there are no cycles and there exists
     * a path from any source to any destination.
     * @return true if this graph is a tree, false otherwise.
     */
    public boolean isTree() {
	return false;
    }
    
    /* Returns a boolean value determining whether the source and destination
     * vertices are connected by a path in this Graph.
     * @param source The source vertex
     * @param destination The end vertex
     * @return true if a path exists connecting source and destination, false otherwise
     */
    public boolean isConnected(int source, int destination) throws IndexOutOfBoundsException {
	return false;
    }
    
    /* Return the distance between two vertices source and destination.
     * @param source The source vertex
     * @param destination The end vertex
     * @return Distance between source and destination vertices
     */
    public int distance(int source, int destination) throws IndexOutOfBoundsException {
	return -1;
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

	// For Adjacency Matrix
	for (int j = 0, int k = 0; j < numberOfVertices; j++) {
	    result += j + ":";
	    for (k = j; k < numberOfVertices; k++) {
		if (adjacencyMatrix[j][k] != null) {
		    result += " (" + k + "," + adjacencyMatrix[j][k] + ")";
		}
	    }
	}
	
	/*
	//For Adjacency List
	for (int j = 0; j < adjacencyList.length; j++) {
	    
	}
	*/
	
    }

}