import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	
	private Point[] points;
	private int no;
	private LineSegment[] allLine;
	
	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		this.points = points;
		allLine = new LineSegment[points.length];
		no = 0;
	}
	
	public int numberOfSegments() {
		// the number of line segments
		return no;
	}
	
	public LineSegment[] segments() {
		// the line segments
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				for (int k = j + 1; k < points.length; k++) {
					for (int l = k + 1; l < points.length; l++) {
						if (checkCollinear(points[i], points[j], points[k], points[l])) {
							Point[] check = Arrays.copyOfRange(points,i,i+4);
							LineSegment out = longLine(check);
							allLine[no] = out;
							no++;
						}
					}
				}
			}
		}
		return allLine;
	}
	
	private boolean checkCollinear(Point a, Point b, Point c, Point d) {
		if (a.slopeTo(b) == a.slopeTo(c) &&
			a.slopeTo(b) == a.slopeTo(d)) {
			return true;
		}
		return false;
	}
	
	private LineSegment longLine(Point[] inputPoints) {
		Point min = inputPoints[0];
		Point max = inputPoints[0];
		for (int i = 1; i < inputPoints.length; i++) {
			if(inputPoints[i].compareTo(min) < 0) {
				min = inputPoints[i];
			}
			if(inputPoints[i].compareTo(max) > 0) {
				max = inputPoints[i];
			}
		}
		LineSegment out = new LineSegment(min, max);
		return out;
	}
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    
	    StdDraw.show();
	}
	
}