import java.util.Iterator;

/**
 * LinkedList object is a GENERIC linked list of Node<T>
 * @author benjaminlinder
 *
 */
public class LinkedList<T> implements Iterable<T>
{
	
	private Node<T> head;
	
	private int length;
	
	/**
	 * Constructor that sets up an empty list
	 */
	public LinkedList()
	{
		this.head = null;
		this.length = 0;
		
	}

	/**
	 * Adds a country to the END of list
	 * @param country
	 */
	public void add(T addition) {
		
		Node<T> toAdd = new Node<T> (addition);
		
		Node<T> walker = head;
		
		// test for the special case that this is the very first node so we don't crash dereferencing walker
		if (walker == null)
		{	
			// set the first country to be toAdd
			this.head = toAdd;
			
			// the list length is now 1
			this.length = 1;
		}
		else
		{
		
			//traverse to find end of list
		while ( walker.getNext() != null)
		{
			walker = walker.getNext();
		}
		
		// now walker is at our last node
		
		// set this CountryNode as the next node to our walker
			walker.setNext(toAdd);
		
		// increment the length for the list
			this.length++;
		}
	}

	/**
	 * Returns the index of a given Country object
	 * @param index
	 * @return Country
	 */
	public T getIndex(int index) {
		
		// test for the index being greater than the length, then return null
		if (index > this.length ) return null;
		
		
		// traverse the list index times and then return walker
		
		Node<T> walker = head;
		
		// test for the special case that this is the very first node so we don't crash dereferencing walker
		if (walker == null)
		{
			return null;
		}
		else
		{
		
		//traverse index steps
			
			for (int i=0; i < index; i++)
		{
			walker = walker.getNext();
		}
		
		// now walker is at our index node
			
			return walker.getData();
			
		}
		
		
	}

	/**
	 * Returns the size of the CountryList
	 * @return int length
	 */
	public int size() {
		
		// we already know it, so return it
		return length;
		
	}

	/**
	 * Searches for an object in the list
	 * 
	 * @param dataToFind
	 * @return
	 */
	
	public T contains(T dataToFind)
	{
		
		Node<T> walker = head;
		
		
		
		// test for the special case that this is the very first node so we don't crash dereferencing walker
		
		if (walker == null)
		{
			
			// the list is empty so we return null
			return null;
		}
		else
		{
		
			//traverse to find end of list
			while ( walker != null)
		{
				
		    if (walker.getData().equals(dataToFind))
		    		{
		    		return walker.getData();
		    		}
			walker = walker.getNext();
		
		}
			return null;
		}
	}
	
	/**
	 * Returns a String representation USING FOR-EACH loop and AN ITERATOR (PROJECT 6)
	 */
	public String toString()
	{
		
		// Changed to use a for-each loop and an iterator
		
		String output="";
		
		for (Iterator<T> iterator = iterator(); iterator.hasNext(); )
	
		{
			output = output + iterator.next().toString();
		}
	
		return output;
	
	}	
	
	/**
	 * Inserts a CountryNode of the given Country at the given index
	 * @param country
	 * @param index
	 * @return
	 */
	public boolean insertAtIndex(T data, int index)
	{

		// test for the index being greater than the length, then make the index equal to length
		// so that the new node goes at the end of the current list
		
				if (index > this.length ) 
					index = this.length;
				
				
				Node<T> walker = head;
				Node<T> insertedCountry = new Node<T>(data);
				Node<T> previousCountry = null;
				
		// Special case if index is 0 and the list is empty
				
				if (index == 0 && walker == null)
				{
					this.head = insertedCountry;
					this.length++;
					return true;
				}
				
		// Special case if we are inserting at index 0
				
				if (index == 0)
				{
					previousCountry = head;
					this.head = insertedCountry;
					insertedCountry.setNext(previousCountry);
					this.length++;
					
					return true;
					
				}
				
				
				// traverse the list index times and then insert
			
		
				//traverse index steps
					
					for (int i=0; i < index; i++)
				{
						previousCountry = walker;
						walker = walker.getNext();
				}
				
				// now walker is at the node currently living in our requested index 
					
					// connect the previous country node to our new node
					previousCountry.setNext(insertedCountry);
					
					// connect our new node to walker (the previous node at this location)
					insertedCountry.setNext(walker);
					
					this.length++;
					
					return true;				
		
	}
	
	 public Iterator<T> iterator() 
	 {
	        return new ListIterator();
	 }

	 public class ListIterator implements Iterator<T>
	 {
		 
		 private Node<T> current = head;
		 
		 
		 public boolean hasNext()
		 {
			 if (current != null)
				 return true;
			 else
				 return false;
		 }
		 
		 public T next()
		 {
			 T toReturn = current.getData();
			 
			 current = current.getNext();
			 
			 return toReturn;
			 
		 }

		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
		
		
	 }
	
}

