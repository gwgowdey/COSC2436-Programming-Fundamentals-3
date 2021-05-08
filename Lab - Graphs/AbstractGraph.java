// Student: Griffin Gowdey.
// Instructor: Douglas Atkinson.
// Class Number: COSC2436-20000
// Class Name: Programming Fundamentals III.
// Semester: Spring 2021
// Lab: BFS and DFS.
// Part 1: Implement BFS (breadth first search).
// Part 2: Implement DFS (depth first search).

/*
Part 1:
In the file AbstractGraph.java, you should implement a breadth first search using the algorithm provided in the textbook (Section 11.5).

BFS(startV)   
   Push startV to frontierQueue
   Add  startV to discoveredSet

   while ( frontierQueue is not empty )
      currentV = Pop frontierQueue
      "Visit" currentV
      for each vertex adjV adjacent to currentV
         if ( adjV is not in discoveredSet )
            Push adjV to frontierQueue
            Add  adjV to discoveredSet
            
            
- You may use whatever data structure you would like for the frontierQueue and the discoveredSet. This includes the data structures included with the Java API
- Your BFS method should return a list of vertices in the order that they were visited. You will need to modify the algorithm some to do this.
*/

/*
Part 2:
In the file AbstractGraph.java, you should implement a depth first search using the algorithm provided in the textbook (Section 11.6).

DFS(startV) 
{
   Push startV to stack

   while ( stack is not empty ) 
   {
      currentV = Pop stack
      if ( currentV is not in visitedSet ) 
      {
         "Visit" currentV
         Add currentV to visitedSet
         for each vertex adjV adjacent to currentV
            Push adjV to stack
      }
   }
}

- You may use whatever data structure you would like for the stack and the visitedSet. This includes the data structures included with the Java API.
- Your DFS method should return a list of vertices in the order that they were visited. You will need to modify the algorithm some to do this.
*/

/*
For testing purposes, GraphDriver.java will read the files cities.txt and edges.txt. GraphDriver.java will then create an 
unweighted graph from the data read in. It will then call your BFS method and your DFS method. 
The following is the expected output. Note: Output has been truncated for brevity.

BFS traversal order when starting with Fort Worth
Fort Worth, TX
Richfield, UT
Eastport, ME
Springfield, IL
Pittsburgh, PA
Carlsbad, NM
Portland, OR
Shreveport, LA
Amarillo, TX
Tulsa, OK
...

DFS traversal order when starting with Fort Worth
Fort Worth, TX
Eastport, ME
Portland, OR
Boston, MA
Raleigh, NC
Charleston, SC
Bangor, ME
Chicago, IL
Carlsbad, NM
Tampa, FL
...
*/

import java.util.*;

// Add any imports that you need for your BFS and DFS implementation.

public abstract class AbstractGraph<V> implements IGraph<V> 
{
    protected List<V> vertices;             // Store vertices.
    protected List<List<Edge>> neighbors;   // Adjacency list.

    /**
     *  Default constructor
     *  Intantiate vertices and neighbors
     */
    protected AbstractGraph() 
    {
        vertices = new ArrayList<>();
        neighbors = new ArrayList<>();
    }

