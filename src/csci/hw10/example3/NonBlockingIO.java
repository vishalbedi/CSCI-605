package csci.hw10.example3;

public class NonBlockingIO {
	public static void main(String args[]) {
		BufferStore bs = new BufferStore(10*1024);
		String dir = System.getProperty("user.dir");
		String inputFileName = dir + "\\src\\csci\\hw10\\example3\\words.txt";
		
		BufferWrite bw = new BufferWrite(bs, inputFileName);
		BufferRead br = new BufferRead(bs);
		
		Thread t1 = new Thread(bw);
		t1.start();
		Thread t2 = new Thread(br);
		t2.start();
	}
}
