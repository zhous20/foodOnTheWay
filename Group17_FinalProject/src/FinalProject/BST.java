package FinalProject;


import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**A Binary Search Tree Search method
 * 
 * @author Croup17
 * @see BST
 * @see FinalProject
 * @see BST
 * @see FinalProject.BST
 * @see #put(Comparable, Object)
 * @see #put(Node, Comparable, Object)
 * @see #get(Comparable)
 * @see #get(Node, Comparable)
 * @see #delete(Comparable)
 * @see #delete(Node, Comparable)
 * @see #contains(Comparable)
 * @see #isEmpty()
 * @see #size()
 * @see #size(Node)
 * @see #min()
 * @see #min(Node)
 * @see #max()
 * @see #max(Node)
 * @see #floor(Comparable)
 * @see #floor(Node, Comparable)
 * @see #ceiling(Comparable)
 * @see #ceiling(Node, Comparable)
 * @see #select(int)
 * @see #select(Node, int)
 * @see #deleteMax()
 * @see #deleteMax(Node)
 * @see #deleteMin()
 * @see #deleteMin(Node)
 * @see #size(Comparable, Comparable)
 * @see #isBinaryTree(Node)
 * @see #height()
 * @see #height(Node)
 * @see #isOrdered(Node, Comparable, Comparable)
 * @see #keys()
 * @see #keys(Comparable, Comparable)
 * @see #keys(Node, List, Comparable, Comparable)
 * @see #postOrder(Node, PrintWriter)
 * @see #postOrderTraversal(Comparable)
 * @see #preOrder(Node, PrintWriter)
 * @see #preOrderTraversal(Comparable)
 * @see #inOrderTraversal(Comparable)
 * @see #hasNoDuplicates(Node)
 * @see #printLevel(Comparable)
 * @see #isBST()
 */

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;


    /**
     * put key ,value in a BST
     * @param Key is the special symbol of the value
     * @param value is the special input
     */
    public void put(Key key, Value value) {
        if (value == null) delete(key);
        else root = put(root, key, value);
    }

    /**
     * put key ,value in a correct node of BST
     * @param node is the special place
     * @param Key is the special symbol of the value
     * @param value is the special  value
     * @return the related node
     */
    private Node put(Node node, Key key, Value value) {
        if (node == null) return new Node(key, value, 1);
        int comp = key.compareTo(node.key);
        if (comp < 0) node.left = put(node.left, key, value);
        else if (comp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.nodesCount = size(node.left) + size(node.right) + 1;
        return node;
    }
    /**
     * get value of key in a BST
     * @param Key is the special symbol of the value
     * @return the value of {@code Key}
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * get value of key in a node of BST
     * @param Key is the special symbol of the value
     * @return the value of {@code Key}
     */
    private Value get(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp < 0) return get(node.left, key);
        else if (comp > 0) return get(node.right, key);
        else return node.value;
    }
    /**
     * delete a value in of BST
     * @param Key is the special symbol of the value
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * get find the correct place of key and delete that value.
     * @param node is the correct node of the key
     * @param Key is the special symbol of the value
     * @return the value of {@code Key}
     */
    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp < 0) node.left = delete(node.left, key);
        else if (comp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }

        node.nodesCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Check if Key is in BST
     * @param Key is the special symbol of the value
     * @return the value of {@code true} if it is in BST
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }
    /**
     * Check if it is empty
     * @return the value of {@code true} if it is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Check the size of BST
     * @return the Size of BST
     */
    public int size() {
        return size(root);
    }
    /**
     * Check the size of the node in BST
     * @param node is the node
     * @return the Size of node
     */
    private int size(Node node) {
        if (node == null) return 0;
        return node.nodesCount;
    }
    /**
     * return the min value in the BST
     * @return the min value
     */
    public Key min() {
        if (isEmpty()) return null;
        return min(root).key;
    }
    /**
     * find the min value in a node
     * @return the min value
     */
    private Node min(Node node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    /**
     * return the max value in the BST
     * @return the max value
     */
    public Key max() {
        if (isEmpty()) return null;
        return max(root).key;
    }

    /**
     * return the max value in the BST
     * @return the max value
     */
    private Node max(Node node) {
        if (node.right == null) return node;
        return max(node.right);
    }

    /**
     * return the Returns the highest key in the BST smaller than or equal to key.
     * @param the key
     * @return the value
     */
    public Key floor(Key key) {
        Node node = floor(root, key);
        if (node == null) return null;
        return node.key;
    }
    /**
     * return the Returns the highest key in the node smaller than or equal to key.
     * @param the node
     * @param the key
     * @return the floor
     */
    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp == 0) return node;
        if (comp < 0) return floor(node.left, key);
        Node temp = floor(node.right, key);
        if (temp != null) return temp;
        return node;
    }
    /**
     * return the Returns the smallest key in the BST greater than or equal to key.
     * @param the key
     * @return the value
     */
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node != null) return node.key;
        return null;
    }
    /**
     * return the Returns the smallest key in the node greater than or equal to key.
     * @param the node
     * @param the key
     * @return the ceiling
     */
    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int comp = key.compareTo(node.key);
        if (comp == 0) return node;
        if (comp < 0) {
            Node temp = ceiling(node.left, key);
            if (temp != null) return temp;
            return node;
        }
        return ceiling(node.right, key);
    }

    /**
     * return the Rank in the BST, that is the number of keys smaller than Key.
     * @param the key
     * @return the Rank
     */
    public int rank(Key key) {
        return rank(root, key);
    }
    /**
     * return the Rank in the node, that is the number of keys smaller than Key.
     * @param the node
     * @param the key
     * @return the Rank
     */
    private int rank(Node node, Key key) {
        if (node == null) return 0;
        int comp = key.compareTo(node.key);
        if (comp < 0) return rank(node.left, key);
        if (comp > 0) return 1 + size(node.left) + rank(node.right, key);
        return size(node.left);
    }
    /**
     * return a key in BST
     * @param the k is the order of key
     * @return the Rank
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) return null;
        Node node = select(root, k);
        return node.key;
    }
    /**
     * return a key in Node
     *  @param the node
     * @param the k is the order of key
     * @return the Rank
     */
    private Node select(Node node, int k) {
        if (node == null) return null;
        int temp = size(node.left);
        if (temp < k) return select(node.right, k - temp - 1);
        if (temp > k) return select(node.left, k);
        return node;
    }
    /**
     * delete min value in BST
     */
    public void deleteMin() {
        if (isEmpty()) return;
        root = deleteMin(root);
    }
    /**
     * delete min value in BST
     * @param the node
     */
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.nodesCount = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * delete max value in BST
     */
    public void deleteMax() {
        if (isEmpty()) return;
        root = deleteMax(root);
    }
    /**
     * delete max value in BST
     * @param the node
     */

    private Node deleteMax(Node node) {
        if (node.right == null) return node.left;
        node.right = deleteMax(node.right);
        node.nodesCount = size(node.left) + size(node.right) + 1;
        return node;
    }
    /**
     * return the size between {@code lo}and {@code hi}
     * @param the lo
     * @param the hi
     */
    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else return rank(hi) - rank(lo);
    }
    
    /**
     * return the new key list
     * @param the lo
     * @param the hi
     * @return the list
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<Key>();
        keys(root, list, lo, hi);
        return list;
    }
    /**
     * return the new key list that is just the old one
     * @param the node
     * @param the hi
     * @param the list
     * @param the node is to spelict.
     */
    private void keys(Node node, List<Key> list, Key lo, Key hi) {
        if (node == null) return;
        int compLo = lo.compareTo(node.key);
        if (compLo < 0) keys(node.left, list, lo, hi);
        int compHi = hi.compareTo(node.key);
        if (compLo <= 0 && compHi >= 0) list.add(node.key);
        if (compHi > 0) keys(node.right, list, lo, hi);

    }
    /**
     * return the new key list that is just the old one
     * @return a Key value
     */
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * return the height of the root
     * @return a int value
     */
    public int height() {
        return height(root);
    }
    /**
     * return the height of the node
     * @return a int value
     */
    private int height(Node node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }
    /**
     * Check if it is BinaryTree
     * @param the node
     * @return {@code true} if isBinaryTree istrue
     */
    private boolean isBinaryTree(Node node) {
        if (node == null) return true;
        if (node.left != null && node.nodesCount <= node.left.nodesCount) return false;
        if (node.right != null && node.nodesCount <= node.right.nodesCount) return false;
        return isBinaryTree(node.left) && isBinaryTree(node.right);
    }
    /**
     * check node node if it is corrected order.
     * @param the node
     * @param the min key
     * @param the max key
     * @return {@code true} if isBinaryTree is Ordered
     */
    private boolean isOrdered(Node node, Key min, Key max) {
        if (node == null) return true;
        if (node.key.compareTo(min) < 0 || node.key.compareTo(max) > 0) return false;
        if (node.left != null && node.left.key.compareTo(node.key) >= 0) return false;
        if (node.right != null && node.right.key.compareTo(node.key) <= 0) return false;
        return isOrdered(node.left, min, max) && isOrdered(node.right, min, max);
    }
    /**
     * check node node whether there has no duplicates or not.
     * @param the node
     * @return {@code true} if there has no duplicates
     */
    private boolean hasNoDuplicates(Node node) {
        if (node == null) return true;
        if (node.left != null && node.left.key == node.key) return false;
        if (node.right != null && node.right.key == node.key) return false;
        return hasNoDuplicates(node.left) && hasNoDuplicates(node.right);
    }
    /**
     * check node node whether there has BSt.
     * @return {@code true} if there is BST
     */
    public boolean isBST() {
        if (!isBinaryTree(root)) return false;
        if (!isOrdered(root, min(), max())) return false;
        if (!hasNoDuplicates(root)) return false;
        return true;
    }
    /**
     * print the key
     * @param node
     */
    public void printLevel(Key key) //bread first traversal
    {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        output.println("printLevel for " + key);
        Node node = root;
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp == 0) break;
            else if (comp < 0) node = node.left;
            else node = node.right;
        }

        if (node == null) return;
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(node);
        while (!queue.isEmpty()) {
            node = queue.dequeue();
            output.print(node.key + " ");
            if (node.left != null) queue.enqueue(node.left);
            if (node.right != null) queue.enqueue(node.right);
        }
        output.println();
    }
    /**
     * check node node whether there is preOrderTraversa.
     * @param output the special key od the Key
     * @return {@code true} if there is preOrderTraversa
     */
    public void preOrderTraversal(Key key) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        output.println("preOrder traversal for " + key);
        Node node = root;
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp == 0) break;
            else if (comp < 0) node = node.left;
            else node = node.right;
        }

        preOrder(node, output);
        output.println();
    }
    /**
     * check node node whether there is preOrderTraversa.
     * @param Node
     * @param output
     */
    private void preOrder(Node node, PrintWriter output) {
        if (node == null) return;
        output.print(node.key + " ");
        preOrder(node.left, output);
        preOrder(node.right, output);
    }
    /**
     * check node node whether there is OrderTraversal.
     * @param key
     * @return {@code true} if there is OrderTraversal
     */
    public void inOrderTraversal(Key key) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        output.println("inOrder traversal for " + key);
        Node node = root;
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp == 0) break;
            else if (comp < 0) node = node.left;
            else node = node.right;
        }

        inOrder(node, output);
        output.println();
    }
    /**
     * check if inOrter, and output
     * @param Node
     * @param output
     */
    private void inOrder(Node node, PrintWriter output) {
        if (node == null) return;
        inOrder(node.left, output);
        output.print(node.key + " ");
        inOrder(node.right, output);
    }
    /**
     * Do postOrderTraversal ,print the output
     * @param Key
     * @param output
     */
    public void postOrderTraversal(Key key) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        output.println("postOrder traversal for " + key);
        Node node = root;
        while (node != null) {
            int comp = key.compareTo(node.key);
            if (comp == 0) break;
            else if (comp < 0) node = node.left;
            else node = node.right;
        }

        postOrder(node, output);
        output.println();
    }

    /**
     * Do postOrder,print the output
     * @param node
     * @param output
     */
    private void postOrder(Node node, PrintWriter output) {
        if (node == null) return;
        postOrder(node.left, output);
        postOrder(node.right, output);
        output.print(node.key + " ");
    }

    private class Node implements Comparable<Node> {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int nodesCount;

        public Node(Key key, Value value, int nodesCount) {
            this.key = key;
            this.value = value;
            this.nodesCount = nodesCount;
        }

        public int compareTo(Node other) {
            return this.key.compareTo(other.key);
        }
    }
}
