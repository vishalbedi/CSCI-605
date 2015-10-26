package csci.hw9.example2;

public class ZeroOne extends Thread	{
	private String info;
	static Object o = new Object();
	static long nextId = -10;
	static boolean oneIsRunning = false;
	
	public ZeroOne (String info) {
		this.info    = info;
	}
	public void run () {
		while ( true )	{
			synchronized ( o ) {
				try {
					if ( nextId == -10)	{
						nextId = Long.parseLong(this.info);
						System.out.print(info);
						for (int i = 1; i < 99; i++)
						( new ZeroOne(""+i) ).start();
					}
					if(nextId + 1 == Long.parseLong(this.info)){
						System.out.print(info);
						nextId = nextId == 97 ? -1 : Long.parseLong(this.info);
						o.notifyAll();
					}
					sleep(3);
					o.wait();
				} catch ( Exception e ) { }
			}
		}
	}
	public static void main (String args []) {
		new ZeroOne("0").start();
	}
}