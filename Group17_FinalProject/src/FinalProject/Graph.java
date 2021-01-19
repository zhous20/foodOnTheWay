package FinalProject;
/**
 * Graph is to make a graph form input file, base on textbook algorithm. Not really used because of the lack of data set.
 * @author Group17
 * @see Graph
 * @see FinalProject
 * @see Graph
 * @see FinalProject.Graph
 * @see #adj(int)
 * @see #Graph(int)
 * @see #Graph(In)
 * @see #addEdge(int, int)
 * @see #toString()
 * @see #getVertexCount()
 * @see #getEdgeCount()
 */
public class Graph {

    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    /**
     * Initializes an empty graph with V vertices and 0 edges.
     * @param  V number of vertices
     */
    @SuppressWarnings("unchecked")
	public Graph(int V) {
    	this.E = 0;
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    /**  
     * Initializes a graph from the specified input stream.
     * The format is just the same as textbook.
     * @param  in the input stream
     */
    @SuppressWarnings("unchecked")
	public Graph(In in) {
            this.V = in.readInt();
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            for (int i = 0; i < E; i++) {
                int w = in.readInt();
                int v = in.readInt();
                addEdge(v, w); 
            }
        }
    /**
     * Adds the undirected edge v-w to this graph.
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     */
    public void addEdge(int w, int v) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }
    
    /**
     * Returns a string representation of this graph.
     * @return the number of vertices {@code V},  the number of edges, followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices and " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }
    /**
     * Returns the number of Vertexes in this  Graph.
     *
     * @return the number of Vertexes in this  Graph
     */
    public int getVertexCount() { return V; }
    /**
     * Returns the number of edges in this  digraph.
     *
     * @return the number of edges in this  digraph
     */
    public int getEdgeCount() { return E; }
}