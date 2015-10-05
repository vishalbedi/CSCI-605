/*
 * Implement a class using generics for each of the requirements:
 * the maximum elements stored is between 0 and 100. You have to use a 
 * data structure which capacity is fixed after creation. 
 * It is important that adding a item to the storage is 0(1). 
 * This class has to be named: StorageFixed.java. The compiler can not produce any 
 * warning during compilation. You can not use any compiler flags. You can not use any existent 
 * java classes for the storage functionally.

 * The maximum elements stored is unknown. You have to use a dynamic data structure 
 * which capacity is not fixed after creation. This class has to be named: StorageDynamic.java. 
 * The compiler can not produce any warning during compilation. 
 * You can not use any compiler flags. You can not use any existent java classes for the storage functionally.
 * You have to provide a test class for your implementation. The same test class has to be used for 
 * StorageFixed.java and StorageDynamic.java. It must be possible to 
 * compile your implementations with the test class StorageTest.java. without any warnings. 
 * You can not use any compiler flags.
 */

package csci.hw5.example1;

/**
 * Linked list with some basic operations, which can store 100 items.
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */

public class StorageFixed <E,V> implements Storage<E,V>{
	
	Class<E> ec;
	Class<V> vc;
	private final int capacity = 100;
	@SuppressWarnings( "unchecked" )
	private  E[] storageArrayMain = (E[])new Object[capacity];
	@SuppressWarnings( "unchecked" )
	private  V[] storageArrayBackUp = (V[])new Object[capacity];
	private int size = 0;
	
	public StorageFixed(){
		
	}
	
	private StorageFixed(E[] e, V[] v, int size) {
		storageArrayMain = e;
		storageArrayBackUp = v;
		this.size = size;
	}
	
	@Override
	public boolean add(E e) {
		if(size < capacity){
			storageArrayMain[size] = e;
			storageArrayBackUp[size] = null;
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean add(int index, E element) {
		if((size < capacity) && (index <= size)){
			for(int i = size; i >= index; i--) {
				storageArrayMain[i+1] = storageArrayMain[i];
				storageArrayBackUp[i+1] = storageArrayBackUp[i];
			}
			storageArrayMain[index] = element;
			storageArrayBackUp[index] = null;
			size++;
			return true;
		}
		return false;		
	}

	@Override
	public void addElement(E obj) {
		add(obj);
	}

	@Override
	public void addElement(E obj, V elem) {
		if(size < capacity){
			storageArrayMain[size] = obj;
			storageArrayBackUp[size] = elem;
			size++;
		}
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		  storageArrayMain = (E[])new Object[capacity];
		  storageArrayBackUp = (V[])new Object[capacity];
		  size = 0;
	}

	@Override
	public E firstElement() {
		return storageArrayMain[0];
	}

	@Override
	public E get(int index) {
		return storageArrayMain[index];
	}

	@Override
	public E lastElement() {
		return storageArrayMain[size-1];
	}

	@Override
	public Object clone(){
		return new StorageFixed<E, V>(storageArrayMain, storageArrayBackUp, size);
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (int x = 0; x < size; x++){
			result.append(storageArrayMain[x] + " ");
		}
		return result.toString();
	}
}