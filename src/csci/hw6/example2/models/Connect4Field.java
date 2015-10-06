package csci.hw6.example2.models;

/**
 * @author Vishal, Daichi Mae
 *
 */
public class Connect4Field implements Connect4FieldInterface {

	private int CONNECT4_FIELD_ROW = 9;
	private int CONNECT4_FIELD_COL = 25;
	private final String UNDEFINED_STATE = "!";
	private final String EMPTY_STATE = "0";
	private String[][] board = new String[CONNECT4_FIELD_ROW][CONNECT4_FIELD_COL];
	private PlayerInterface[] thePlayers = new PlayerInterface[2];
	
	
	public Connect4Field(){
		board = fillCols(board);
	}
	
	public PlayerInterface[] getPlayers(){
		return this.thePlayers;
	}
	
	public String getEmptyState(){
		return this.EMPTY_STATE;
	}
	
	public String[][] getBoard() {
		return board;
	}
	
	public int getRows() {
		return CONNECT4_FIELD_ROW;
	}
	
	public int getCols() {
		return CONNECT4_FIELD_COL;
	}
	
	public String[][] fillCols(String[][] _board) {
		for (int i = 0; i < CONNECT4_FIELD_ROW; i++) {
			for (int j = 0; j < CONNECT4_FIELD_COL; j++) {
				if(j >= i && j < CONNECT4_FIELD_COL - i){
					_board[i][j] = EMPTY_STATE;
				}else {
					_board[i][j] = UNDEFINED_STATE;
				}
			}
		}
		return _board;
	}

	public String toString() {
		String brd = "";
		char newLine = '\n';
		char space = ' ';
		for (String[] rows : board) {
			for (int i = 0; i < rows.length; i++) {
				if (!rows[i].equals(UNDEFINED_STATE))
					brd += rows[i];
				else
					brd += space;
			}
			brd += newLine;
		}
		return brd;
	}

	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		for (String[] str : board) {
			if (column >= 0 && column < CONNECT4_FIELD_COL &&  str[column].equals(EMPTY_STATE)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void dropPieces(int column, char gamePiece) {
		if(!checkIfPiecedCanBeDroppedIn(column)){
			System.out.println("please try again");
			System.out.println(this);
			return;
		}
		int rowNumber = getRow(column);
		board[rowNumber][column] = ""+gamePiece;
	}

	public int getRow(int col) {
		int rowNumber = -1;
		for (String[] row : board) {
			if (row[col] == EMPTY_STATE) {
				rowNumber++;
				continue;
			}
			break;
		}
		return rowNumber;
	}

	@Override
	public boolean didLastMoveWin() {
		//rows
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				if(!p(i,j).equals("!") && !p(i,j).equals(EMPTY_STATE) && p(i,j).equals(p(i,j+1)) && 
						p(i,j).equals(p(i,j+2)) && p(i,j).equals(p(i,j+3))){
					return true;
				}
			}
		}
		//Cols
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				if(!p(i,j).equals("!") && !p(i,j).equals(EMPTY_STATE)&& p(i,j).equals(p(i+1,j)) && 
						p(i,j).equals(p(i+2,j)) && p(i,j).equals(p(i+3,j))){
					return true;
				}
			}
		}
		
		//diagonal
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				for (int d =-1;d<=1;d+=2){
					if(!p(i,j).equals("!") && !p(i,j).equals(EMPTY_STATE)&& p(i,j).equals(p(i+1*d,j+1)) && 
							p(i,j).equals(p(i+2*d,j+2)) && p(i,j).equals(p(i+3*d,j+3))){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isItaDraw() {
		return this.toString().indexOf("0") == -1;
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		thePlayers[0] = playerA;
		thePlayers[1] = playerB;
	}

	@Override
	public void playTheGame() {
		int column;
		boolean gameIsOver = false;
		do {
			for (int i  = 0; i< 2; i++) {
				System.out.println(this);
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
					break;
				} else {
					column = thePlayers[i].nextMove();
					dropPieces(column, thePlayers[i].getGamePiece());
					if (didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: " + thePlayers[i].getName());
						System.out.println(this);
						break;
					}
				}
			}
		} while (!gameIsOver);
	}
	
	private String p(int row, int col){
		return (row < 0 || col < 0 || 
				row >= CONNECT4_FIELD_ROW ||
				col >= CONNECT4_FIELD_COL) ? "!" : board[row][col];
	}	
}

/**
 * ooooooooooooooooooooooooo 
 *  ooooooooooooooooooooooo 
 *   ooooooooooooooooooooo
 *    ooooooooooooooooooo 
 *     ooooooooooooooooo
 *      ooooooooooooooo
 *       ooooooooooooo
 *        ooooooooooo
 *         ooooooooo
 */