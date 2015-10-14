package csci.hw8.example1;

/**
 * 
 * @author Vishal Bedi, Daichi Mae
 * 
 * A parallel version of the sieve of Eratosthenes 
 *
 */

public class SieveOfEratosthenes {

    final static int FIRSTpRIMEuSED = 2;
    static int MAX;
    final boolean[] numbers;

    public SieveOfEratosthenes(int max) {
    	numbers = new boolean[max];
    	MAX = max;
    }
    
    public void determinePrimeNumbers()	{
    	for (int index = 1; index < MAX; index ++ )	{
    		numbers[index] = true;
    	}
	
    	int limit = (MAX > 10 ? (int)Math.sqrt(MAX) + 1 : 3);
	
    	for (int index = 2; index < limit; index ++ ) {						  // this is the part for the parallel part
    		if ( numbers[index] ) {											  // this is the part for the parallel part
    			new Thread(new CompositeFinder(numbers, MAX, index)).start(); // this is the part for the parallel part
    		}
    	}
    }
    
    public void testForPrimeNumber() {
    	int[] test = { 2, 3, 4, 7, 13, 17, MAX - 1, MAX};
    	for (int index = 0; index < test.length; index ++ )	{
    		if ( test[index] < MAX ) {
    			System.out.println(test[index] + " = " + numbers[test[index]]);
    		}
    	}
    }

    public static void main( String[] args ) {
    	SieveOfEratosthenes aSieveOfEratosthenes = new SieveOfEratosthenes(20);
    	aSieveOfEratosthenes.determinePrimeNumbers();
    	aSieveOfEratosthenes.testForPrimeNumber();
    	System.exit(0);
    }
}
