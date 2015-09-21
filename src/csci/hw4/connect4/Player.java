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
	
//	private int getBotMove(){
//		if(field.toString().indexOf(gamePiece) == -1){
//			int min = (field.getMinPlayableCols(field.getRows()));
//			int max = (field.getMaxPlayableCols(field.getRows()));
//			int random = min + (int)(Math.random() * ((max - min) + 1));
//			return random;
//		} else {
//			int opponentId = 1 - id;
//			int row = field.getLastMoveRow(id);
//			int col = field.getLastMoveCol(id);
//			String horizontal = field.getHorizontal(row, col);
//			String vertical = field.getVertical(row, col);
//			String diagonal = field.getDiagonal(row, col);
//			String antiDiagonal = field.getAntiDiagonal(row, col);
//			
//			checkHorizontal(horizontal, id);
//			checkVertical(vertical, id);
//			checkDiagonal(diagonal, id);
//			checkAntiDiagonal(antiDiagonal, id);
//			int myNInLine = nInLine;
//			int myNext = nextCol;
//			
//			nInLine = 0;
//			nextCol = 0;
//			
//			
//			int oppRow = field.getLastMoveRow(opponentId);
//			int oppCol = field.getLastMoveCol(opponentId);
//			String oppHorizontal = field.getHorizontal(oppRow, oppCol);
//			String oppVertical = field.getVertical(oppRow, oppCol);
//			String oppDiagonal = field.getDiagonal(oppRow, oppCol);
//			String oppAntiDiagonal = field.getAntiDiagonal(oppRow, oppCol);
//			
//			checkHorizontal(oppHorizontal, opponentId);
//			checkVertical(oppVertical, opponentId);
//			checkDiagonal(oppDiagonal, opponentId);
//			checkAntiDiagonal(oppAntiDiagonal, opponentId);
//			
//			if(myNInLine > 2){
//				return myNext;
//			}
//			if(nInLine > 2){
//				return nextCol;
//			}
//			return myNext;
//		}
//	}
//	
//	private void checkHorizontal (String horizontal, int id){
//		int localComparator = 0;
//		int index = 1;
//		while(index < horizontal.length()-1){
//			if(horizontal.charAt(index) == field.getGamePiece(id)){
//				localComparator++;
//				if(horizontal.charAt(index + 1) == field.getEmptyState()){
//					if(localComparator > nInLine){
//						nextCol = index + 1;
//						nInLine = localComparator;
//						localComparator = 0;
//					}
//				}
//				if(horizontal.charAt(index-1) == field.getEmptyState()){
//					preEmpty = true;
//					preCol = index - 1;
//				}
//			}
//			if(horizontal.charAt(index) == field.getGamePiece(1-id)){
//				if(localComparator > nInLine && preEmpty){
//					nInLine = localComparator;
//					nextCol = preCol;
//				}
//				localComparator = 0;
//			}
//			index++;
//		}
//	}
//	
//	private void checkVertical(String vertical, int id){
//		int localComparator = 0;
//		int col = field.getLastMoveCol(id);
//		int index = vertical.length()-1;
//		while(index > 1){
//			if(vertical.charAt(index) == field.getGamePiece(id)){
//				localComparator++;
//				if(vertical.charAt(index - 1) == field.getEmptyState()){
//					if(localComparator > nInLine){
//						nextCol = col;
//						nInLine = localComparator;
//						localComparator = 0;
//					}
//				}
//				
//			}
//			if(vertical.charAt(index) == field.getGamePiece(1-id)){
//				localComparator = 0;
//			}
//			index--;
//		}
//	}
//	
//	private void checkDiagonal(String diagonal, int id){
//		int localComparator = 0;
//		int col = field.getLastMoveCol(id);
//		int row = field.getLastMoveRow(id);
//		int index = 1;
//		int tempRow = field.getRows() - row -1;
//		int offset = tempRow >= col ? col : tempRow;
//		int initialCol = col - offset;
//		localComparator = 0;
//		while(index < diagonal.length()-1){
//			if(diagonal.charAt(index) == field.getGamePiece(id)){
//				localComparator++;
//				if(diagonal.charAt(index + 1) == field.getEmptyState()){
//					if(localComparator > nInLine){
//						nextCol = initialCol + index + 1;
//						nInLine = localComparator;
//						localComparator = 0;
//					}
//				}
//				if(diagonal.charAt(index-1) == field.getEmptyState()){
//					preEmpty = true;
//					preCol = initialCol + index - 1;
//				}
//			}
//			if(diagonal.charAt(index) == field.getGamePiece(1-id)){
//				if(localComparator > nInLine && preEmpty){
//					nInLine = localComparator;
//					nextCol = preCol;
//				}
//				localComparator = 0;
//			}
//			index++;
//		}
//	}
//	
//	private void checkAntiDiagonal(String antiDiagonal, int id){
//		int localComparator = 0;
//		int col = field.getLastMoveCol(id);
//		int row = field.getLastMoveRow(id);
//		int index = 1;
//		int offset = row >= col ? col : row;
//		int initialCol = col - offset;
//		while(index < antiDiagonal.length()-1){
//			if(antiDiagonal.charAt(index) == field.getGamePiece(id)){
//				localComparator++;
//				if(antiDiagonal.charAt(index + 1) == field.getEmptyState()){
//					if(localComparator > nInLine){
//						nextCol = initialCol + index + 1;
//						nInLine = localComparator;
//						localComparator = 0;
//					}
//				}
//				if(antiDiagonal.charAt(index-1) == field.getEmptyState()){
//					preEmpty = true;
//					preCol = initialCol + index - 1;
//				}
//			}
//			if(antiDiagonal.charAt(index) == field.getGamePiece(1-id)){
//				if(localComparator > nInLine && preEmpty){
//					nInLine = localComparator;
//					nextCol = preCol;
//				}
//				localComparator = 0;
//			}
//			index++;
//		}
//	}
}
