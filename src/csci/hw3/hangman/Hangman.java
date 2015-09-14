package csci.hw3.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * Hangman Class  Logic to create hangman game.
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */
public class Hangman {
	ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<String> wordsDb = new ArrayList<String>();
	private String currentWord;
	private char[] currentGuess;
	private final String DEFAULT_FILE = "C:\\Users\\Vishal\\Workplace\\CSCI-605\\src\\csci\\hw3\\words.txt";
	private final int GUESS_LIMIT = 6;
	private int gameCount = 0;
	private boolean continueGame = true;
	Scanner sc = new Scanner(System.in);

	/**
	 * @description : Reads the file and populates the words
	 * 
	 * @param String wordFileName
	 * 
	 * @return null
	 */
	private void populateWordsDb(String wordFileName) {
		File wordFile = new File(wordFileName);
		try (Scanner sc = new Scanner(wordFile)) {
			while (sc.hasNext()) {
				wordsDb.add(sc.next());
			}
		} catch (FileNotFoundException e) {
			System.out.println("The File name you entered does not exists..");
			System.out.println("Please Check the File name..");
			// e.printStackTrace();
		}
		System.out.println("###########-- Read successful --###########");
	}

	/**
	 * @description : Adds players to the players list. 
	 * 
	 * @param String playerName
	 * 
	 * @return null
	 */
	private void addPlayer(String playerName) {
		players.add(new Player(playerName));
	}

	/**
	 * @description : gets random word from the wordsdb 
	 * 
	 * @param null
	 * 
	 * @return String randomWord
	 */
	private String getRandomWord() {
		int wordCount = wordsDb.size();
		Random generator = new Random();
		int randomIndex = generator.nextInt(wordCount);
		return wordsDb.remove(randomIndex);
	}

	/**
	 * @description : gets random word from the wordsDb 
	 * 
	 * @param null
	 * 
	 * @return String randomWord
	 */
	private boolean updateCurrentGuess(char charSequence) {
		int charIndex = currentWord.indexOf(charSequence);
		if (charIndex > -1) {
			if (currentGuess.length == 0)
				setCurrentGuess(currentWord);
			currentGuess[charIndex] = charSequence;
			return true;
		}
		return false;
	}

	/**
	 * @description : sets current guess
	 * 
	 * @param String currentWord :random word 
	 * 
	 * @return null
	 */
	private void setCurrentGuess(String currentWord) {
		currentGuess = new char[currentWord.length()];
		for (int i = 0; i < currentGuess.length; i++) {
			currentGuess[i] = '_';
		}
	}

	/**
	 * @description : prints characters of an array
	 * 
	 * @param  String guess
	 * 
	 * @return null
	 */
	private void printCurrentGuess(char[] guess) {
		for (int i = 0; i < guess.length; i++) {
			System.out.print(guess[i] + " ");
		}
		System.out.println("");
	}

	/**
	 * @description : Displays Game intro
	 * 
	 * @param null
	 * 
	 * @return null
	 */
	private void intro() {
		System.out.println("###########---HANGMAN---###########");
		System.out.println("Welcome to Hangman");
		System.out.println("The goal of the game is to guess all the leters in the word");
		System.out.println("If you guess the letter right the word with the letter placement will be shown");
		System.out.println("If you exceede " + GUESS_LIMIT + " chanches.. YOU LOOSE :-X");
		System.out.println("Lets Start... :-D");
	}

	/**
	 * @description : Fetches the file from user specified location
	 * 
	 * @param null
	 * 
	 * @return null
	 */
	private void userFile() {
		System.out.println("Do you have a text file for hangman?(y/n)");
		char userInput;
		userInput = sc.next().charAt(0);
		if (userInput == 'y') {
			System.out.println("Enter File name...");
			String fileName = sc.next();
			populateWordsDb(fileName);
		} else if (userInput == 'n') {
			populateWordsDb(DEFAULT_FILE);
		} else {
			System.out.flush();
			System.out.println("Sorry I could't get that..");
			userFile();
		}
	}

	/**
	 * @description : Sets up player objects
	 * 
	 * @param null 
	 * 
	 * @return null
	 */
	private void setUpPlayers() {
		System.out.println("How many Player ?");
		int playerCount = sc.nextInt();
		System.out.println("Enter Player Names...");
		for (int i = 0; i < playerCount; i++) {
			System.out.print("Player " + (i + 1) + " : ");
			addPlayer(sc.next());
			System.out.println("");
		}
	}

	/**
	 * @description : Checks the winning condition
	 * 
	 * @param null
	 * 
	 * @return null
	 */
	private boolean iWon() {
		String current = new String(currentGuess);
		return currentWord.equals(current);
	}

	/**
	 * @description : Computes difficulty by checking the length of the string
	 * 
	 * @param null
	 * 
	 * @return int difficulty index
	 */
	private int computeDifficulty() {
		return currentWord.length();
	}

	/**
	 * @description : Initializes the game
	 * 
	 * @param null 
	 * 
	 * @return null
	 */
	private void init() {
		currentGuess = new char[0];
		currentWord = getRandomWord();
		System.out.flush();
		System.out.println("Starting Hangman for " + players.size() + " players.");
		System.out.println("Guess the word");
	}

	/**
	 * @description : Game logic
	 * 
	 * @param null 
	 * 
	 * @return null
	 */
	private void play() {
		for (Player p : players) {
			while (p.getAttempts() < GUESS_LIMIT) {
				System.out.println(p.getName() + " You have " + (GUESS_LIMIT - p.getAttempts() + " left."));
				System.out.println("Characters guessed: " + p.getTries());
				System.out.println("Enter a character");
				char input = sc.next().charAt(0);
				if(p.getTries().indexOf(input) != -1){
					System.out.println("Character already used");
					continue;
				}
				p.updateTries(input);
				if (!updateCurrentGuess(input))
					p.incrementAttempts();
				if (currentGuess.length > 0) {
					printCurrentGuess(currentGuess);
				}
				if (iWon()) {
					System.out.println("Awesome.. you guessed it right.. ");
					System.out.println("The word is : " + currentWord);
					p.updateScore(computeDifficulty());
					System.out.println("Your score is " + p.getScore());
					break;
				}
				if (p.getAttempts() == GUESS_LIMIT) {
					System.out.println("You loose..");
					System.out.println("The word is : " + currentWord);
				}
			}
			p.resetAttempts();
		}
	}

	/**
	 * @description : Prints the score
	 * 
	 * @param null
	 * 
	 * @return null
	 */
	private void showScores() {
		System.out.println("Your scores are...");
		for (Player p : players) {
			System.out.println(p.getName() + " : " + p.getScore());
		}
	}

	/**
	 * @description : one game set
	 * 
	 * @param null 
	 * 
	 * @return null
	 */
	private void gameSet() {
		if (gameCount == 0) {
			userFile();
			setUpPlayers();
		}
		init();
		play();
		showScores();
		gameCount++;
	}

	
	/**
	 * @description : game loop
	 * 
	 * @param 
	 * 
	 * @return null
	 */
	public void game() {
		intro();
		gameSet();
		while (continueGame) {
			System.out.println("Do you want to continue");
			char input = sc.next().charAt(0);
			if (input == 'y') {
				gameSet();
			}
			if (input == 'n') {
				System.out.println("Good bye....");
				continueGame = false;
			}
		}
	}
}
