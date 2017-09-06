
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
    		node oldFirst = first;
        	first.item = item;
        	first.nextNode = oldFirst;
        	oldFirst.prevNode = first;
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
        		node oldLast = last;
        		last = new node();
        		last.item = item;
        		last.prevNode = oldLast;
        		oldLast.nextNode = last;
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
    		return out;
    		
    	}else {
    		first = first.nextNode;
    		no--;
        	return out;
    	}
    }
	   
	   public Item removeLast() {
		   // remove and return the item from the end
		   validRemove();
		   if (last.item == null) {
			   Item out = last.prevNode.item;
			   no--;
			   return out;
		   }else {
			   Item out = last.item;
			   last = last.prevNode;
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
		   
		   private int i = 0;
		   private node current = first;
		   
		   public boolean hasNext() {
			   return i < no;
		   }
		   
		   public void remove() {
			   throw new UnsupportedOperationException();
		   }
		   public Item next() {
			   if (!hasNext()) {
				   throw new NoSuchElementException();
			   }
			   return current.nextNode.item;
		   }
	   }
	   
	   public static void main(String[] args) {
		   // unit testing (optional)
		   Deque<String> test = new Deque<String>();
		   System.out.println("size:" + test.no);
		   test.addFirst("A");
		   String a = test.removeLast();
		   System.out.println("A:" + a);
		   System.out.println("size:" + test.no);
		   System.out.println("Empty:" + test.isEmpty());
		   test.addLast("B");
		   System.out.println("size:" + test.no);
	   }
}