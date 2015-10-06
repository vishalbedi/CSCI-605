package csci.hw6.example2.views;

import java.util.Scanner;

import csci.hw6.example2.models.*;
import csci.hw6.example2.controllers.Connect4Controller;

public class Connect4View {
	Connect4Controller controller;
	Connect4Field model;
	Scanner sc = new Scanner(System.in);

	public void setController(Connect4Controller ctr) {
		this.controller = ctr;
	}

	public void bindModel(Connect4Field model) {
		this.model = model;
	}

	public void buildView() {
		greetings();

	}

	private void greetings() {
		System.out.println("Welcome to connect 4");
		System.out.println("If you drop 4 checkers in a row.. YOU win");
		System.out.println("All the best...");
		playType();
	}

	public void playType() {
		System.out.println("1. VS Multiplayer \n2. VS Computer   \nEnter 1 or 2");
		try {
			int userInput = sc.nextInt();
			if (userInput == 1) {
				controller.initMultiplayer();
			} else if (userInput == 2) {
				controller.initVsCpu();
			} else {
				System.out.println("Sorry.. I did't get it. Please select one of the two options");
			}
		} catch (Exception e) {
			System.out.println("Opps... Something went wrong.. Please try again");
			sc.next();
			playType();
		}
	}

	public int getNextMove(String name) {
		System.out.println(name + " Please Enter column number.. ");
		return getUserMove();
	}
	private int getUserMove(){
		try {
			int lastMove = -1;
			lastMove = sc.nextInt();
			return lastMove;
		} catch (Exception e) {
			System.out.println("Opps... Something went wrong.. Please try again");
			sc.next();
			getUserMove();
		}
		return -1;
	}
	
	public void drawBoard(){
		System.out.println(model);
	}
	
	public void displayDraw(){
		System.out.println("Draw");
	}
	
	public void displayWinner(String name){
		System.out.println("The winner is: " + name);
	}
}
