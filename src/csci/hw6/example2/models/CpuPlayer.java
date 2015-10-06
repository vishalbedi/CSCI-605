package csci.hw6.example2.models;


public class CpuPlayer implements PlayerInterface {
	private final Connect4FieldInterface field;
	private final String name;
	private final char gamePiece;
	private int CONNECT4_FIELD_ROW = 9;
	private int CONNECT4_FIELD_COL = 25;
	private String[] board;

	private int nConnect = 0;
	private int nextMove = 0;

	public CpuPlayer(Connect4FieldInterface field, String name, char symbol) {
		this.field = field;
		this.name = name;
		this.gamePiece = symbol;
		board = getBoard();
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
		board = getBoard();
		checkIfWin();
		return nextMove;
	}

	private String[] getBoard(){
		String stringBoard = field.toString();
		String[] rows = stringBoard.split("\n");
		return rows;
	}
	
	private void checkIfWin() {
		// rows
		for (int i = 0; i < CONNECT4_FIELD_ROW; i++) {
			for (int j = 0; j < CONNECT4_FIELD_COL; j++) {
				if (!p(i, j).equals(" ")  && !p(i,j).equals("0") && p(i, j).equals(p(i, j + 1)) && p(i, j).equals(p(i, j + 2))) {
					if(p(i,j-1).equals("0") && !p(i+1,j-1).equals("0")){
						nConnect = 3;
						nextMove = j-1;
					}
					if(p(i,j+3).equals("0") && !p(i+1,j + 3).equals("0")){
						nConnect = 3;
						nextMove = j+3;
					}					
				}
				
				if (!p(i, j).equals(" ") && !p(i,j).equals("0") && p(i, j).equals(p(i, j + 1)) && nConnect < 3) {
					if(p(i,j-1).equals("0") && !p(i+1,j-1).equals("0")){
						nConnect = 2;
						nextMove = j-1;
					}
					if(p(i,j+2).equals("0") && !p(i+1,j + 2).equals("0")){
						nConnect = 2;
						nextMove = j+2;
					}					
				}
			}
		}
		// Cols
		for (int i = 0; i < CONNECT4_FIELD_ROW; i++) {
			for (int j = 0; j < CONNECT4_FIELD_COL; j++) {
				if (!p(i, j).equals(" ") && !p(i,j).equals("0") && p(i, j).equals(p(i + 1, j)) && p(i, j).equals(p(i + 2, j))) {
					if(p(i-1,j).equals("0")){
						nConnect = 3;
						nextMove = j;
					}
				}
				if (!p(i, j).equals(" ") && p(i, j).equals(p(i + 1, j)) && nConnect < 3) {
					if(p(i-1,j).equals("0")){
						nConnect = 2;
						nextMove = j;
					}
				}
			}
		}

		// diagonal
		for (int i = 0; i < CONNECT4_FIELD_ROW; i++) {
			for (int j = 0; j < CONNECT4_FIELD_COL; j++) {
				for (int d = -1; d <= 1; d += 2) {
					if (!p(i, j).equals(" ") && !p(i,j).equals("0") && p(i, j).equals(p(i + 1 * d, j + 1)) && p(i, j).equals(p(i + 2 * d, j + 2))) {
						if(p(i - 1 * d, j -1).equals("0") && !p(i , j -1 * d).equals("0")){
							nConnect = 3;
							nextMove = j-1;
						}
						if(p(i + 3 * d, j + 3).equals("0") && !p(i + 4,j + 3 * d).equals("0")){
							nConnect = 3;
							nextMove = j+3;
						}	
					}
					
					if (!p(i, j).equals(" ") && !p(i,j).equals("0") && p(i, j).equals(p(i + 1 * d, j + 1)) && nConnect < 3 ) {
						if(p(i - 1 * d, j -1).equals("0") && !p(i, j -1 * d).equals("0")){
							nConnect = 2;
							nextMove = j-1;
						}
						if(p(i + 2 * d, j + 2).equals("0") && !p(i + 3,j + 2*d).equals("0")){
							nConnect = 2;
							nextMove = j+2;
						}	
					}
				}
			}
		}
		
		if(nConnect <= 1){
			nextMove = 8 + (int)(Math.random() * ((17 - 8) + 1));
		}
	}
	
	private String p(int row, int col){
		return (row < 0 || col < 0 || 
				row >= CONNECT4_FIELD_ROW ||
				col >= CONNECT4_FIELD_COL) ? " " : ""+board[row].charAt(col);
	}
}
