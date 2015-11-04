package csci.hw9.example3;

import java.util.LinkedList;

public class WrappingConsumer extends Thread {

	private LinkedList<String> candyStorage;
	private LinkedList<String> wrapperStorage;
	private LinkedList<String> wrappedCandyStorage;
	private LinkedList<String> finalCandyStore;

	public WrappingConsumer(LinkedList<String> candyStorage, LinkedList<String> wrapperStorage,
			LinkedList<String> wrappedCandyStorage, LinkedList<String> finalCandyStore) {
		this.candyStorage = candyStorage;
		this.wrapperStorage = wrapperStorage;
		this.wrappedCandyStorage = wrappedCandyStorage;
		this.finalCandyStore = finalCandyStore;
	}

	public void run() {
		while (true) {
			synchronized (finalCandyStore) {
				if (finalCandyStore.size() == 100)
					System.exit(0);
			}

			String candy, wrapper;
			synchronized (candyStorage) {
				while (candyStorage.isEmpty()) {
					try {
						candyStorage.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				candy = candyStorage.removeFirst();
			}
			synchronized (wrapperStorage) {
				while (wrapperStorage.isEmpty()) {
					try {
						wrapperStorage.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
				}
				wrapper = wrapperStorage.removeFirst();
			}
			synchronized (wrappedCandyStorage) {
				String wrappedCandy = candy + "#" + wrapper;
				wrappedCandyStorage.addLast(wrappedCandy);
				wrappedCandyStorage.notify();

				System.out.println("Candy wrapped and stored " + wrappedCandy);
			}

		}
	}
}
