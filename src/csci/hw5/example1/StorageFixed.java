package csci.hw5.example1;

import java.lang.reflect.Array;

/*
 * Implement a class using generics for each of the requirements:
 •
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

public class StorageFixed <E,V> implements Storage<E,V>{
	
	Class<E> ec;
	Class<V> vc;
	private int capacity = 100;
	@SuppressWarnings( "unchecked" )
	private  E[] storageArrayMain = (E[])Array.newInstance(ec, capacity);
	@SuppressWarnings( "unchecked" )
	private  V[] storageArrayBackUp = (V[])Array.newInstance(vc, capacity);
	private int size = 0;
	private int lastIndex = size;
	
	private StorageFixed(E[] e, V[] v) {
		storageArrayMain = e;
		storageArrayBackUp = v;
	}
	
	@Override
	public boolean add(E e) {
		if(size < capacity){
			storageArrayMain[size] = e;
			storageArrayBackUp[size] = null;
			size++;
			setLastIndex(size);
			return true;
		}
		return false;
	}

	@Override
	public boolean add(int index, E element) {
		if(index < capacity){
			size = storageArrayMain[index] == null ? size++ : size;
			storageArrayMain[index] = element;
			storageArrayBackUp[index] = null;
			setLastIndex(index);
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
			setLastIndex(size);
		}
	}

	@Override
	public int capacity() {
		return capacity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		  storageArrayMain = (E[])Array.newInstance(ec, capacity);  
		  storageArrayBackUp = (V[])Array.newInstance(vc, capacity);
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
		return storageArrayMain[lastIndex-1];
	}

	@Override
	public Object clone(){
		return new StorageFixed<E, V>(storageArrayMain, storageArrayBackUp);
	}
	
	private void setLastIndex(int index){
		lastIndex = lastIndex >= index ? lastIndex : index;
	}

}
