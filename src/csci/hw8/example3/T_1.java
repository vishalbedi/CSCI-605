/*
 * T_1.java
 */

package csci.hw8.example3;

/**
 * Possible outputs:
 * 3 a: 3		5 b: 5
 * 5 b: 5		3 a: 3
 * 
 * 3 a: 4		5 b: 5
 * 5 b: 5		3 a: 4
 * 
 * 3 a: 5		5 b: 5
 * 5 b: 5		3 a: 5
 * 
 * 4 a: 4		5 b: 5
 * 5 b: 5		4 a: 4
 * 
 * 4 a: 5		5 b: 5
 * 5 b: 5		4 a: 5
 * 
 * 5 a: 5		5 b: 5
 * 5 b: 5		5 a: 5
 * 
 * 5 a: 5		3 b: 3
 * 3 b: 3		5 a: 5
 * 
 * 5 a: 5		3 b: 4
 * 3 b: 4		5 a: 5
 * 
 * 5 a: 5		3 b: 5
 * 3 b: 5		5 a: 5
 * 	
 * 5 a: 5		4 b: 4
 * 4 b: 4		5 a: 5
 * 
 * 5 a: 5		4 b: 5
 * 4 b: 5		5 a: 5
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 *
 */
public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;
	}

	public void run () {
		x++;
		String output = x + " " + info + ": " + x;
		System.out.println(output);
	}

	public static void main (String args []) {
		new T_1("a").start();
		new T_1("b").start();
	}
}