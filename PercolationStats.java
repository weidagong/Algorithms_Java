import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats {
	
	private int width;
	private int trials;
	private double[] allEstimate;
   
	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if(n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Input must be a positive integer greater than 0");
		}
		width = n;
		this.trials = trials;
		
		allEstimate = new double[trials];
		
		for (int i = 0; i < trials; i++) {
			allEstimate[i] = estimate(width);
		}
	}
	
	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(allEstimate);
	}
   
	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(allEstimate);
	}
   
	public double confidenceLo() {
		// low  endpoint of 95% confidence interval
		double temp = this.mean() - 1.96 * this.stddev() / Math.sqrt(trials);
		return temp;
	}
   
	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		double temp = this.mean() + 1.96 * this.stddev() / Math.sqrt(trials);
		return temp;
	}
	
	private double estimate(int width) {
		Percolation model = new Percolation(width);
		int count = 0;
		double totalCell = width * width * 1.0;		
		while(!model.percolates()) {
			while(true) {
				int row = StdRandom.uniform(1, width + 1);
				int col = StdRandom.uniform(1, width + 1);
				if(!model.isOpen(row, col)) {
					model.open(row, col);
					count ++;
					break;
				}
			}
		}
		double out = count/totalCell;
		return out;
	}

	public static void main(String[] args) {
		// test client (described below)
		int width = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		PercolationStats stats = new PercolationStats(width, trials);
		
		System.out.println("mean                    = " + stats.mean());
		System.out.println("stddev                  = " + stats.stddev());
		System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());
	}
}