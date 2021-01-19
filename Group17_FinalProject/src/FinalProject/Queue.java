package FinalProject;

/*Chapter 1.3 FIFO queue*/
/**Base on textbook algorithm
 * @author Group17
 * @see Queue
 * @see FinalProject
 * @see Queue
 * @see FinalProject.Queue
 * @see #isEmpty()
 * @see #size()
 * @see #enqueue(Object)
 * @see #dequeue()
 */
public class Queue<Item> {
	private Node first;
	private Node last;
	private int n;
	
	private class Node{
		Item item;
		Node next;
	}
	/**
     * Check if the queue is empty
     * @return {@code true }if first is empty
     */
	public boolean isEmpty() {
		return first == null;
	}
	/**
     * Get the size of the queue
     * @return the number
     */
	public int size() {
		return n;
	}
	/**
     * Put the item in the queue
     * @param item is the input item
     */
	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next=last;
		n++;
	}
	/**
     * Get the item that pop the first one.
     * @return the item
     */
	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		n--;
		if(isEmpty())
			last=null;
		return item;
	}
}
