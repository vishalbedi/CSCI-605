//Implement a program that takes a integer n from the command line and
//finds all prime numbers p_i; 1 <= i <= k; so such n = p1 * p2 * p3 â¦ * pk.
//Example:
//% java Factorization 12
//12 = 2 * 2 * 3


/**
 * 
 * Factorization.java
 * 
 * Version: 1.0
 * 
 */
package csci.hw2;


import java.util.ArrayList;
import java.util.Scanner;

import csci.hw1.Numbers;
/**
 * @description Gets all the factors of a number
 * 
 * @author Vishal
 * @author Daichi Mae
 */
public class Factorization {
	
	/**
	 * @description Returns Prime factors of an integer n
	 * 
	 * @param integer
	 * @return ArrayList<Integer> primeFactors
	 */
	public static ArrayList<Integer> getPrimeFactors(int number){
		int halfNumber = number % 2 == 0 ? number/2 : (number+ 1)/2;
		ArrayList<Integer> primes = Numbers.getprimeList(halfNumber);
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();
		for (int prime : primes){
			if(number % prime == 0){
				primeFactors.add(prime);
			}
		}
		return primeFactors;
	}
	
	/**
	 * @description Returns all the factors of an integer n
	 * 
	 * @param integer
	 * @return ArrayList<Integer> factors
	 */
	public static ArrayList<Integer> getFactors(int number){
		ArrayList<Integer> factors = getPrimeFactors(number);
		int multiple = getMultiple(factors);
		while(multiple != number){
			factors.addAll(getFactors(number/multiple));
			multiple = getMultiple(factors);
		}
		return factors;
	}
	
	/**
	 * @description Multiplies all the number in an arrayList
	 * 
	 * @param ArrayList<Integer> numbers 
	 * @return multiple
	 */
	public static int getMultiple (ArrayList<Integer> primeFactors){
		int multiple = 1;
		for (int primeFactor : primeFactors){
			multiple *= primeFactor;
		}
		return multiple;
	}
	/**
	 * @description read the number from CLI and prints out its factors
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number to factorize");
		int number;
		try(Scanner sc = new Scanner(System.in)){
			number = sc.nextInt();
		}		
		ArrayList<Integer> factors = getFactors(number);
		System.out.println(factors);
	}

}
