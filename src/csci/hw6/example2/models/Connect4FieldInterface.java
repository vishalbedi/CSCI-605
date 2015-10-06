package csci.hw6.example2.models;

import csci.hw6.example2.models.PlayerInterface;

/**
 * @author Vishal, Diachi
 *
 */
public interface Connect4FieldInterface {
	
	public boolean checkIfPiecedCanBeDroppedIn(int column);

	public void dropPieces(int column, char gamePiece);

	boolean didLastMoveWin();

	public boolean isItaDraw();

	public void init(PlayerInterface playerA, PlayerInterface playerB);

	public String toString();

	public void playTheGame();
}