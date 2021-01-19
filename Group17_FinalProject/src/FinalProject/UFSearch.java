package FinalProject;

//4.1.8(page 558)
/**A UF Search method base on textbook algorithm. Not really used because of the lack of data set.
 * 
 * @author Croup17
 * @see UFsearch
 * @see FinalProject
 * @see UFsearch
 * @see FinalProject.UFsearch
 * @see #UFSearch(Graph, int)
 * @see #marked(int)
 * @see #count()
 */
public class UFSearch {
    private UF uf;
    private int source;

    /**
     * Initializes an UFsearch
     * @param g is the input Graph
     * @param t is the number of the source.
     */
    public UFSearch(Graph g, int s) {
        UF uf = new UF(g.getVertexCount());
        for (int i = 0; i < g.getVertexCount(); i++)
            for (int j : g.adj(i))
                uf.union(i, j);
        source = s;
    }

    /**
     * mark a graph.
     * @param v is an element in Graph
     * @return {@code true }if source and v is connected.
     */
    public boolean marked(int v) {
        return uf.connected(source, v);
    }

    /**
     * count a graph.
     * @return the number of components of graph in this ufsearch.
     */
    public int count() {
        return uf.count();
    }
}