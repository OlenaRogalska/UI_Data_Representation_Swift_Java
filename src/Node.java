/**
 * This class is a GENERIC node in a GENERIC linked list
 * 
 * @author benjaminlinder
 *
 */
public class Node<T>
{

	private T data;
	private Node<T> next;
	
	/**
	 * Constructor that takes in only a country,and sets the next node to null
	 * @param country - Country object
	 */
	public Node(T data)
	{
		this.data = data;
		this.next = null;
		
	}
	/**
	 *  Constructor that builds a node and a pointer to the next node
	 * @param country Country object
	 * @param next Country object that's next
	 */
	public Node(T data, Node<T> next)
	{
		this.data = data;
		this.next = next;
	}
	
	/**
	 * Returns the Country object in the node
	 * @return Country
	 */
	public T getData() {
		
		return data;
		
	}

	/**
	 * Returns the next country object in the node
	 * @return Country
	 */
	public Node<T> getNext() {
		return next;
	}

	/**
	 * Mutator to set the next Country
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}

	/**
	 * To String method returns a string representation of the object by calling its toString method
	 */
	
	public String toString()
	{
		return data.toString();
	}
	
}
