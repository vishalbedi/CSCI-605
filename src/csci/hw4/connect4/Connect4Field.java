/**
 * 
 */
package csci.hw4.connect4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Vishal
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
	private int lastMoveRow = 0;
	private int lastMoveCol = 0;

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
			if (sc.next().toLowerCase().charAt(0) == 'y') {
				System.out.println("Enter Rows");
				CONNECT4_FIELD_ROW = sc.nextInt();
				System.out.println("Enter Cols");
				CONNECT4_FIELD_COL = sc.nextInt();
			}
		} catch (InputMismatchException e) {
			System.out.println("Something went wrong");
			System.out.println("Lets Try again");
			createBoard();
		}
		board = new String[CONNECT4_FIELD_ROW];
		board = fillCols(board);
	}

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

	@Override
	public boolean checkIfPiecedCanBeDroppedIn(int column) {
		for (String str : board) {
			if (str.charAt(column) == EMPTY_STATE) {
				return true;
			}
		}
		return false;
	}

	private void assignPlayers() {
		System.out.println("1. VS Computer \n 2. Multiplayer \n Enter 1 or 2");
		try {
			int userInput = sc.nextInt();
			if (userInput == 1) {
				setupPlayers(userInput);
				// TODO : create a bot
			}
			if (userInput == 2) {
				setupPlayers(userInput);
			} else {
				System.out.println("Sorry.. I did't get it. Please select one of the two options");
				assignPlayers();
			}
		} catch (InputMismatchException e) {
			System.err.println("Something went wrong. Try again");
			assignPlayers();
		}
	}

	private void setupPlayers(int playerCount) {
		for (int i = 0; i < playerCount; i++) {
			System.out.println("Enter Player Name.");
			System.out.print("Player " + (i + 1) + ": ");
			System.out.println();
			String name = sc.next();
			System.out.print("Enter Symbol : ");
			char symbol = sc.next().charAt(0);
			thePlayers[i] = new Player(this, name, symbol);
			thePlayers[i].setType(1);
		}
	}

	@Override
	public void dropPieces(int column, char gamePiece) {
		// TODO Auto-generated method stub
		if(!checkIfPiecedCanBeDroppedIn(column)){
			System.out.println("please try again");
			System.out.println(this);
			return;
		}
		int rowNumber = getRow(column);
		lastMoveRow = rowNumber;
		lastMoveCol = column;
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

	@Override
	public boolean didLastMoveWin() {
		char gamePiece = thePlayers[currentPlayerIndex].getGamePiece();
		int col = lastMoveCol;
		int row = lastMoveRow;

		return (checkConnect4(getHorizontal(row, col), gamePiece) || 
				checkConnect4(getVertical(row, col), gamePiece) || 
				checkConnect4(getDiagonal(row, col), gamePiece) || 
				checkConnect4(getAntiDiagonal(row, col), gamePiece));
	}

	@Override
	public boolean isItaDraw() {
		return board.toString().indexOf(EMPTY_STATE) == -1;
	}

	@Override
	public void init(PlayerInterface playerA, PlayerInterface playerB) {
		// TODO Auto-generated method stub

	}

	@Override
	public void playTheGame() {
		int column;
		System.out.println(this);
		boolean gameIsOver = false;
		assignPlayers();
		do {
			for (currentPlayerIndex = 0; currentPlayerIndex < 2; currentPlayerIndex++) {
				System.out.println(this);
				if (isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
				} else {
					column = thePlayers[currentPlayerIndex].nextMove();
					dropPieces(column, thePlayers[currentPlayerIndex].getGamePiece());
					if (didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: " + thePlayers[currentPlayerIndex].getName());
					}
				}
			}
		} while (!gameIsOver);

	}

	private boolean checkConnect4(String str, char gamePiece) {
		return str.matches(gamePiece + "{4}");
	}

	private String getHorizontal(int row, int col) {
		// forward check
		return board[row];
	}

	private String getVertical(int row, int col) {
		String vertical = "";
		for (String s : board) {
			vertical += s.charAt(col);
		}
		return vertical;
	}

	private String getDiagonal(int row, int col) {
		int tempRow = CONNECT4_FIELD_ROW - row -1;
		int initialRow = 0;
		int initialCol = 0;
		if (tempRow >= col) {
			initialRow = row + col;
			initialCol = col - col;
		} else {
			initialRow = row + tempRow;
			initialCol = col - tempRow;
		}
		String diagonal = "";
		String[] board = this.board;
		for (int i = initialRow, j = initialCol; i >= 0; i--) {
					diagonal += board[i].charAt(j);
					j++;
		}
		return diagonal;
	}

	private String getAntiDiagonal(int row, int col) {
		int tempRow =  row;
		int tempCol =  col;
		int initialRow = 0;
		int initialCol = 0;
		if (tempRow >= tempCol) {
			initialRow = row - tempCol;
			initialCol = col - tempCol;
		} else {
			initialRow = row - tempRow;
			initialCol = col - tempRow;
		}
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