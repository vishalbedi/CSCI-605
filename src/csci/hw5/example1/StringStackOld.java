package csci.hw5.example1;

/**
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */

//this implementation implements the methods,
//but the methods are null methods;
public class StringStackOld implements StackInterfaceOld {
 
 public void push( Object item )	{	       }
 public Object pop() 		{ return null; }
 public Object peek() 		{ return "hi"; }
 public boolean isEmpty() 		{ return true; }

 public static void main(String args[])	{
	StackInterfaceOld aStackInterfaceOld = new StringStackOld();
	/*
	 * Since the push method expects an object all the classes extends object class
	 * thus strings can be pushed inside the stack 
	 */
	aStackInterfaceOld.push("hello");	// why is here no warning?
	/*
	 * The pop() method returns an object of the Object class. 
	 * Since we are trying to store the value of returned by the pop method into
	 * a String type variable we get an error of type mismatch.
	 * If we cast the object of Object class into string this error will go away at compile time
	 * if the inserted element was not a string it will result in an cast exception at run time.
	 */
	String aString = (String)aStackInterfaceOld.pop();
 }
/*
javac StringStackOld.java			// explain this error
StringStackOld.java:11: incompatible types	// explain what a cast would do
found   : java.lang.Object			// regarding possible compiler error detection
required: java.lang.String
	String aString = aStackInterfaceOld.pop();
	                                       ^
1 error

*/

}