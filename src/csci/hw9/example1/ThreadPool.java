package csci.hw9.example1;

import java.util.LinkedList;

public class ThreadPool {
	private LinkedList<Runnable> queue;
	private WorkerThread[] threads;

	public ThreadPool(int threadCount) {
		queue = new LinkedList<Runnable>();
		threads = new WorkerThread[threadCount];
		initThreads(threadCount);
	}

	public void initThreads(int maxCount) {
		for (int i = 0; i < maxCount; i++) {
			threads[i] = new WorkerThread(queue);
			threads[i].start();
		}
	}

	public void enqueue(Runnable r) {
		synchronized (queue) {
			queue.addFirst(r);
			queue.notify();
		}
	}
}
