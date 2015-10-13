package csci.hw7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
// import java.util.zip.StringZipInputStream;
// import java.util.zip.StringZipOutputStream;
import java.io.FileWriter;

public class MyCompressTest {
	final int MAX = 1024;
	private String dir = System.getProperty("user.dir");
	String inputFileName = dir + "\\src\\csci\\hw7\\words.txt";
	String outputFileName = dir + "\\src\\csci\\hw7\\words.compress";
	String uncompressed = dir + "\\src\\csci\\hw7\\words.uncompress.txt";

	void compress() {
		try {
			String aWord;

			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			StringZipOutputStream aStringZipOutputStream = new StringZipOutputStream(
					new FileOutputStream(outputFileName));

			while ((aWord = input.readLine()) != null) {
				System.out.println("write:	" + aWord);
				aStringZipOutputStream.write(aWord);
			}
			aStringZipOutputStream.close();
			input.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	void unCompress() {
		try {
			String aWord;
			byte[] buffer = new byte[MAX];

			BufferedWriter uncompress = new BufferedWriter(new FileWriter(uncompressed));
			StringZipInputStream aStringZipInputStream = new StringZipInputStream(new FileInputStream(outputFileName));
			String theWord;

			while ((theWord = aStringZipInputStream.read()) != null) {
				uncompress.write(theWord, 0, theWord.length());
				 System.out.print(theWord);
			}
			aStringZipInputStream.close();
			uncompress.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String args[]) {
		MyCompressTest aMyCompressTest = new MyCompressTest();
		aMyCompressTest.compress();
		aMyCompressTest.unCompress();
	}
}