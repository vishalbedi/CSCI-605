package csci.hw4.connect4;

import java.util.Scanner;

public class Player implements PlayerInterface{
	Connect4FieldInterface field;
	final String name ;
	final char gamePiece;
//	private final String EMPTY_STATE;

	
	boolean preEmpty = false;
	int preCol = -0;
	
	Player (Connect4FieldInterface _field, String _name, char _gamePiece){
		field = _field;
		name = _name;
		gamePiece = _gamePiece;
	}
	

	@Override
	public char getGamePiece() {
		return gamePiece;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int nextMove() {
		return getUserMove();
	}
	
	private int getUserMove(){
		System.out.println("Enter column number.. ");
		int lastMove = -1;
		try (Scanner sc = new Scanner(System.in)) {
			lastMove = sc.nextInt();
		}
		return lastMove;
	}
}
