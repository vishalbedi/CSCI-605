package csci.hw4.connect4;

import java.util.InputMismatchException;
/**
 * PLayer class
 * 
 * Class for playing connect4 game
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */
public class Player implements PlayerInterface{
	private final int HUMAN_TYPE = 1;
	private final int BOT_TYPE = 2;
	Connect4Field field;
	final String name ;
	final char gamePiece;
	private int type ;
	private int id;
	private final String EMPTY_STATE;
	private int nInLine = 0;
	private int nextCol = -1;
	
	boolean preEmpty = false;
	int preCol = -0;
	
	public void setId(int id){
		this.id = id;
	}
	Player (Connect4Field _field, String _name, char _gamePiece){
		field = _field;
		name = _name;
		gamePiece = _gamePiece;
		EMPTY_STATE =  "["+field.getEmptyState()+"\\s]";
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
	/**
	 * @description Get the move to play the game
	 */
	@Override
	public int nextMove() {
		if(type == HUMAN_TYPE){
			return getUserMove();
		} if( type == BOT_TYPE){
			return getBotMove();
		}
		return 0;
	}
	/**
	 * @description Get the move of user 
	 */
	private int getUserMove(){
		System.out.println("Enter column number.. ");
		int lastMove = -1;
		try {
			lastMove = field.getScanner().nextInt();
		}catch (InputMismatchException e){
			System.out.println("Something Went wrong.. try again");
			field.getScanner().next();
			getUserMove();
		}
		return lastMove;
	}
	/**
	 * @description Get the move of CPU
	 */
	private int getBotMove(){
		if(field.toString().indexOf(gamePiece) == -1){
			int min = (field.getMinPlayableCols(field.getRows()));
			int max = (field.getMaxPlayableCols(field.getRows()));
			int random = min + (int)(Math.random() * ((max - min) + 1));
			return random;
		} else {
			int opponentId = 1 - id;
			int row = field.getLastMoveRow(id);
			int col = field.getLastMoveCol(id);
			String horizontal = field.getHorizontal(row, col);
			String vertical = field.getVertical(row, col);
			String diagonal = field.getDiagonal(row, col);
			String antiDiagonal = field.getAntiDiagonal(row, col);
			
			checkHorizontal(horizontal, id);
			checkVertical(vertical, id);
			checkDiagonal(diagonal, id);
			checkAntiDiagonal(antiDiagonal, id);
			int myNInLine = nInLine;
			int myNext = nextCol;
			
			nInLine = 0;
			nextCol = 0;
			
			
			int oppRow = field.getLastMoveRow(opponentId);
			int oppCol = field.getLastMoveCol(opponentId);
			String oppHorizontal = field.getHorizontal(oppRow, oppCol);
			String oppVertical = field.getVertical(oppRow, oppCol);
			String oppDiagonal = field.getDiagonal(oppRow, oppCol);
			String oppAntiDiagonal = field.getAntiDiagonal(oppRow, oppCol);
			
			checkHorizontal(oppHorizontal, opponentId);
			checkVertical(oppVertical, opponentId);
			checkDiagonal(oppDiagonal, opponentId);
			checkAntiDiagonal(oppAntiDiagonal, opponentId);
			
			if(myNInLine > 2){
				return myNext;
			}
			if(nInLine > 2){
				return nextCol;
			}
			return myNext;
		}
	}
	
	private void checkHorizontal (String horizontal, int id){
		int localComparator = 0;
		int index = 0;
		while(index < horizontal.length()){
			if(horizontal.charAt(index) == field.getGamePiece(id)){
				localComparator++;
				if(index <horizontal.length()-1 && horizontal.charAt(index + 1) == field.getEmptyState()){
					if(localComparator > nInLine){
						nextCol = index + 1;
						nInLine = localComparator;
						localComparator = 0;
					}
				}
				if(index > 0 && horizontal.charAt(index-1) == field.getEmptyState()){
					preEmpty = true;
					preCol = index - 1;
				}
			}
			if(horizontal.charAt(index) == field.getGamePiece(1-id)){
				if(localComparator > nInLine && preEmpty){
					nInLine = localComparator;
					preEmpty = false;
					nextCol = preCol;
				}
				localComparator = 0;
			}
			index++;
		}
		if(localComparator > nInLine && preEmpty){
			nInLine = localComparator;
			preEmpty = false;
			nextCol = preCol;
		}
	}
	
	private void checkVertical(String vertical, int id){
		int localComparator = 0;
		int col = field.getLastMoveCol(id);
		int index = vertical.length()-1;
		while(index >= 0){
			if(vertical.charAt(index) == field.getGamePiece(id)){
				localComparator++;
				if(index < vertical.length()-2 && vertical.charAt(index - 1) == field.getEmptyState()){
					if(localComparator > nInLine){
						nextCol = col;
						nInLine = localComparator;
						localComparator = 0;
					}
				}
				
			}
			if(vertical.charAt(index) == field.getGamePiece(1-id)){
				localComparator = 0;
			}
			index--;
		}
	}
	
	private void checkDiagonal(String diagonal, int id){
		int localComparator = 0;
		int col = field.getLastMoveCol(id);
		int row = field.getLastMoveRow(id);
		int index = 0;
		int tempRow = field.getRows() - row -1;
		int offset = tempRow >= col ? col : tempRow;
		int initialCol = col - offset;
		localComparator = 0;
		while(index < diagonal.length()){
			if(diagonal.charAt(index) == field.getGamePiece(id)){
				localComparator++;
				if(index < diagonal.length()-1 && diagonal.charAt(index + 1) == field.getEmptyState()){
					if(localComparator > nInLine){
						nextCol = initialCol + index + 1;
						nInLine = localComparator;
						localComparator = 0;
					}
				}
				if(index > 0 && diagonal.charAt(index-1) == field.getEmptyState()){
					preEmpty = true;
					preCol = initialCol + index - 1;
				}
			}
			if(diagonal.charAt(index) == field.getGamePiece(1-id)){
				if(localComparator > nInLine && preEmpty){
					nInLine = localComparator;
					preEmpty = false;
					nextCol = preCol;
				}
				localComparator = 0;
			}
			index++;
		}
		if(localComparator > nInLine && preEmpty){
			nInLine = localComparator;
			preEmpty = false;
			nextCol = preCol;
		}
	}
	
	private void checkAntiDiagonal(String antiDiagonal, int id){
		int localComparator = 0;
		int col = field.getLastMoveCol(id);
		int row = field.getLastMoveRow(id);
		int index = 0;
		int offset = row >= col ? col : row;
		int initialCol = col - offset;
		while(index < antiDiagonal.length()){
			if(antiDiagonal.charAt(index) == field.getGamePiece(id)){
				localComparator++;
				if( index < antiDiagonal.length()-1 && antiDiagonal.charAt(index + 1) == field.getEmptyState()){
					if(localComparator > nInLine){
						nextCol = initialCol + index + 1;
						nInLine = localComparator;
						localComparator = 0;
					}
				}
				if(index > 0 &&  antiDiagonal.charAt(index-1) == field.getEmptyState()){
					preEmpty = true;
					preCol = initialCol + index - 1;
				}
			}
			if(antiDiagonal.charAt(index) == field.getGamePiece(1-id)){
				if(localComparator > nInLine && preEmpty){
					preEmpty = false;
					nInLine = localComparator;
					nextCol = preCol;
				}
				localComparator = 0;
			}
			index++;
		}
		if(localComparator > nInLine && preEmpty){
			nInLine = localComparator;
			preEmpty = false;
			nextCol = preCol;
		}
	}
}
