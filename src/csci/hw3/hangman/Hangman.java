package csci.hw3.hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
	ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<String> wordsDb = new ArrayList<String>();
	private String currentWord;
	private char[] currentGuess = new char[0];
	private final String DEFAULT_FILE = "C:\\Users\\Vishal\\Workplace\\CSCI-605\\src\\csci\\hw3\\words.txt";
	private final int GUESS_LIMIT = 6;
	private int gameSet = 0;
	private boolean continueGame = true;
	Scanner sc = new Scanner(System.in);

	private void reset() {
		players.clear();
		currentGuess = new char[0];
	}

	private void populateWordsDb(String wordFileName) {
		File wordFile = new File(wordFileName);
		try (Scanner sc = new Scanner(wordFile)) {
			while (sc.hasNext()) {
				wordsDb.add(sc.next());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("The File name you entered does not exists..");
			System.out.println("Please Check the File name..");
			// e.printStackTrace();
		}
		System.out.println("###########-- Read successful --###########");
	}

	private void addPlayer(String playerName) {
		players.add(new Player(playerName));
	}

	private void addPlayers(String[] playerNames) {
		for (String player : playerNames) {
			players.add(new Player(player));
		}
	}

	private String getRandomWord() {
		int wordCount = wordsDb.size();
		Random generator = new Random();
		int randomIndex = generator.nextInt(wordCount) + 1;
		return wordsDb.remove(randomIndex);
	}

	private void updateCurrentGuess(String charSequence) {
		if (charSequence.length() == 1) {
			int charIndex = currentWord.indexOf(charSequence);
			if (charIndex > -1) {
				if (currentGuess.length == 0)
					setCurrentGuess(currentWord);
				currentGuess[charIndex] = charSequence.charAt(0);
			}

		}
		if (currentWord.equals(charSequence)) {
			currentGuess = currentWord.toCharArray();
		}
	}

	private void setCurrentGuess(String currentWord) {
		currentGuess = new char[currentWord.length()];
		for (int i = 0; i < currentGuess.length; i++) {
			currentGuess[i] = '_';
		}
	}

	private void printCurrentGuess(char[] guess) {
		for (int i = 0; i < guess.length; i++) {
			System.out.print(guess[i] + " ");
		}
		System.out.println("");
	}

	private void intro() {
		System.out.println("###########---HANGMAN---###########");
		System.out.println("Welcome to Hangman");
		System.out.println("The goal of the game is to guess all the leters in the word");
		System.out.println("If you guess the letter right the word with the letter placement will be shown");
		System.out.println("If you exceede " + GUESS_LIMIT + " chanches.. YOU LOOSE :-X");
		System.out.println("Lets Start... :-D");
	}

	private void userFile() {
		System.out.println("Do you have a text file for hangman?(y/n)");
		char userInput;
		userInput = sc.next().charAt(0);
		if (userInput == 'y') {
			System.out.println("Enter File name...");
			String fileName = sc.next();
			populateWordsDb(fileName);
		} else if (userInput == 'n') {
			if (gameSet == 0)
				populateWordsDb(DEFAULT_FILE);
		} else {
			System.out.flush();
			System.out.println("Sorry I could't get that..");
			userFile();
		}

	}

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

	private boolean iWon() {
		String current = currentGuess.toString().replace(',', '\0');
		return currentWord.equals(current);
	}

	private int computeDifficulty() {
		return currentWord.length();
	}

	private void init() {
		setUpPlayers();
		currentWord = getRandomWord();
		System.out.flush();
		System.out.println("Starting Hangman for " + players.size() + " players.");
		System.out.println("Guess the word");
	}

	private void play() {
		for (Player p : players) {
			while (p.getAttempts() < GUESS_LIMIT) {
				System.out.println(p.getName() + " You have " + (GUESS_LIMIT - p.getAttempts() + " left."));
				System.out.println("Enter a character or word");
				String input = sc.next();
				updateCurrentGuess(input);
				p.incrementAttempts();
				if (currentGuess.length > 0) {
					printCurrentGuess(currentGuess);
				}
				if (iWon()) {
					p.updateScore(computeDifficulty());
					break;
				}
				if (p.getAttempts() == GUESS_LIMIT) {
					System.out.println("You loose..");
					System.out.println("The word is : " + currentWord);
					p.resetAttempts();
				}
			}
		}
	}

	private void showScores() {
		System.out.println("Your scores are...");
		for (Player p : players) {
			System.out.println(p.getName() + " : " + p.getScore());
		}
	}

	private void gameSet() {
		userFile();
		init();
		play();
		showScores();
		gameSet++;
	}

	public void game() {
		while (continueGame) {
			if (gameSet == 0) {
				intro();
				gameSet();
			} else {
				System.out.println("Do you want to continue");
				char input = sc.next().charAt(0);
				if (input == 'y') {
					play();
				}
				if (input == 'n') {
					continueGame = false;
				}
			}
		}
	}
}
