//Explain the following program by adding comments to the program:


/**
 * 
 * OnceOrMany.java
 * 
 * Version: 1.0
 * 
 */

package csci.hw2;

/**
 * @author Daichi Mae
 * @author Vishal
 * 
 */
public class OnceOrMany {

	/**
	 * @description Compares two strings. True if both have same value and are literals, false otherwise
	 * @param String literal
	 * @param String aNewString
	 * @return boolean 
	 */
	public static boolean singelton(String literal, String aNewString) {
		return (literal == aNewString);//Comparison of two strings. True if the two strings are literals: False if aNewString is string object 
	}

	/**
	 * @description main function. Executes all the static functions to compare String object and literals
	 * @param args
	 * @return null
	 */
	public static void main(String[] args) {
		
		String aString = "xyz";
        System.out.println("1.  xyz == aString: " +     "xyz" == aString   );//False as we are comparing xyz with "1.  xyz == aString: xyz"
        	//The two strings are concatenated first then compared. 
        System.out.println("2.  xyz == aString: " +   ( "xyz" == aString ) );//True as both the literals are same
        
        String newString = new String("xyz");
        System.out.println("xyz == new String(xyz)\n    " + ("xyz" == newString) );//False as we compare a literal and a string object
        
        System.out.println("1: " + singelton("xyz", "xyz")); //True as we compare two literals
        System.out.println("2: " + singelton("xyz", new String("xyz") ));//False as we compare literal and string object
        System.out.println("3: " + singelton("xyz", "xy" + "z"));//True as the concatenation of strings happen first then the final string 
        	//is passed as the parameter to the function
        System.out.println("4: " + singelton("x" + "y" + "z", "xyz")); //True as both the parameters are same literals
        System.out.println("5: " + singelton("x" + "y" + new String("z"), "xyz"));// False comparing a literal and string object
        System.out.println("6: " + singelton("x" + ( "y" + "z"), "xyz"));// True x will be added with y and z to make xyz literal and it will be passed to the function
        System.out.println("7: " + singelton('x' + ( "y" + "z"), "xyz"));//True char x will be added to string yz making it a literal xyz.
        
	}

}
