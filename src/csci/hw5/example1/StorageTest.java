package csci.hw5.example1;

/**
 * @author Vishal
 *
 */
public class StorageTest {
	public static void main(String args[]) {
		Storage<String, Integer> aStorageString = new StorageFixed<String, Integer>();
		aStorageString.add("a");
		aStorageString.add("b");
		aStorageString.add("c");
		System.out.println(aStorageString.capacity());
		System.out.println(aStorageString.firstElement());
		System.out.println(aStorageString.lastElement());
		
		aStorageString.add(1, "d");
		aStorageString.add(4, "e");
		aStorageString.add(0, "f");
		aStorageString.add(50, "foo"); 
		aStorageString.add(80, "bar");
		System.out.println(aStorageString.lastElement());
		System.out.println(aStorageString.get(80));
		
		System.out.println(aStorageString);
		
/*		Storage<Integer, String> aStorageInteger = new StorageDynamic<Integer, String>();
		aStorageInteger.add(1);
		aStorageInteger.add(2);
		aStorageInteger.add(3);
		aStorageInteger.add(4);
		aStorageInteger.add(5);
		aStorageInteger.add(2, 12);
		System.out.println(aStorageInteger);
		*/
	}
}