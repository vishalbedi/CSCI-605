package csci.hw6.example1;

public class CalculatorController {
	private CalculatorModel model;
	private CalculatorView view;
	
	public CalculatorController(CalculatorModel model, CalculatorView view){
		this.model = model;
		this.view = view;
	}
	
	public void init(){
		if(model.getExpression().isEmpty()){
			view.init();
			return;
		}
		view.displayResult();
	}
}
