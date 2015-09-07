package csci.hw2;

public class FactorizationArray {
	
	public static int[] factors;
	public static int totalFactors = 0;
	/**
	 * @description : Return true if n is prime otherwise return false.
	 * 
	 * @param n
	 * @return
	 */
	private static boolean isPrime(int n){
		int limit = (int) (Math.sqrt(n) + 1);
		for(int i = 2; i <= limit; i++){
			if(n % i == 0) return false;
		}
		return true;
	}
	
	/**
	 * @description : Print the prime factor as many as n has.
	 * 
	 * @param n
	 * @param prime
	 */
	private static void printFactors(int n, int prime){
		if((n % prime) == 0) {
			factors[totalFactors] = prime;
			totalFactors++;
			printFactors(n / prime, prime);
		}
	}
	
	/**
	 * @description : Print the prime factors of n.
	 * 
	 * @param n
	 */
	private static void factorize(int n){
		System.out.print(n + " = ");
		printFactors(n,2);
		for(int i = 3; i <= n / 2; i +=2){
			if(isPrime(i)) printFactors(n, i);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numToFactorize = Integer.parseInt(args[0]);
		factors = new int[numToFactorize == 0 ? numToFactorize/2 : (numToFactorize +1)/2];
		factorize(numToFactorize);
		for(int i=0; i< totalFactors; i++){
			System.out.print(factors[i]);
			if(i != totalFactors-1){
				System.out.print(" * ");
			}
		}
		

	}
}
