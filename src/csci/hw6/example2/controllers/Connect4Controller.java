package csci.hw6.example2.controllers;

import csci.hw6.example2.models.*;
import csci.hw6.example2.views.Connect4View;

public class Connect4Controller {
	Connect4Field model;
	Connect4View view;
	
	public Connect4Controller (Connect4Field m, Connect4View v){
		this.model = m;
		this.view = v;
	}
	
	public void initMultiplayer(){
		Player aPlayer = new Player(model, "A", '+');
		Player bPlayer = new Player(model, "B", '*');
		model.init(aPlayer, bPlayer);
	}
	
	public void initVsCpu(){
		Player aPlayer = new Player(model, "A", '+');
		CpuPlayer cup = new CpuPlayer(model, "CPU", '*');
		model.init(aPlayer, cup);
	}
	
	public void playGame (){
		int column;
		boolean gameIsOver = false;
		do {
			for (int i  = 0; i< 2; i++) {
				System.out.println(model);
				if (model.isItaDraw()) {
					System.out.println("Draw");
					gameIsOver = true;
					break;
				} else {					
					if(model.getPlayers()[i].getName() == "CPU"){
						column = model.getPlayers()[i].nextMove();	
					}else {
						column = view.getNextMove(model.getPlayers()[i].getName());
					}
					model.dropPieces(column, model.getPlayers()[i].getGamePiece());
					if (model.didLastMoveWin()) {
						gameIsOver = true;
						System.out.println("The winner is: " + model.getPlayers()[i].getName());
						System.out.println(model);
						break;
					}
				}
			}
		} while (!gameIsOver);
	}
	
	public void run (){
		view.buildView();
		playGame();
	}
}
