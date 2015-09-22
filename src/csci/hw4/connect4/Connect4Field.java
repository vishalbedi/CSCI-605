/**
 * 
 */
package csci.hw4.connect4;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Connect4Field class
 * Backbone of connect4 game.
 * Initializes the players and starts the game
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */

public class Connect4Field implements Connect4FieldInterface {

	private int CONNECT4_FIELD_ROW = 9;
	private int CONNECT4_FIELD_COL = 25;
	private final char UNDEFINED_STATE = '!';
	private final char EMPTY_STATE = '.';
	private Scanner sc = new Scanner(System.in);
	private String[] board;
	private Player[] thePlayers = new Player[2];
	private int currentPlayerIndex = 0;
	private int lastMoveRow[] = new int[2];
	private int lastMoveCol[] = new int[2] ;
	private char[] gamePieceArray = new char[2];
	
	/**
	 * @description : initializes the board
	 */
	public Connect4Field(){
		board = new String[CONNECT4_FIELD_ROW];
		board = fillCols(board);
	}
	
	/**
	 * @description Gets the last move of player
	 * @param int id 
	 * @return int lastMove
	 */
	public int getLastMoveRow(int id){
		return lastMoveRow[id];
	}
	
	public char getGamePiece(int id){
		return gamePieceArray[id];
	}
	
	public int getLastMoveCol(int id){
		return lastMoveCol[id];
	}
	
	public char getEmptyState(){
		return this.EMPTY_STATE;
	}

	public String[] getBoard() {
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
		board = new String[CONNECT4_FIELD_ROW];
		board = fillCols(board);
	}

	/**
	 * @description Fill the cols of the board to empty state
	 * 
	 */
	public String[] fillCols(String[] _board) {
		String nullState = "";
		for (int i = 0; i < CONNECT4_FIELD_ROW; i++) {
			_board[i] = nullState;
			for (int j = 0; j < CONNECT4_FIELD_COL - 2 * i; j++) {
				_board[i] += EMPTY_STATE;
			}
			_board[i] += nullState;
			nullState += UNDEFINED_STATE;
		}
		return _board;
	}

	/**
	 * @description return the board instead of object address
	 * 
	 */
	@Override
	public String toString() {
		String brd = "";
		char newLine = '\n';
		char space = ' ';
		for (String row : board) {
			for (int i = 0; i < row.length(); i++) {
				if (row.charAt(i) != UNDEFINED_STATE)
					brd += row.charAt(i);
				else
					brd += space;
			}
			brd += newLine;
		}
		return brd;
	}

	/**
	 * @description check if the piece can be dropped in the col
	 * @param int col
	 */
	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		for (String str : board) {
			if (column >= 0 && column < CONNECT4_FIELD_COL && str.charAt(column) == EMPTY_STATE) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @description creates player objects to play the game
	 */
	private void assignPlayers() {
		System.out.println("1. VS Multiplayer \n2. VS Computer   \nEnter 1 or 2");
		try {
			int userInput = sc.nextInt();
			if(userInput == 1){
				setupPlayers(2);
			}
			if(userInput == 2){
				setupPlayers(1);
				thePlayers[1] = new Player(this, "DragonSlayer", '*');
				thePlayers[1].setId(1);
				thePlayers[1].setType(2);
				gamePieceArray[1] = '*';
			}
			else {
				System.out.println("Sorry.. I did't get it. Please select one of the two options");
				assignPlayers();
			}
		} catch (InputMismatchException e) {
			System.err.println("Something went wrong. Try again");
			sc.next();
			assignPlayers();
		}
	}
	/**
	 * @description sets the players to pay the game
	 */
	private void setupPlayers(int playerCount) {
		for (int i = 0; i < playerCount; i++) {
			System.out.println("Enter Player Name.");
			System.out.print("Player " + (i + 1) + ": ");
			System.out.println();
			String name = sc.next();
			System.out.print("Enter Symbol : ");
			char symbol = sc.next().charAt(0);
			thePlayers[i] = new Player(this, name, symbol);
			thePlayers[i].setId(i);
			thePlayers[i].setType(1);
			gamePieceArray[i] = symbol;
		}
	}

	/**
	 * @description drops the game piece in the col
	 * @param int column 
	 * @param char gamePiece
	 */
	@Override
	public void dropPieces(int column, char gamePiece) {
		// TODO Auto-generated method stub
		if(!checkIfPiecedCanBeDroppedIn(column)){
			System.out.println("please try again");
			System.out.println(this);
			return;
		}
		int rowNumber = getRow(column);
		lastMoveRow[currentPlayerIndex] = rowNumber;
		lastMoveCol[currentPlayerIndex] = column;
		char[] row = board[rowNumber].toCharArray();
		row[column] = gamePiece;
		String newRow = String.valueOf(row);
		board[rowNumber] = newRow;
	}

	public int getRow(int col) {
		int rowNumber = -1;
		for (String row : board) {
			if (row.charAt(col) == EMPTY_STATE) {
				rowNumber++;
				continue;
			}
			break;
		}
		return rowNumber;
	}
	
	/**
	 * @description Check if any player won
	 */
	
	@Override
	public boolean didLastMoveWin() {
		//rows
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				if(!p(i,j).equals("!") && !p(i,j).equals(".") && p(i,j).equals(p(i,j+1)) && 
						p(i,j).equals(p(i,j+2)) && p(i,j).equals(p(i,j+3))){
					return true;
				}
			}
		}
		
