package FinalProject;

/**Edge, base on textbook algorithm. Not really used because of the lack of data set.
 * @author Group17
 * @see Edge
 * @see FinalProject
 * @see Edge
 * @see FinalProject.Edge
 * @see #Edge(int, int, double)
 * @see #weight
 * @see #either()
 * @see #other(int)
 * @see #toString()
 * @see #compareTo(Edge)
 */
public class Edge implements Comparable<Edge> { 

    private final int v;
    private final int w;
    private final double weight;

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of the given {@code weight}.
     * @param  v one vertex
     * @param  w the other vertex
     * @param  weight the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w} 
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a positive integer or 0");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a positive integer or 0");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns either end point of this edge.
     *
     * @return either end point of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the end points of this edge
     *         
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Compares two edges by weight.
     * @param  that the other edge
     * @return a number. The relation between output anf 0 show the relation between this one and the input one.
     */
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * A string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}
