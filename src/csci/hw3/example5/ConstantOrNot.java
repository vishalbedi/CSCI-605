package csci.hw3.example5;

import java.util.Vector; // what does this line do?
//This line imports a Vector class

class ConstantOrNot {

	private final int aInt = 1;
	private final String aString = "abc";
	private final Vector aVector = new Vector();

	public void doTheJob() {
		// aInt = 3; why would this fail?
		// aInt is declared final so it cannot be reassigned
		// aString = aString + "abc"; why would this fail?
		// aString is a final field and cannot be changed or reassigned
		aVector.add("abc"); // why does this work?
		// We are not modifying the vector but adding elements to it
		//aVector = new Vector<String>(); is not allowed
	}

	public static void main(String args[]) {
		new ConstantOrNot().doTheJob();
	}
}
