/*
 *  * Explain the execution of of:
 * % java AA
 * AA: 13
 * AA: 13
 * aA: 11
 * % java AAA
 * aA:        AAA: 11
 * aAA:       AAA: 11
 * aAAA:      AAA: 11
 * aAAA:.aInt 14
 * Add your comments to the source code.
 * */


package csci.hw3.example2;

public class AA extends A { //AA is child class of A

	int aInt = 1; 

	AA() {
		aInt = 11; //Sets aInt to 11 whenever an object of AA is created
	}

//	public int intPlusPlus() {
//		return ++aInt;
//	}
	
	
	public String toString() {
		return this.getClass().getName() + ": " + aInt;  //Override toString to display class name with aInt value 
	}

	public static void main(String args[]) {
		AA aAA = new AA(); //Create an object of AA
		A aA = (A) aAA; //Typecast an object of AA to A as all child class objects can behave as parent class objects.
		aAA.intPlusPlus(); //inc aInt of AA class
		aA.intPlusPlus(); //inc aInt of AA class
		System.out.println(aA);
		System.out.println(aAA);
		System.out.println("aA: " + aA.aInt); //member fields cannot be extended thus output will be 11
	}
}