		//Cols
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				if(!p(i,j).equals("!") && !p(i,j).equals(".") && p(i,j).equals(p(i+1,j)) && 
						p(i,j).equals(p(i+2,j)) && p(i,j).equals(p(i+3,j))){
					return true;
				}
			}
		}
		
		//diagonal
		for(int i =0; i< CONNECT4_FIELD_ROW; i++){ 
			for(int j = 0; j < CONNECT4_FIELD_COL; j++){
				for (int d =-1;d<=1;d+=2){
					if(!p(i,j).equals("!") && !p(i,j).equals(".") && p(i,j).equals(p(i+1*d,j+1)) && 
							p(i,j).equals(p(i+2*d,j+2)) && p(i,j).equals(p(i+3*d,j+3))){
						return true;
					}
				}
			}
		}
		return false;
	}
	/**
	 * @description Boundary check for board returns character at the given row and col
	 * @param row
	 * @param col
	 * @return char 
	 */
	private String p(int row, int col){
		return (row < 0 || col < 0 || 
				row >= CONNECT4_FIELD_ROW ||
				col >= CONNECT4_FIELD_COL) ? "!" : ""+board[row].charAt(col);
	}

	@Override
	public boolean isItaDraw() {
		return board.toString().indexOf(EMPTY_STATE) == -1;
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		// TODO Auto-generated method stub

	}

	/**
	 * @description game loop
	 */
	@Override
	public void playTheGame() {
		int column;
		boolean gameIsOver = false;
		assignPlayers();
		do {
			for (currentPlayerIndex = 0; currentPlayerIndex < 2; currentPlayerIndex++) {
				System.out.println(this);
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
					break;
				} else {
					column = thePlayers[currentPlayerIndex].nextMove();
					dropPieces(column, thePlayers[currentPlayerIndex].getGamePiece());
					if (didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: " + thePlayers[currentPlayerIndex].getName());
						System.out.println(this);
						break;
					}
				}
			}
		} while (!gameIsOver);

	}
	
	/**
	 * @description returns horizontal row of the placed character
	 */
	public String getHorizontal(int row, int col) {
		return board[row];
	}
	

	/**
	 * @description returns vertical row of the placed character
	 */
	public String getVertical(int row, int col) {
		String vertical = "";
		for (String s : board) {
			vertical += s.charAt(col);
		}
		return vertical;
	}

	/**
	 * @description returns diagonal row of the placed character
	 */
	public String getDiagonal(int row, int col) {
		int tempRow = CONNECT4_FIELD_ROW - row -1;
		int offset = tempRow >= col ? col : tempRow;
		int initialRow = row + offset;
		int initialCol = col - offset;
		String diagonal = "";
		String[] board = this.board;
		for (int i = initialRow, j = initialCol; i >= 0; i--) {
					diagonal += board[i].charAt(j);
					j++;
		}
		return diagonal;
	}

	/**
	 * @description returns antiDiagonal row of the placed character
	 */
	public String getAntiDiagonal(int row, int col) {
		int offset = row >= col ? col : row;
		int initialRow = row - offset;
		int initialCol = col - offset;
		String antiDiagonal = "";
		String[] board = this.board;
		for (int i = initialRow, j = initialCol; i < CONNECT4_FIELD_ROW; i++) {
					antiDiagonal += board[i].charAt(j);
					j++;
		}
		return antiDiagonal;		
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