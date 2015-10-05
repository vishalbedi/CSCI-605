package csci.hw6.example1;


public class MainClass {
	public static void main(String[] args){
		CalculatorModel model = new CalculatorModel();
		CalculatorView view = new CalculatorView();
		CalculatorController ctr = new CalculatorController(model, view);
		view.bindController(ctr);
		view.bindModel(model);
		view.init();
	}
}
