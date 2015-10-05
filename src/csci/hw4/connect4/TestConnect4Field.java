package csci.hw4.connect4;

import java.util.Scanner;

public class TestConnect4Field {

	public static Connect4Field aConnect4Field = new Connect4Field();
	public Player aPlayer = new Player(aConnect4Field, "A", '+');
	public Player bPlayer = new Player(aConnect4Field, "B", '*');

	public void dropTest(int column) {
		System.out
				.println("Can it be dropped in " + column + ": " + aConnect4Field.checkIfPiecedCanBeDroppedIn(column));
	}

	public void testIt() {
	              aConnect4Field = new Connect4Field();
	              System.out.println(aConnect4Field);
	              dropTest(-1);
	              dropTest(0);
	              dropTest(1);
	              aConnect4Field.dropPieces(1, '+');
	              System.out.println(aConnect4Field);
	              aConnect4Field.dropPieces(1, '*');
	              System.out.println(aConnect4Field);
	              aConnect4Field.didLastMoveWin();
	              aConnect4Field.isItaDraw();
	              aConnect4Field.init(aPlayer, bPlayer);
	      }

	public void playMultiplayer(){
		aConnect4Field.init(aPlayer, bPlayer);
	}
	public void playCpu(){
		CpuPlayer cpu = new CpuPlayer(aConnect4Field, "CPU", '*');
		aConnect4Field.init(aPlayer, cpu);
	}
	public static void main(String[] args) {
//		new TestConnect4Field().testIt();
//		aConnect4Field.createBoard();
//		System.out.println(aConnect4Field.toString());

		System.out.println("1. VS Multiplayer \n2. VS Computer   \nEnter 1 or 2");
		try (Scanner sc = new Scanner(System.in)){
			int userInput = sc.nextInt();
			if(userInput == 1){
				new TestConnect4Field().playMultiplayer();
			}
			if(userInput == 2){
				new TestConnect4Field().playCpu();
			}
			else {
				System.out.println("Sorry.. I did't get it. Please select one of the two options");
			}
		}
		
	}
}
