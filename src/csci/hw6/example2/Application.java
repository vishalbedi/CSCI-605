package csci.hw6.example2;

import csci.hw6.example2.models.*;
import csci.hw6.example2.views.*;
import csci.hw6.example2.controllers.*;
public class Application {
	public static void main(String[] args){
		Connect4Field model = new Connect4Field();
		Connect4View view = new Connect4View();
		Connect4Controller ctr = new Connect4Controller(model, view);
		view.bindModel(model);
		view.setController(ctr);
		
		ctr.run();
	}
}
