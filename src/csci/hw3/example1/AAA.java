package csci.hw3.example1;

public class AAA extends AA {

	int aInt = 1;

	AAA() {
		aInt = 11;
	}

	public int intPlusPlus() {
		return ++aInt;
	}

	public static void main(String args[]) {
		AAA aAAA = new AAA(); //Create an object of AAA, This will call the constructor of AA and A also
		AA aAA = (AA) aAAA; // typecast object to object of AA
		A aA = (A) aAA; // typecast to object A
		aAAA.intPlusPlus(); //Increment aInt of AAA
		aAA.intPlusPlus(); //increment aInt of AAA
		aA.intPlusPlus(); //Increment aInt of AAA
		System.out.println("aA:        " + aA); //prints 11 as toString of AA is called
		System.out.println("aAA:       " + aAA);//prints 11 as toString of AA is called
		System.out.println("aAAA:      " + aAAA);//Prints 11 as toString of AA is called
		System.out.println("aAAA:.aInt " + aAAA.aInt); // prints 14 as it was incremented thrice in previous calls
	}
}
