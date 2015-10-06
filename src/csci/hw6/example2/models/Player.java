package csci.hw6.example2.models;

import java.util.Scanner;

public class Player implements PlayerInterface{
	Connect4FieldInterface field;
	final String name ;
	final char gamePiece;
	Scanner sc = new Scanner(System.in);

	public Player (Connect4FieldInterface _field, String _name, char _gamePiece){
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
		return 0;
	}
	

}
