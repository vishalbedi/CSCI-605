/**
 * 
 */
package csci.hw4.connect4;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Vishal
 *
 */
public class Connect4Field implements Connect4FieldInterface {

	private int CONNECT4_FIELD_ROW = 9;
	private int CONNECT4_FIELD_COL = 25;
	private final String UNDEFINED_STATE = "!";
	private final String EMPTY_STATE = "0";
	private Scanner sc = new Scanner(System.in);
	private String[][] board;
	private PlayerInterface[] thePlayers = new PlayerInterface[2];
	
	public Connect4Field(){
		createBoard();
	}
	public String getEmptyState(){
		return this.EMPTY_STATE;
	}

	public String[][] getBoard() {
		return board;
	}

	public Scanner getScanner() {
		return sc;
	}

	public int getRows() {
		return CONNECT4_FIELD_ROW;
	}

	public int getCols() {
		return CONNECT4_FIELD_COL;
	}

	public int getMinPlayableCols(int row) {
		return row;
	}

	public int getMaxPlayableCols(int row) {
		return CONNECT4_FIELD_COL - row;
	}

	public void createBoard() {
		System.out.println("Do you want to play with customized board ? (y/n)");
		try {
			char input =  sc.next().toLowerCase().charAt(0);
			if (input =='y') {
				System.out.println("Enter Rows");
				CONNECT4_FIELD_ROW = sc.nextInt();
				System.out.println("Enter Cols");
				CONNECT4_FIELD_COL = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			System.out.println("Something went wrong");
			System.out.println("Lets Try again");
			sc.next();
			createBoard();
		}
		board = new String[CONNECT4_FIELD_ROW][CONNECT4_FIELD_COL];
		board = fillCols(board);
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
		for (String[] col : board) {
			for (int i = 0; i < col.length; i++) {
				if (col[i].equals(EMPTY_STATE))
					brd += col[i];
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
			if (str[column].equals(EMPTY_STATE)) {
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
				if(!p(i,j).equals("!") && p(i,j).equals(p(i,j+1)) && 
						p(i,j).equals(p(i,j+2)) && p(i,j).equals(p(i,j+3))){
					return true;
				}
			}
		}
		//Cols
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				if(!p(i,j).equals("!") && p(i,j).equals(p(i+1,j)) && 
						p(i,j).equals(p(i+2,j)) && p(i,j).equals(p(i+3,j))){
					return true;
				}
			}
		}
		
		//diagonal
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				for (int d =-1;d<=1;d+=2){
					if(!p(i,j).equals("!") && p(i,j).equals(p(i+1*d,j+1)) && 
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
		return board.toString().indexOf("0") == -1;
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		int firstChance = new Random().nextInt(2);
		thePlayers[0] = playerA;
		thePlayers[1] = playerB;

		playTheGame();
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

/*
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