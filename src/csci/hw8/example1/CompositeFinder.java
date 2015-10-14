package csci.hw8.example1;

/**
 * 
 * @author Vishal Bedi, Daichi Mae
 * 
 * Find composite numbers
 *
 */

public class CompositeFinder implements Runnable {
	final boolean[] numbers;
	final int MAX;
	final int base;
	
	public CompositeFinder(boolean[] numbers, int max, int base) {
		this.numbers = numbers;
		this.MAX = max;
		this.base = base;
	}

	/**
	 * Starting from the base number, enumerate its multiples by counting to MAX,
	 * and mark them in the list
	 */
	@Override
	public void run() {
		int counter = 2;
		while ( base * counter < MAX ) {
			numbers[base * counter] = false;
			counter++;
		}                    
	}
}
