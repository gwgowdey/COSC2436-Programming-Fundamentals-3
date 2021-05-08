import java.util.List;

/**
 *
 * @param <V> is the generic type for a vertex
 */
public interface IGraph<V> {

    /**
     * Return the number of vertices in the graph
     * @return
     */
    public int getSize();

    /**
     * Return the vertices in the graph
     * @return
     */
    public List<V> getVertices();

    /**
     * Return the object for the specified vertex index
     * @param index
     * @return
     */
    public V getVertex(int index);

    /**
     * Return the index for the specified vertex object
     * @param vertex
     * @return
     */
    public int getIndex(V vertex);

    /**
     * Return the neighbors of vertex with the specified index
     * @param index
     * @return
     */
    public List<Integer> getNeighbors(int index);

    /**
     * Return the neighbors of vertex
     * @param vertex
     * @return
     */
    public List<Integer> getNeighbors(V vertex);

    /**
     * Return the degree of a vertex with the specified index
     * @param index
     * @return
     */
    public int getDegree(int index);

    /**
     * Return the degree of a vertex
     * @param vertex
     * @return
     */
    public int getDegree(V vertex);

    /**
     * Print the edges
     */
    public void printEdges();

    /**
     * Clear the graph
     */
    public void clear();

    /**
     * Add a vertex to the graph. This vertex will be unconnected until an edge is added that connects it.
     * @param vertex
     * @return
     */
    public boolean addVertex(V vertex);

    /**
     * Add an edge to the graph between vertices with indices u and v
     * @param u
     * @param v
     */
    public boolean addEdge(int u, int v);

    /**
     * Add an edge to the graph between vertex1 and vertex2
     * @param vertex1
     * @param vertex2
     */
    public boolean addEdge(V vertex1, V vertex2);

    /**
     * Obtain a list of vertices sorted by a depth first search
     * @param index
     * @return
     */
    public List<V> dfs(int index);

    /**
     * Obtain a list of vertices sorted by a depth first search
     * @param vertex
     * @return
     */
    public List<V> dfs(V vertex);

    /**
     * Obtain a list of vertices sorted by a breadth first search
     * @param index
     * @return
     */
    public List<V> bfs(int index);

    /**
     * Obtain a list of vertices sorted by a breadth first search
     * @param vertex
     * @return
     */
    public List<V> bfs(V vertex);

}