    /**
     * Contructor to create graph from array of vertices and 2d array of integers
     * @param vertices - an array of vertices
     * @param edges - a 2d array of integers
     */
    protected AbstractGraph(V[] vertices, int[][] edges) 
    {
        this();
        for (int i = 0; i < vertices.length; i++) 
        {
            addVertex(vertices[i]);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    /**
     * Constructor to create graph from a list of vertices and a list of edges
     * @param vertices
     * @param edges
     */
    protected AbstractGraph(List<V> vertices, List<Edge> edges) 
    {
        this();
        for (int i = 0; i < vertices.size(); i++) 
        {
            addVertex(vertices.get(i));
        }
        createAdjacencyLists(edges, vertices.size());
    }

    /**
     * Constructor to create graph from a list of edges and the number of vertices
     * vertices will only be identified by index
     * @param edges
     * @param numberOfVertices
     */
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) 
    {
        this();
        for (int i = 0; i < numberOfVertices; ++i) 
        {
            addVertex((V) (Integer.valueOf(i))); // Vertices is {0, 1, 2, ...}.
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Constructor to create graph from a 2d array of integers to represent edges and the number of vertices
     * vertices will only be identified by index
     * @param edges
     * @param numberOfVertices
     */
    protected AbstractGraph(int[][] edges, int numberOfVertices) 
    {
        this();
        for (int i = 0; i < numberOfVertices; ++i) 
        {
            addVertex((V) (Integer.valueOf(i))); // Vertices is {0, 1, 2, ...}.
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    /**
     * Initialize the neighbors list from a 2d array of integers
     * @param edges
     * @param numberOfVertices
     */
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) 
    {
        for (int i = 0; i < edges.length; ++i) 
        {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    /**
     * Initialize the neighbors list from a list of edges
     * @param edges
     * @param numberOfVertices
     */
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) 
    {
        for (Edge edge : edges) 
        {
            addEdge(edge.u, edge.v);
        }
    }

    /**
     * Simple edge class - non-weighted
     * Edge is identified by two integers that are the indices of the vertices the edge connects
     * This class can be subclassed to make a weighted edge
     */
    public static class Edge 
    {
        int u;
        int v;

        public Edge(int u, int v) 
        {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) 
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return u == edge.u && v == edge.v;
        }

        @Override
        public int hashCode() 
        {
            return Objects.hash(u, v);
        }
    }

    @Override
    public int getSize() 
    {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() 
    {
        return vertices;
    }

    @Override
    public V getVertex(int index) 
    {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V vertex) 
    {
        return vertices.indexOf(vertex);
    }

    @Override
    public List<Integer> getNeighbors(int index) 
    {
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index)) 
        {
            result.add(e.v);
        }
        return result;
    }

    @Override
    public List<Integer> getNeighbors(V vertex) 
    {
        return getNeighbors(vertices.indexOf(vertex));
    }

    @Override
    public int getDegree(int index) 
    {
        return neighbors.get(index).size();
    }

    @Override
    public int getDegree(V vertex) 
    {
        return getDegree(vertices.indexOf(vertex));
    }

    @Override
    public void printEdges() 
    {
        for (int u = 0; u < neighbors.size(); u++) 
        {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Edge e : neighbors.get(u)) 
            {
                System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() 
    {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public boolean addVertex(V vertex) 
    {
        if (!vertices.contains(vertex)) 
        {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
        else 
        {
            return false;
        }
    }

    protected boolean addEdge(Edge e) 
    {
        if (e.u < 0 || e.u > getSize() - 1) 
        {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        if (e.v < 0 || e.v > getSize() - 1) 
        {
            throw new IllegalArgumentException("No such index: " + e.v);
        }
        if (!neighbors.get(e.u).contains(e)) 
        {
            neighbors.get(e.u).add(e);
            return true;
        }
        else 
        {
            return false;
        }
    }

    @Override
    public boolean addEdge(int u, int v) 
    {
        return addEdge(new Edge(u, v));
    }

    @Override
    public boolean addEdge(V vertex1, V vertex2) 
    {
        return addEdge(new Edge(vertices.indexOf(vertex1), vertices.indexOf(vertex2)));
    }

    @Override
    public List<V> dfs(int index) 
    {
        List<V> traverseOrder = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visitedSet = new HashSet<>();
        
        stack.push(index);
        
        // Loop until are vertices are visited.
        while (!stack.isEmpty())
        {
           Integer currentV = stack.pop();
           if (!visitedSet.contains(currentV))
           {
              traverseOrder.add(vertices.get(currentV));
              visitedSet.add(currentV);
              for (Integer neighbor : getNeighbors(currentV))
              {
                 stack.push(neighbor);
              }  
           }     
        }     
        
        return traverseOrder;
    }

    @Override
    public List<V> dfs(V vertex) 
    {
        return dfs(vertices.indexOf(vertex));
    }

    @Override
    public List<V> bfs(int index) 
    {
       // BFS for a tree is called a level-order traversal.
        Queue<Integer> frontierQueue = new LinkedList<>(); // Queue for vertices to be processed.
        Set<Integer> discoveredSet = new HashSet<>(); // Already in queue set.
        List<V> traverseOrder = new ArrayList<>();
        
        frontierQueue.offer(index);
        discoveredSet.add(index);
        
        // Loop until all vertices have been visited.
        while (!frontierQueue.isEmpty())
        {
           Integer currentV = frontierQueue.poll();
           traverseOrder.add(vertices.get(currentV)); // The point of the visit.
           for (Integer neighbor : getNeighbors(currentV))
           {
              if (!discoveredSet.contains(neighbor))
              {
                 frontierQueue.offer(neighbor);
                 discoveredSet.add(neighbor);
              }
           }           
        }
        
        return traverseOrder;
      }

    @Override
    public List<V> bfs(V vertex) 
    {
        return bfs(vertices.indexOf(vertex));
    }
}