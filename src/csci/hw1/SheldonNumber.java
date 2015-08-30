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
