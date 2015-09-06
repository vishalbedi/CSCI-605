/*
 * Sheldon (more or less from the clip): "The best number is 73. Why? 73 is the 21st prime number. Its mirror, 37, is the 12th and its mirror, 21, is the product of multiplying 7 and 3... and in binary 73 is a palindrome, 1001001, which backwards is 1001001."
 *	Definitions:
 *	- n: is a integer in the range between 2 and 100000 
 *	- m: is the mirror of n (n=73, m=37) 
 *	- bN: is the binary representation of n 
 *	- bM: is the binary representation of m
 *	Write a program that looks for all numbers m, and n, which meets all of the following properties:
 *	- n is the k.st prime number (73 is the 21. prime number) 
 *	- m is mirror of k.st prime number (37 is the 12. prime number) 
 *	- bN is a palindrome
 *	You have to name your program Numbers.java. A possible execution:
 *	% java Numbers
 *	 ...
 *	 73
 *	 ...
 * 
 */


/*
 * 
 * Numbers.java
 * 
 * Version: 1.0
 * 
 */
package csci.hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * @description Program to calculate Sheldon Numbers
 * 
 * @author Daichi Mae
 * @author Vishal Bedi
 */
public class Numbers {

	/**
	 * @description Checks if a number is a prime given n-1 primes
	 * @param number
	 * @param primes
	 * @return boolean 
	 */
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
	
	
	/**
	 * @description Prime generator till n
	 * 
	 * @param limit
	 * @return ArrayList<Integer> primeList
	 */
	public static ArrayList<Integer> getprimeList(int limit) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		primeList.add(2);

		for(int i=3, j=2; i <= limit; i=2*j+1, j++){
			if(isPrime(i, primeList)){
				primeList.add(i);
			}
		}
		return primeList;
	}
	
	/**
	 * @description Checks if the given string is a palindrome
	 * 
	 * @param wannabePalindrome
	 * @return boolean isPalindrome
	 */
	private static boolean isPalindrome (String wannabePalindrome){
		String wannabePalindromeReverse = new StringBuffer(wannabePalindrome).reverse().toString();
		if(wannabePalindrome.equals(wannabePalindromeReverse)){
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * @description returns a list of primes whose binary representation is a palindrome
	 * 
	 * @param primes
	 * @return ArrayList<Integer> primeNumbers
	 */
	private static ArrayList<Integer> getPalindromePrimes(ArrayList<Integer> primes){
		ArrayList<Integer> palindromePrimes = new ArrayList<Integer>();
		for(int prime : primes){
			String binaryPrime = Integer.toBinaryString(prime);
			if(isPalindrome(binaryPrime)){
				palindromePrimes.add(prime);
			}
		}
		return palindromePrimes;
	}
	
	/**
	 * @description Checks if the reverse of the prime is also a prime. If true adds to a HashMap with prime as key and reverse of the prime as value.
	 * 
	 * 
	 * @param ArrayList<Integer> palindromePrimes - List of primes to check if reverse is also a prime.
	 * @param ArrayList<Integer> primes - All the primes till n
	 * @return ArrayList<Integer> HashMap<Integer, Integer> reversePrimes
	 */
	private static HashMap<Integer, Integer> getReversePrime(ArrayList<Integer> palindromePrimes, ArrayList<Integer> primes){
		HashMap<Integer, Integer> reversePrimes = new HashMap<Integer,Integer>();
		for(int prime : palindromePrimes){
			int reverseOfPrime = Integer.parseInt(new StringBuffer(""+prime).reverse().toString());
			if(primes.contains(reverseOfPrime)){
				reversePrimes.put(prime, reverseOfPrime);
			}
		}
		return reversePrimes;
	}
	
	
	/**
	 * @description Compares the index of all the prime number who are palindrome and whose reverse is also a prime.
	 * It checks if the index of the prime and the reverse of the prime are also reverse of each other.
	 * e.g. 73 is the 21st prime number. Its mirror, 37, is the 12th and its mirror, 21.
	 * 
	 * @param HashMap<Integer, Integer> reversePrimePalindromes 
	 * @param ArrayList<Integer> primes 
	 * @return ArrayList<Integer> SheldonNumbers
	 */
	private static ArrayList<Integer> getSheldonNumber (HashMap<Integer, Integer> reversePrimePalindromes, ArrayList<Integer> primes){
		ArrayList<Integer> sheldonNumber = new ArrayList<Integer>();
		for (Entry<Integer, Integer> reversePrimePalindrome : reversePrimePalindromes.entrySet()){
			int prime = reversePrimePalindrome.getKey();
			int reverseOfPrime = reversePrimePalindrome.getValue();
			int primeIndex = primes.indexOf(prime) + 1;
			int reverseOfPrimeIndex = primes.indexOf(reverseOfPrime) + 1;
			int reversePrimeReverseIndex = Integer.parseInt(new StringBuffer(""+reverseOfPrimeIndex).reverse().toString());
			if(primeIndex == reversePrimeReverseIndex){
				sheldonNumber.add(prime);
			}
			
		}
		return sheldonNumber;	
	}
	/**
	 * @description main function. Executes all the static functions to find Sheldon Numbers
	 * @param args
	 * @return null
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int primeLimit = 100000;
		long startTime = System.nanoTime();
		ArrayList<Integer> primes = getprimeList(primeLimit);
		System.out.println("pi(100000)" + primes.size()); //Number of primes <100000 = 9592 :: this looks correct
		ArrayList<Integer> palindromePrimes = getPalindromePrimes(primes);
		HashMap<Integer, Integer> reversePrimes = getReversePrime(palindromePrimes, primes);
		ArrayList<Integer> sheldonNumber = getSheldonNumber(reversePrimes, primes);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
		System.out.println("Time to run the program: " + duration/1000000 + " ms");
		System.out.println("Sheldon Numbers Are: " + sheldonNumber);
	}

}
