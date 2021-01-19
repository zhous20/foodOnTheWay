package FinalProject;

/**UF, base on textbook algorithm. Not really used because of the lack of data set.
 * @author Group17
 * @see UF
 * @see FinalProject
 * @see UF
 * @see FinalProject.UF
 * @see #UF(int)
 * @see #find(int)
 * @see #count
 * @see #connected(int, int)
 * @see #union(int, int)
 * @see #validate(int)
 */
public class UF {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;     // number of components

    /**
     * Initializes an empty union¨Cfind data structure 
     * @param  n the number of sites
     * @throws IllegalArgumentException if {@code n < 0}
     */
    public UF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Component identifier for the component containing site {@code p}.
     * @param  p the integer representing one site
     * @return the component identifier for the component containing site {@code p}
     * @throws IllegalArgumentException unless {@code 0 <= p < n}
     */
    public int find(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }

    /**
     * Returns the number of components.
     * @return the number 
     */
    public int count() {
        return count;
    }
  
    /**
     * Returns true if the the two sites are in the same component.
          * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component;
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
  
    /**
     * Merges the component containing site {@code p} with the 
     * the component containing site {@code q}.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IllegalArgumentException unless
     *         both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    public void union(int p, int q) {
        int P = find(p);
        int Q = find(q);
        if (P == Q) return;

        // make root of smaller rank point to root of larger rank
        if      (rank[P] < rank[Q]) parent[P] = Q;
        else if (rank[P] > rank[Q]) parent[Q] = P;
        else {
            parent[Q] = P;
            rank[P]++;
        }
        count--;
    }

    /**
     * Check if p is valid.
     * @param  p the integer representing one site
     * @throws IllegalArgumentException unless both {@code 0 <= p < n} and {@code 0 <= q < n}
     */
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }

    /**
     * Reads in a an UF format file from standard input, where each integer in the pair represents some site;
     * if the sites are in different components, merge the two components
     * and print the pair to standard output.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        UF uf = new UF(n);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }


}