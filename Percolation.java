import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int width; //width = height
	private boolean[] openIndex;
	private WeightedQuickUnionUF wUF;
	private int top;
	private int bottom;
	
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if (n < 1) {
			throw new IllegalArgumentException("Index out of bound ~~~~~");
		}
		width = n;
		top = 0;
		bottom = width*width + 1;
		openIndex = new boolean[bottom + 1];
		openIndex[top] = true;
		openIndex[bottom] = true;
		wUF = new WeightedQuickUnionUF(bottom + 1);
	}
	   
	public void open(int row, int col) {
		// open site (row, col) if it is not open already
		int index = getIndex(row, col);
		openIndex[index] = true;
		
		//if first row, union virtual top cell; not first row, union if cell above is open
		if (row == 1) {
			wUF.union(index, top);
		}else {
			if (openIndex[index - width]) {
				wUF.union(index, index - width);
			}
		}
		
		//check left or right, union if open
		//left
		if (col > 1) {
			if (openIndex[index - 1]) {
				wUF.union(index, index - 1);
			}
		}
		//right
		if (col < width) {
			if (openIndex[index + 1]) {
				wUF.union(index, index + 1);
			}
		}
		//check bottom
		if (row == width) {
			wUF.union(index, bottom);
		}else {
			if (openIndex[index + width]) {
				wUF.union(index, index + width);
			}
		}
	}
	public boolean isOpen(int row, int col) {
		
		int index = getIndex(row, col);
		return openIndex[index];
	}
	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		int index = getIndex(row, col);
		return wUF.connected(0, index);
	}
	public int numberOfOpenSites() {
	   // number of open sites
		int count = 0;
		for (int i = 1; i < bottom; i++) {
			if (openIndex[i] == true) {
				count ++;
			}
		}
		return count;
	}
	
	public boolean percolates() {
	   // does the system percolate?
		return wUF.connected(0, bottom);
	}

	public static void main(String[] args) {
	   Percolation testPerc = new Percolation(1);
	   System.out.println("Is the system percolated? :" + testPerc.percolates());
	   testPerc.open(1, 1);
	   System.out.println("How about it now? :" + testPerc.percolates());
	}
	
	private int getIndex(int i, int j) {
		checkIndex(i, j);
		int index = (i - 1) * width + j;
		return index;
	}
	
	private void checkIndex(int i, int j) {
		if(i <= 0 || j <= 0 || i > width || j > width) {
			throw new IllegalArgumentException("Index out of bound ~~~~~");
		}
	}
}
