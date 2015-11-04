package csci.hw9.example3;

import java.util.LinkedList;

public class BoxConsumer extends Thread {

	private LinkedList<String> boxStore;
	private LinkedList<String> wrappedCandyStorage;
	private LinkedList<String> finalCandyStore;
	private int boxCount = 0;

	public BoxConsumer(LinkedList<String> boxStore, LinkedList<String> wrappedCandyStorage,
			LinkedList<String> finalCandyStore) {
		this.boxStore = boxStore;
		this.wrappedCandyStorage = wrappedCandyStorage;
		this.finalCandyStore = finalCandyStore;
	}

	public void run() {
		while (true) {

			String box, wrappedCandy;
//			System.out.println("Box Consumer");
			synchronized (wrappedCandyStorage) {
				System.out.println("in sync");
				while (wrappedCandyStorage.size() < 4) {
					try {
//						System.out.println("Wrap wait");
						wrappedCandyStorage.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
				}
//				System.out.println("removing wrapped candy");
				wrappedCandy = wrappedCandyStorage.removeFirst();
				wrappedCandy += wrappedCandyStorage.removeFirst();
				wrappedCandy += wrappedCandyStorage.removeFirst();
				wrappedCandy += wrappedCandyStorage.removeFirst();

				synchronized (boxStore) {
					while (boxStore.isEmpty()) {
						try {
//							System.out.println("Box wait");
							boxStore.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					box = boxStore.removeFirst();
				}

			}
			synchronized (finalCandyStore) {
				String boxedCandy = box + "#" + wrappedCandy;
				finalCandyStore.add(boxedCandy);
				finalCandyStore.notifyAll();
				System.out.println("Boxes Filled " + boxCount);
				boxCount++;
			}
		}
	}
}
