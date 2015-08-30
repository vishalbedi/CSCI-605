/**
 * 
 */
package csci.hw1;

import java.util.ArrayList;

/**
 * @author Vishal
 *
 */
public class SheldonNumber {

	private static boolean isPrime(int number, ArrayList<Integer> primes) {
		int listSize = primes.size();
		double limit = Math.floor(Math.sqrt(number));
		int lastPrime = primes.get(listSize-1);
		if(limit <= lastPrime){
			for(int i = 0; primes.get(i) <= limit; i++){
				if(number % primes.get(i) == 0){
					return false;
				}
			}
			return true;
		}else {
			for(int i=0; i < limit; i++){
				if(number % i == 0){
					return false;
				}
			}
			return true;
		}
	}
	private static ArrayList<Integer> primeList(int limit) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		primeList.add(2);
		primeList.add(3);

		for(int i=4, j=2; i <= limit; i=2*j+1, j++){
			if(isPrime(i, primeList)){
				primeList.add(i);
			}
		}
		return primeList;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int primeLimit = 100000;
		System.out.println(primeList(primeLimit));
	}

}
