/*
 * Look at the Hello program:   
 * Classical: Hello, World
 *
 *
 * @version   $Id: Hello.java,v 1.3 2001/06/06 23:05:46 hpb Exp hpb $
 *
 * @author    hpb
 *
 * Revisions:
 *
 *      Revision 1.41  2013/06/06 16:19:12  hpb
 *      Revision 1.42  2014/08/06 10:31:21  hpb
 *      Initial revision
 *
       

      class Hello {
          public static void main (String args []) { // main program
              System.out.println("Hello World!");
          }
      }


	and modify it in a way, that it prints out the operating system name. It is not allowed to hard code the os name. This is an output example if I execute the program:
	% java OsName.java
	OS: OS X
 */




/*
 * 
 * OsName.java
 * 
 * Version: 1.0
 * 
 */
package csci.hw1;

/**
 * Fetches and displays OS Name
 * 
 * @author Vishal Bedi(vgb8777@rit.edu)
 *
 */
public class OsName {
	/**
	 * @description : Fetches OS name and prints
	 * 
	 * @return null
	 */
	private static void printName() {
		String osName = System.getProperty("os.name") ;
		if(osName.isEmpty()){
			System.out.println("Java was not able to get OS name");
		}else{
			System.out.println("OS : " + osName);
		}
	}
	
	public static void main (String args[]){
		printName();
	}

}
