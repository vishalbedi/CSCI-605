package csci.hw4.connect4;

import java.util.ArrayList;

public class CpuPlayer implements PlayerInterface{
	private final Connect4FieldInterface field;
	private final String name;
	private final char gamePiece;
	
	private final ArrayList<String> threats = new ArrayList<String>();
	private int nConnect = 0;
	
	public CpuPlayer(Connect4FieldInterface field, String name, char symbol) {
		this.field = field;
		this.name = name;
		this.gamePiece = symbol;
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
