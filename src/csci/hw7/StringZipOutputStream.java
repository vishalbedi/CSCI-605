package csci.hw7;

import java.io.IOException;
import java.io.OutputStream;
/**
 * 
 * @author Vishal, Daichi
 * 
 * Class to compress a text file that contains ASCII characters
 *
 */
public class StringZipOutputStream {

	private int buffer;
	private int n;
	private final int EOF = 256;
	private final char newLine = '\n';
	private OutputStream out;
	private final int writeBits = 16;
	private int code;
	private final int maxCode = (int) Math.pow(2, writeBits);
	private TST<Integer> dict = new TST<Integer>(); //Tertiary Search Tree

	// Creates a new output stream with a default buffer size.
	public StringZipOutputStream(OutputStream out) {
		this.out = out;
		buildInitialDict();
	}
	
	/**
	 * @description : Build dictionary to start compression
	 * 
	 */
	private void buildInitialDict() {
		for (int i = 0; i < EOF; i++) {
			dict.insert("" + (char) i, i);
		}
		code = EOF + 1;
	}

	// Writes aStrign compressed output stream. This method will block until all
	// data is written.
	public void write(String aString) {
		aString += newLine;
		while (aString.length() > 0) {
			String prefix = dict.getLongestPrefix(aString);
			write(dict.get(prefix), writeBits);
			int prefixLen = prefix.length();
			if(prefixLen < aString.length() && code < maxCode){
				dict.insert(aString.substring(0,prefixLen + 1), code++);
			}
			aString = aString.substring(prefixLen);
		}
	}

	// Writes remaining data to the output stream and closes the underlying
	// stream.
	public void close() {
		write(EOF,writeBits);
		clearBuffer();
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeBit(boolean bit) {
		// add bit to buffer
		buffer <<= 1;
		if (bit)
			buffer |= 1;

		// if buffer is full (8 bits), write out as a single byte
		n++;
		if (n == 8)
			clearBuffer();
	}

	private void clearBuffer() {
		if (n == 0)
			return;
		if (n > 0)
			buffer <<= (8 - n);
		try {
			out.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		n = 0;
		buffer = 0;
	}

	public void write(int x, int r) {
		if (r < 1 || r > 32)
			throw new IllegalArgumentException("Illegal value for r = " + r);
		if (x < 0 || x >= (1 << r))
			throw new IllegalArgumentException("Illegal " + r + "-bit char = " + x);
		for (int i = 0; i < r; i++) {
			boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
			writeBit(bit);
		}
	}
}
