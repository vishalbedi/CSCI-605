package csci.hw10.example3;

/**
 * BufferRead.java
 * 
 * @author Vishal, Daichi
 * 
 * @description This class reads the data from non blocking buffer and prints it on to the console.
 *
 */

public class BufferRead implements Runnable {
	private BufferStore bs;
	private int bufferSize = 1024;

	public BufferRead(BufferStore bs) {
		this.bs = bs;
	}

	@Override
	public void run() {
		String aWord = "";
		synchronized (bs) {
			while (true) {
				try {
					while (bs.size <= 0) {
						bs.notify();
						bs.wait();
					}
					char[] ba = bs.get(0, bs.size > bufferSize? bufferSize : bs.size);
					for(int i = 0; i < ba.length; i++){
						aWord += ba[i];
					}
					System.out.print(aWord);
					bs.notify();
					bs.wait(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
