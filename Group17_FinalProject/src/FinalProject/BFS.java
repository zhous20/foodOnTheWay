package FinalProject;
/**A Breadth-First Search method
 * 
 * @author Croup17
 * @see BFS
 * @see FinalProject
 * @see BFS
 * @see FinalProject.BFS
 * @see #bfs(Graph, int)
 * @see #BFS(Graph, int)
 * @see #hasPathTo(int)
 * @see #pathTo(int)
 */


public class BFS {
	
	 private boolean[] marked;
	    private int[] edgeTo;
	    private int s;
	    /**
	     * This is a constructor to generate the input.
	     * @param  g the graph
	     * @param  s the start point.
	     */
	    public BFS(Graph g, int s)
	    {
	    	this.s=s;
	    	marked = new boolean[g.getVertexCount()];
	        edgeTo = new int[g.getVertexCount()];
	        bfs(g, s);
	    }
	    /**
	     * check and use queque to store the input.
	     * @param  g the graph
	     * @param  s the start point.
	     */
	    private void bfs(Graph g, int s){
	        Queue<Integer> q = new Queue<Integer>();
	        q.enqueue(s);
	        marked[s] = true;
	        while(!q.isEmpty())
	        {
	        	int v = q.dequeue();
	            for(int w:g.adj(v))
	            {
	            	{
		                if(!marked[w])
		                {
		                    q.enqueue(w);
		                    marked[w] = true;
		                    edgeTo[w] = v;
		                }
		            }
	            }
	        }
	    }
	    /**
	     * check if there is a path to v vertex
	     * @param  v the v th vertex
	     */
	    public boolean hasPathTo(int v){return marked[v];}
	    
	    /**
	     * locate the vertex
	     * @param  v the v th vertex
	     */
	    public Stack<Integer> pathTo(int v){
	        if(!hasPathTo(v)) 
	        	return null; 
	        Stack<Integer> path = new Stack<Integer>();
	        for(int x = v; x!=s; x=edgeTo[x])
	            path.push(x);
	        path.push(s);
	        return path;
	    }
}
