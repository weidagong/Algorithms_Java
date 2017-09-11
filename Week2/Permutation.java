import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
	
	public static void main(String[] args) {
		
		int no = Integer.parseInt(args[0]);
		RandomizedQueue<String> out = new RandomizedQueue<String>();
		
		while(!StdIn.isEmpty()) {
			String input = StdIn.readString();
			out.enqueue(input);
		}
		for (int i = 0; i < no; i++) {
			String result = out.dequeue();
			StdOut.println(result);
		}
	}

}
