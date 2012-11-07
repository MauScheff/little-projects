public abstract class UWGraph {
  
  public UWGraph(int numberOfVertices) {}
  
  /* Adds an edge connecting the source vertex to the destination vertex. 
   * @param source The first vertex of this edge
   * @param destination The second vertex of this edge
   * @param distance The length of this edge
   */
  public abstract void addEdge(int source, int destination, int distance);
  
  /* Performs a Bredth-First Search, returning a new Graph that
   * contains only the edges searched from the source to all other
   * connected vertices..
   * @param source The source vertex that becomes the root of the BFS tree
   * @return A Graph containing only the edges searched during the
   * task of performing a Bredth-First Search.
   */
  public abstract UWGraph breadthFirstSearch(int source);
  
  /* Performs a Depth-First Search, returning a new Graph that
   * contains only the edges searched from the source to all other
   * connected vertices.
   * @param source The source vertex that becomes the root of the DFS tree
   * @return A Graph containing only the edges searched during the
   * task of performing a Depth-First Search.
   */
  public abstract UWGraph depthFirstSearch(int source);
  
  /* Creates a Minimum Spanning Tree from the source to all other
   * connected vertices.
   * @param source The source vertex that becomes the root of the MST tree
   * @return A Graph containing only the edges contained in the
   * Minimum Spanning Tree of the original Graph.
   */
  public abstract UWGraph minimumSpanningTree(int source);
  
  /* Solves the Single-Source Shortest Path problem, returning a new
   * graph containing only the edges from the source to all other
   * connected vertices.
   * contains negative edges
   * @param source The source vertex that becomes the root of the SSSP tree
   * @return A Graph containing only the edges for the Single Source 
   * Shortest Path of the original Graph.
   * @throw java.lang.UnsupportedOperationException if the Graph 
   */
  public abstract UWGraph singleSourceShortestPath(int source);
  
  /* Returns a boolean value determining whether this Graph is a tree. 
   * If this Graph is a tree, then there are no cycles and there exists
   * a path from any source to any destination.
   * @return true if this graph is a tree, false otherwise.
   */
  public abstract boolean isTree();
  
  /* Returns a boolean value determining whether the source and destination
   * vertices are connected by a path in this Graph.
   * @param source The source vertex
   * @param destination The end vertex
   * @return true if a path exists connecting source and destination, false otherwise
   */
  public abstract boolean isConnected(int source, int destination);
  
  /* Return the distance between two vertices source and destination.
   * @param source The source vertex
   * @param destination The end vertex
   * @return Distance between source and destination vertices
   */
  public abstract int distance(int source, int destination);
  
  /* Returns a String-representation of this Graph that, when printend,
   * can be read by a human to verify and recreate the Graph.
   *     source: (dest1,dist1) (dest2,dist2)
   *     source: (dest1,dist1) (dest2,dist2)
   * @return A human-readable String representation of this Graph.
   */
  public abstract String toString();
}
