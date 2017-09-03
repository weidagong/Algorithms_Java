package Chapter1;

public class quickUnion {
	private int[] id;
	
	public quickUnion(int N) {
		id = new int[N];
		for (int i = 0; i<N; i++)
			id[i] = i;
	}
	
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	public int root(int p) {
		while (id[p] != p) {
			p = id[p];
		}
		return p;
	}
	
	public void union(int p, int q) {
		int i = root(p);
		id[i] = root(q);
	}
}
