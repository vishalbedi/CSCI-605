package csci.hw4.connect4;

import java.util.InputMismatchException;

public class Player implements PlayerInterface{
	private final int HUMAN_TYPE = 1;
	private final int BOT_TYPE = 2;
	Connect4Field field;
	final String name ;
	final char gamePiece;
	private int type ;
	private final String EMPTY_STATE = "[.\\s]";
	private int lastMove = 0;
	
	public int getLastMove () {
		return lastMove;
	}
	Player (Connect4Field _field, String _name, char _gamePiece){
		field = _field;
		name = _name;
		gamePiece = _gamePiece;
	}
	
	public void setType (int _type){
		type = _type;
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
		if(type == HUMAN_TYPE){
			return getUserMove();
		}
		return 0;
	}
	
	private int getUserMove(){
		System.out.println("Enter column number.. ");
		try {
			return lastMove = field.getScanner().nextInt();
		}catch (InputMismatchException e){
			System.out.println("Something Went wrong.. try again");
			return -1;
		}
	}
	
	private int getBotMove(){
		if(field.toString().matches(EMPTY_STATE)){
			int min = (field.getMinPlayableCols(field.getRows()));
			int max = (field.getMaxPlayableCols(field.getRows()));
			int random = min + (int)(Math.random() * ((max - min) + 1));
			return random;
		}
		return 0;
	}
}
