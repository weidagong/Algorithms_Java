package Chapter2;

public class sizedArrayStack {
	
	private int index;
	private String[] items;

	public sizedArrayStack(int capacity) {
		// TODO Auto-generated constructor stub
		items = new String[capacity];
		index = 0;
	}
	
	public boolean isEmpty() {
		return index == 0;
	}
	
	public void push(String item) {
		if (items.length == index) {
			resize(2 * items.length);
		}
		items[index++] = item;
	}
	
	public String pop() {
		String item = items[--index];
		items[index] = null;
		if (index > 0 && index < items.length/4.0) {
			resize(items.length/2);
		}
		return item;
	}
	
	private void resize(int capacity) {
		String[] copy = new String[capacity];
		
		for (int i = 0; i < index; i++) {
			copy[i] = items[i];
		}
		
		items = copy;
	}

}
