/*
 * T_2.java
 */

package csci.hw8.example3;

/**
 * Possible outputs:
 * theValue = 1
 * theValue = 1
 * 
 * theValue = 1
 * theValue = 2
 * 
 * theValue = 1
 * theValue = 3
 * 
 * theValue = 2
 * theValue = 2
 * 
 * theValue = 2
 * theValue = 3
 * 
 * theValue = 3
 * theValue = 2
 * 
 * theValue = 3
 * theValue = 3
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 *
 */
public class T_2 extends Thread {
    int id = 1;
    static String theValue  = "1";
 
    T_2(int id) {
    	this.id = id;
    }
    
    public void run () {
    	if ( id == 1 )
    		theValue = "3";
        else
        	theValue = "2";
    }      
        
    public static void main (String args []) {
        new T_2(1).start();;
        new T_2(2).start();;
            
        System.out.println("theValue = " + theValue );
        System.out.println("theValue = " + theValue );
    }       
}