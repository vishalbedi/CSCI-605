package csci.hw3.example2;
/*
 * You are allowed to add up to 6 characters to one of the 3 source files, so such the execution of AA produces the following output:
 * % java AA
 * AA: 11
 * AA: 11
 * aA: 13
 */
public class A {

	int aInt = 1;

	A() {
		aInt = 11; //Sets aInt to 11 whenever an object of AA is created
	}

	public int intPlusPlus() {
		return ++aInt; //Pre increment aInt
	}

	public String toString() {
		return this.getClass().getName() + ": " + aInt; //Override toString to display class name with aInt value 
	}

	public static void main(String args[]) {
		A aA = new A(); //Create an object of A and sets aInt to 11
		aA.intPlusPlus(); //sets aInt to 12
		System.out.println(aA); //Prints 12 along with class name of A
	}
}