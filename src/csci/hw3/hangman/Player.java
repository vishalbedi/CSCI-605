package csci.hw3.hangman;

public class Player {
	private int attempts = 0;
	private String name = "";
	private int score = 0;
	private final int SCORE_MULTIPLER = 10;
	
	public Player(String playerName){
		name = playerName;
	}
	/**
	 * @return the attempts
	 */
	public int getAttempts() {
		return attempts;
	}
	/**
	 * @param attempts the attempts to set
	 */
	public void incrementAttempts() {
		this.attempts++;
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
	public void updateScore (int difficulty){
		score = ( difficulty/attempts ) * SCORE_MULTIPLER;
	}
	
}
