package csci.hw7;

public class TST<T> {
	int size = 0;
	TSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return size;
	}

	public TST() {
		root = null;
	}

	public void insert(String key, T value) {
		if(!contains(key)) size++;
		root = insert(root, key, value, 0);
	}

	private TSTNode<T> insert(TSTNode<T> n, String key, T value, int ptr) {
		char c = key.charAt(ptr);
		if (n == null) {
			n = new TSTNode<T>(c);
		}
		if (c < n.data) {
			n.left = insert(n.left, key, value, ptr);
		} else if (c > n.data) {
			n.right = insert(n.right, key, value, ptr);
		} else if (ptr < key.length() - 1) {
			n.middle = insert(n.middle, key, value, ptr + 1);
		} else {
			n.val = value;
		}
		return n;
	}
	
	public boolean contains(String key) {
        return get(key) != null;
    }
	
	public String getLongestPrefix(String query) {
		TSTNode<T> n = root;
		int i = 0;
		int length = i;
		if (query == null || query.length() == 0) {
			return null;
		}
		while (n != null && i < query.length()) {
			char c = query.charAt(i);
			if (c < n.data) {
				n = n.left;
			} else if (c > n.data) {
				n = n.right;
			} else {
				i++;
				if (n.val != null)
					length = i;
				n = n.middle;
			}
		}
		return query.substring(0, length);
	}

	public T get(String key) {
		if (key.length() < 1)
			throw new IllegalArgumentException("Incorrect " + "key length.. Key length is " + key.length());
		TSTNode<T> n = get(root, key, 0);
		if (n == null)
			return null;
		return n.val;
	}

	private TSTNode<T> get(TSTNode<T> n, String key, int ptr) {
		char c = key.charAt(ptr);
		if (n == null) {
			return null;
		}
		if (c < n.data) {
			return get(n.left, key, ptr);
		}
		if (c > n.data) {
			return get(n.right, key, ptr);
		}
		if (ptr < key.length() - 1) {
			return get(n.middle, key, ptr + 1);
		} else {
			return n;
		}
	}

	/**
	 * 
	 * @author Vishal Private Node class
	 *
	 */
	private static class TSTNode<T> {
		char data;
		TSTNode<T> left, right, middle;
		T val;

		public TSTNode(char data) {
			this.data = data;
			this.left = null;
			this.right = null;
			this.middle = null;
			this.val = null;
		}
	}
}
