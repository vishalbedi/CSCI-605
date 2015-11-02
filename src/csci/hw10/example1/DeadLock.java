package csci.hw10.example1;

public class DeadLock extends Thread {
	private static Object lock = new Object();
	private static Object lockAgain = new Object();
	private String id;

	public DeadLock(String id) {
		this.id = id;
	}

	public void run() {
		synchronized (lock) {
			lock.notify();
			System.out.println(id);
			synchronized (lockAgain) {
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("I am outside lock... " + id);
			}
		}
	}

	public static void main(String args[]) {
		DeadLock t1 = new DeadLock("1");
		DeadLock t2 = new DeadLock("0");

		t1.start();
		t2.start();
	}
}
