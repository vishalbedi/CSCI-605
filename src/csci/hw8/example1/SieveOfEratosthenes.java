/*
 * SieveOfEratosthenes.java
 */

package csci.hw8.example1;

/**
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 * A parallel version of the sieve of Eratosthenes 
 *
 */

public class SieveOfEratosthenes {

    final static int FIRSTpRIMEuSED = 2;
    static int MAX;
    final boolean[] numbers;
    private ThreadPool pool;
    public SieveOfEratosthenes(int max) {
    	numbers = new boolean[max];
    	MAX = max;
    }
    
    private void initThreadPool (int threadCount){
    	pool = new ThreadPool(threadCount);
    }
    
    public void determinePrimeNumbers()	{
    	for (int index = 1; index < MAX; index ++ )	{
    		numbers[index] = true;
    	}
	
    	int limit = (MAX > 10 ? (int)Math.sqrt(MAX) + 1 : 3);
	
    	for (int index = 2; index < limit; index ++ ) {						  // this is the part for the parallel part
    		if ( numbers[index] ) {											  // this is the part for the parallel part
    			pool.enqueue(new CompositeFinder(numbers, MAX, index)); // this is the part for the parallel part
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
    	SieveOfEratosthenes aSieveOfEratosthenes = new SieveOfEratosthenes(2000);
    	aSieveOfEratosthenes.initThreadPool(4);
    	aSieveOfEratosthenes.determinePrimeNumbers();
    	aSieveOfEratosthenes.testForPrimeNumber();
    	System.exit(0);
    }
}
