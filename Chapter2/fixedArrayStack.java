package Chapter2;

public class fixedArrayStack {
	
	private int index;
	private String[] items;
			
	public fixedArrayStack(int capacity) {
		// TODO Auto-generated constructor stub
		items = new String[capacity];
		index = 0;
	}
	
	public boolean isEmpty() {
		return index == 0;
	}
	
	public void push(String item) {
		items[index++] = item;
	}
	
	public String pop() {
		String item = items[--index];
		items[index] = null;
		return item;
	}

}
