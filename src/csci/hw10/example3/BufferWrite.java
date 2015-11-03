package csci.hw10.example3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * BufferWrite.java
 * 
 * @author Vishal, Daichi
 * 
 * @description This class reads the data from file
 * as non blocking IO
 *
 */

public class BufferWrite implements Runnable {
	private BufferStore bs;
	private int bufferLimit = 10 * 1024;
	private String file;

	public BufferWrite(BufferStore bs, String file) {
		// TODO Auto-generated constructor stub
		this.bs = bs;
		this.file = file;
	}

	private BufferedReader getReader(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return br;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader br = getReader(file);
		String aWord = null;
		synchronized (bs) {
			try {
				while ((aWord = br.readLine()) != null) {
					aWord += '\n';
					if (bs.size < bufferLimit) {
						char[] ba = aWord.toCharArray();// create a char array
						bs.add(ba);
						bs.notify();
					} else {
						bs.notify();
						bs.wait();
					}
				}
				bs.notify();
				bs.wait();
				while(bs.size != 0){
					bs.notify();
					bs.wait();
				}
				System.exit(0);
			} catch (IOException ex) {
				System.out.println("Something went wrong with reading file");
			} catch (InterruptedException ex) {
				System.out.println("Something went wrong with wait");
			}
		}
	}

}
