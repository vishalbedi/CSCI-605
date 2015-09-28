/**
 * 
 */
package csci.hw5.example1;

/**
 * @author Vishal
 *
 */
public class StorageTest {
	public static void main(String args[]) {
		Storage<String, String> aStorageString = new StorageFixed<String, String>();
		Storage<Integer, String> aStorageInteger = new StorageDynamic<Integer, String>();
		aStorageInteger.add(1);
		aStorageInteger.add(2);
		aStorageInteger.add(3);
		aStorageInteger.add(4);
		aStorageInteger.add(5);
		
		
		aStorageInteger.add(2, 12);
		System.out.println(aStorageInteger);
	}
}
