package csci.hw2;


import java.util.Scanner;
import java.util.ArrayList;

public class Subsets2 {
	static int i = 0;
	static String combinats[];
	static String comb[];
	static int c = 0;
	static int people;
	static int l = 1;
	private static Scanner scan;
	
	public static void main( String[] args ) {
		//String[] combinationsFormed;
		System.out.println( "Enter the number of people attending the party: " );
		
		scan = new Scanner( System.in );
		people = scan.nextInt();

		combinats = new String[(int) Math.pow(2, people)];
		System.out.println(Subsets2.combinations(people));
	}
	
	static String combinations(int n) {
		if (n == 0) {
			return "{}";
		} else {
			return calcSubsets(combinations(n-1), n);
		}
	}
	
	
	static String calcSubsets(String x, int n) {
		ArrayList<String> values = 	readSet(x);
		String result = "{";
		for(String s : values){
			result += " {" + s + "}";
		}
		for(String s : values){
			result += " {" + s + n + "}";
		}
		result += " }";
		return result;
	}
	static ArrayList<String> readSet(String set){
		ArrayList<String> result = new ArrayList<String>();
		String tempString = "";
		boolean isLastBraces = false;
		String setNoSpace = set.replaceAll("\\s+","");
		int setLen = setNoSpace.length();
		for (i=0 ; i < setLen; i++){
			char c = setNoSpace.charAt(i);{
				if(c == '{' || c == '}'){
					if(c == '{'){
						continue;
					}
					if(c == '}' ){
						if(!isLastBraces)
							result.add(tempString);
						tempString = "";
						isLastBraces = true;
						continue;
					}
				}
				tempString += c;
				isLastBraces = false;
			}
		}
		return result;

	}
	static void Subset(int x) {
		//int num = (1<<x);
		
		//for(int i =1;i<num;i++){
			for(int j=0;j<x;j++){
				if(((1 << j)) >0)
					System.out.print((j+1)+"");
			}
		System.out.println();
			
	}
	
	static void getSet(String x) {
		for (int j = 0; j<people+1; j++) {
			combinats[i++] = x + j;
			System.out.println(x+j);
		}
	}
}