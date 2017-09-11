
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private node first;
	private node last;
	private int no;
    
	private class node{
		Item item;
	    node nextNode;
	    node prevNode;
	}
	
	public Deque() {
		// construct an empty deque
		first = new node();
		last = new node();
		first.nextNode = last;
		last.prevNode = first;
		no = 0;
    }
	   
    public boolean isEmpty() {
    	// is the deque empty?
    	return no == 0;
    }
	   
    public int size() {
    	// return the number of items on the deque
    	return no;
    }
	   
    public void addFirst(Item item) {
    	// add the item to the front
    	validAdd(item);
    	if (first.item == null) {
    		first.item = item;
    	}else {
    		node newFirst = new node();
    		newFirst.item = item;
    		newFirst.nextNode = first;
    		first.prevNode = newFirst;
    		first = newFirst;
    	}
    	no++;
    }
	   

    public void addLast(Item item) {
    	// add the item to the end
    	validAdd(item);
    	
    	if (isEmpty()) {
    		addFirst(item);
    	}else {
    		if (last.item == null) {
        		last.item = item;
        	}else {
        		node newLast = new node();
        		newLast.item = item;
        		newLast.prevNode = last;
        		last.nextNode = newLast;
        		last = newLast;
        	}
    		no++;
    	}
    	
    }
    
    private void validAdd(Item item) {
    	if (item == null) {
    		throw new IllegalArgumentException("Adding null item");
    	}
    }
	   
    public Item removeFirst() {
    	// remove and return the item from the front
    	validRemove();
    	Item out = first.item;
    	if (first.nextNode.item == null) {
    		no--;
    		first.item = null;
    		return out;
    		
    	}else {
    		node newFirst = first.nextNode;
    		first = newFirst;
    		first.prevNode = null;
    		if (first.nextNode == null) {
    			last = new node();
    			first.nextNode = last;
    		}
    		no--;
        	return out;
    	}
    }
	   
	   public Item removeLast() {
		   // remove and return the item from the end
		   validRemove();
		   if (last.item == null) {
			   node newLast = last.prevNode;
			   last = newLast;
			   Item out = last.item;
			   last.item = null;
			   no--;
			   return out;
		   }else {
			   Item out = last.item;
			   node newLast = last.prevNode;
			   last = newLast;
			   no--;
			   return out;
		   }   
	   }
	   
	   private void validRemove() {
		   if (no == 0) {
			   throw new NoSuchElementException();
		   }
	   }
	   
	   public Iterator<Item> iterator(){
		   // return an iterator over items in order from front to end
		   return new LinkedListIterator();
	   }
	   
	   private class LinkedListIterator implements Iterator<Item>{
		   
		   private node current = first;
		   
		   public boolean hasNext() {
			   return (current.item != null);
		   }
		   
		   public void remove() {
			   throw new UnsupportedOperationException();
		   }
		   public Item next() {
			   if (!hasNext()) {
				   throw new NoSuchElementException();
			   }
			   Item out = current.item;
			   current = current.nextNode;
			   return out;
		   }
	   }
	   
	   public static void main(String[] args) {
		   // unit testing (optional)
		   Deque<Integer> test = new Deque<Integer>();
		   test.addFirst(3);
		   test.addFirst(4);
		   System.out.println(test.removeLast());
	       System.out.println(test.removeFirst());
		   test.addLast(9);
		   //test.addLast(1);
	       //test.removeFirst();
	       System.out.println(test.removeLast());
	       //System.out.println(test.removeLast());
	       /*
	       Iterator<Integer> testIte = test.iterator();
	       System.out.println(testIte.next());
	       System.out.println(testIte.next());
	       System.out.println(testIte.next());
	       System.out.println(testIte.next());
	       */
	   }
}