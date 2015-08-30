package csci.hw1;

/**
 * Classical Hello World
 * 
 * @author Vishal
 *
 */
public class OSName {
	/**
	 * @description : Fetches OS name and prints
	 * 
	 * @return null
	 */
	private static void printName() {
		String osName = System.getProperty("os.name") ;
		if(osName.isEmpty()){
			System.out.println("Java was not able to get OS name");
		}else{
			System.out.println("OS : " + osName);
		}
	}
	
	public static void main (String args[]){
		printName();
	}

}
