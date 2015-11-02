package csci.hw10.example2;

public class Semaphore {
	private int count;
	private int weight;
	public Semaphore(int count, int weight) {
		this.count = count;
		this.weight = weight;
	}
	
	public synchronized void P(int weight) {
		while (count <= 0 || this.weight <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		count = count - 1;
		this.weight = this.weight - weight;
	}
	
	public synchronized void V(int weight) {
		count = count + 1;
		this.weight = this.weight + weight;
		if (count > 0 && this.weight > 0) {
			notify();
		}
	}
}