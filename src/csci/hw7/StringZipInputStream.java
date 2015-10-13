package csci.hw7;

import java.io.IOException;
import java.io.InputStream;
/**
 * 
 * @author Vishal, Daichi
 * 
 * Class to Decompresses a text file that contains ASCII characters
 *
 */
public class StringZipInputStream {

	private int buffer;
	private int n;
	private final int EOF = 256;
	private InputStream in;
	private final int writeBits = 16;
	private int code;
	private String decodedString = null;
	private final int maxCode = (int) Math.pow(2, writeBits);
	private String[] dict = new String[maxCode];

	// Creates a new input stream with a default buffer size.
	public StringZipInputStream(InputStream in) {
		this.in = in;
		buildInitialDict();
		fillBuffer();
	}
	
	private void buildInitialDict() {
		int i = 0;
		for (i = 0; i < EOF; i++)
			dict[i] = "" + (char) i;
		dict[i++] = "";
		code = EOF + 1;
	}
	
	// Reads data into a string. the method will block until some input can be
	// read; otherwise, no bytes are read and null is returned.
	public String read() {
		int codeword = readInt(writeBits);
		if (codeword == EOF)
			return null; // expanded message is empty string

		String s = dict[codeword];
		if (decodedString != null) {
			if (code == codeword)
				s = decodedString + decodedString.charAt(0); // special case
																// hack
			if (code < maxCode && decodedString.indexOf('\n') == -1) //hack to counter readline of compression
				dict[code++] = decodedString + s.charAt(0);
		}
		decodedString = s;
		return decodedString;
	}

	// Closes this input stream and releases any system resources associated
	// with the stream.
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int readInt(int r) {
		if (r < 1 || r > 32)
			throw new IllegalArgumentException("Illegal value of r = " + r);
		int x = 0;
		for (int i = 0; i < r; i++) {
			x <<= 1;
			boolean bit = readBoolean();
			if (bit)
				x |= 1;
		}
		return x;
	}

	private void fillBuffer() {
		try {
			buffer = in.read();
			n = 8;
		} catch (IOException e) {
			System.out.println("EOF");
			buffer = -1;
			n = -1;
		}
	}

	public boolean isEmpty() {
		return buffer == -1;
	}

	public boolean readBoolean() {
		if (isEmpty())
			throw new RuntimeException("Reading from empty input stream");
		n--;
		boolean bit = ((buffer >> n) & 1) == 1;
		if (n == 0)
			fillBuffer();
		return bit;
	}

}