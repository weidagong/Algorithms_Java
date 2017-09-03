package Chapter1;

public class weightUF {
	private int[] id;
	private int[] size;
	
	public weightUF(int N) {
		id = new int[N];
		size = new int[N];
		for (int i = 0; i<N; i++) {
			id[i] = i;
			size[i] = 1;
		}	
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
		int j = root(q);
		if (size[p] > size[q]) {
			id[j] = i;
			size[i] += size[j];
		}else {
			id[i] = j;
			size[j] += size[i];
		}
		
	}
}
