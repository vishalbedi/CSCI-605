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
package csci.hw1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
	
	
	private static ArrayList<Integer> getprimeList(int limit) {
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
	
	private static boolean isPalindrome (String wannabePalindrome){
		String wannabePalindromeReverse = new StringBuffer(wannabePalindrome).reverse().toString();
		if(wannabePalindrome.equals(wannabePalindromeReverse)){
			return true;
		}
		return false;
	}
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
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int primeLimit = 100000;
		ArrayList<Integer> primes = getprimeList(primeLimit);
		System.out.println("pi(100000)" + primes.size()); //Number of primes <100000 = 9592 :: this looks correct
		ArrayList<Integer> palindromePrimes = getPalindromePrimes(primes);
		HashMap<Integer, Integer> reversePrimes = getReversePrime(palindromePrimes, primes);
		ArrayList<Integer> sheldonNumber = getSheldonNumber(reversePrimes, primes);
		System.out.println("Sheldon Numbers Are: " + sheldonNumber);
	}

}
