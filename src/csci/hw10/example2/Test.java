package csci.hw10.example2;

import java.util.Random;
import java.util.Scanner;

public class Test implements Runnable {
	private static Semaphore lock;
	private Truck t;
	public Test(Truck t) {
		this.t = t;
	}
	
	public static void main(String args[]){
		System.out.println("How many trucks ???");
		Scanner sc = new Scanner(System.in);
		int totalTrucks = sc.nextInt();
		int maxWeight = 200000;
		int maxTrucks = 4;
		lock = new Semaphore(maxTrucks, maxWeight);
		Random rand = new Random();
		for (int i = 0; i < totalTrucks; i++){
			new Thread (new Test(new Truck(rand.nextInt(1000000), i, i%2 == 0 ? Direction.RIGHT : Direction.LEFT))).start();;
		}
	}

	@Override
	public void run() {
		try {
			lock.P(this.t.weight);
			System.out.println("Crossing the bridge... " + t.id);
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.V(this.t.weight);
			System.out.println("Bridge Crossed... " + t.id);
		}
	}
	
}
