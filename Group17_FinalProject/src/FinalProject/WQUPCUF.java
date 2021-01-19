package FinalProject;

/** Weighted Quick Union Path Compression UF, not really used because of lack of data.
 * @author Group17
 * @see WQUPCUF 
 * @see FinalProject
 * @see WQUPCUF 
 * @see FinalProject.WQUPCUF 
 * @see #WQUPCUF(int)
 * @see #find(int)
 * @see #count
 * @see #connected(int, int)
 * @see #union(int, int)
 * @see #validate(int)
 */
public class WQUPCUF {
    private int[] parent;  // parent[i] = parent of i
    private int[] size;    // size[i] = number of sites in tree rooted at i
    private int count;     // number of components in the graph

    /**
     * Initializes an empty union¨Cfind data structure with {@code n} sites
     *
     * @param  n the number of sites
     */
    public WQUPCUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Returns the number of components.
     * @return the number of components 
     */
    public int count() {
        return count;
    }
  

    /**
     * component identifier for the component containing site .
     * @param  p the site number
     * @return the component identifier for the component containing p site 
     */
    public int find(int p) {
        validate(p);
        int root = p;
        while (root != parent[root])
            root = parent[root];
        while (p != root) {
            int np = parent[p];
            parent[p] = root;
            p = np;
        }
        return root;
    }

   /**
     * Returns true if the the two sites are in the same component.
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return {@code true} if the two sites {@code p} and {@code q} are in the same component, otherwise false;
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is out of bounds ");  
        }
    }  

    /**
     * Merges the component containing site p with the the one containing site {@code q}
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     */
    public void union(int p, int q) {
        int Rp = find(p);
        int Rq = find(q);
        if (Rp == Rq) return;

        // make smaller root point to larger one
        if (size[Rp] < size[Rq]) {
            parent[Rp] = Rq;
            size[Rq] += size[Rp];
        }
        else {
            parent[Rq] = Rp;
            size[Rp] += size[Rq];
        }
        count--;
    }

    /**
     * Reads in a sequence of pairs of integers (between 0 and n-1) from standard input, 
     * where each integer represents some site;
     * if the sites are in different components, merge the two components
     * and print the pair to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = StdIn.readInt();
        WQUPCUF uf = new WQUPCUF(n);
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