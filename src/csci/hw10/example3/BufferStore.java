package csci.hw10.example3;

public class BufferStore {
	private char[] buffer;
	public int capacity = 0;
	public int size;
	public BufferStore(int capacity) {
		buffer = new char[capacity];
		size = 0;
		this.capacity = capacity;
 	}
	
	
	public void add(char b){
		if(size < this.capacity)
			buffer[size++] = b;
	}
	public void add(char[] arr){
		for (int i = 0; i< arr.length; i++){
			add(arr[i]);
		}
	}
	public char[] get(int start, int end){
		char[] result = new char[end-start];
		char[] copy = new char[capacity];
		System.arraycopy(buffer, start, result, 0, end-start);
		System.arraycopy(buffer, end-start, copy, 0, size - (end - start));
		size = size - (end - start);
		buffer = copy;
		return result;
	}
	
}
