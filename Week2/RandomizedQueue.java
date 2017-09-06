import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] allItems;
	private int no;
	
	public RandomizedQueue() {
		// construct an empty randomized queue
		allItems = (Item[]) new Object[1];
		no = 0;
	}
	
	public boolean isEmpty() {
		// is the queue empty?
		return no == 0;
	}
	
	public int size() {
		// return the number of items on the queue
		return no;
	}
	
	public void enqueue(Item item) {
		// add the item
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (no == allItems.length) {
			resize(2 * allItems.length);
		}
		allItems[no++] = item;
	}
	
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < no; i++) {
			copy[i] = allItems[i];
		}
		allItems = copy;
	}
	
	public Item dequeue() {
		// remove and return a random item
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int index = StdRandom.uniform(no);
		while(allItems[index] == null) {
			index = StdRandom.uniform(no);
		}
		Item out = allItems[index];
		allItems[index] = null;
		no--;
		return out;
	}
	
	public Item sample() {
		// return (but do not remove) a random item
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int index = StdRandom.uniform(no);
		return allItems[index];
	}
	
	private class RandomIterator implements Iterator<Item>{
		   
		   private int i;
		   private Item[] copy;
		   private int temp_no;
		   
		   public RandomIterator() {
			   i = 0;
			   copy = (Item[]) new Object[no];
			   for (int j = 0; j < no; j++) {
				   copy[j] = allItems[j];
			   }
			   temp_no = copy.length;
		   }
		   
		   public boolean hasNext() {
			   return i < temp_no;
		   }
		   
		   public void remove() {
			   throw new UnsupportedOperationException();
		   }
		   
		   public Item next() {
			   if (!hasNext()) {
				   throw new NoSuchElementException();
			   }
			   int index = StdRandom.uniform(temp_no);
			   while(copy[index] == null) {
					index = StdRandom.uniform(no);
				}
			   Item out = copy[index];
			   i++;
			   copy[index] = null;
			   return out;
		   }
	   }
	
	public Iterator<Item> iterator(){
		// return an independent iterator over items in random order
		return new RandomIterator();
	}
	
	public static void main(String[] args) {
		// unit testing (optional)
		RandomizedQueue<String> test = new RandomizedQueue<String>();
		test.enqueue("AA");
		System.out.println("size:" + test.no);
		System.out.println("Empty:" + test.isEmpty());
		test.enqueue("BB");
		test.enqueue("CC");
		test.enqueue("DD");
		System.out.println("size:" + test.no);
		System.out.println("Empty:" + test.isEmpty());
		Iterator<String> sb = test.iterator();
		System.out.println("Iterator:" + sb.next());
		System.out.println("Iterator:" + sb.next());
		System.out.println("Iterator:" + sb.next());
		System.out.println("Iterator:" + sb.next());
	}
}