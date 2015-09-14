package csci.hw3.hangman;



/**
 * PLayer class: Players for hangman
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */

public class Player {
	private int attempts = 0;
	private String name = "";
	private int score = 0;
	private final int SCORE_MULTIPLER = 10;
	private String tries = "";
	
	/**
	 * @param playerName
	 */
	public Player(String playerName){
		name = playerName;
	}
	
	public void updateTries(char trial){
		tries += trial + " ";
	}
	
	public String getTries(){
		return tries;
	}
	/**
	 * @return the attempts
	 */
	public int getAttempts() {
		return attempts;
	}

	public void incrementAttempts() {
		this.attempts++;
	}
	
	/**
	 * 
	 */
	public void resetAttempts(){
		this.attempts = 0;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * @param difficulty
	 */
	public void updateScore (int difficulty){
		score += ( difficulty/attempts ) * SCORE_MULTIPLER;
	}
	
}
