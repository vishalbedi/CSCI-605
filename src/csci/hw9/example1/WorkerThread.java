package csci.hw9.example1;

import java.util.LinkedList;

public class WorkerThread extends Thread {

	private LinkedList<Runnable> queue;

	public WorkerThread(LinkedList<Runnable> queue) {
		this.queue = queue;
	}

	public void run() {
		// TODO Auto-generated method stub
		Runnable t;
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				t = (Runnable) queue.removeFirst();
				try {
					t.run();
				} catch (Exception e) {
					// Couldn't run the thread
				}
				System.out.println(getName());
			}
		}
	}
}
