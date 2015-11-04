package csci.hw9.example3;

import java.util.LinkedList;
import java.util.Random;

public class Producer extends Thread {
	private LinkedList<String> storage;
	LinkedList<String> finalStorage;
	private int id = 0;
	private String task;

	public Producer(String task, LinkedList<String> storage, LinkedList<String> finalStorage) {
		this.storage = storage;
		this.finalStorage = finalStorage;
		this.task = task;
	}

	private void makeStuff() {
		Random random = new Random();
		while (true) {
			synchronized (finalStorage) {
				if(finalStorage.size() == 100)
					System.exit(0);
			}
			synchronized (storage) {
				storage.addLast("" + id++);
//				try {
//					Thread.sleep(random.nextInt(1000));
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				storage.notifyAll();
				System.out.println(task + " " + id);
			}
		}
	}

	@Override
	public void run() {
		makeStuff();
	}

}
