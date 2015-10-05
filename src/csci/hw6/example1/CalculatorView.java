package csci.hw6.example1;

import java.util.Scanner;

public class CalculatorView {
	private CalculatorModel model;
	private CalculatorController controller;
	private Scanner sc;

	public void bindModel(CalculatorModel m) {
		this.model = m;
	}

	public void bindController(CalculatorController controller) {
		this.controller = controller;
	}

	/**
	 * @description : Reads from the CLI, expression to solve
	 * 
	 * @return boolean true if expression is correctly entered
	 */
	private void captureExpression() {
		String expression = "";
		try {
			sc = new Scanner(System.in);
			expression = sc.nextLine();
			model.setExpression(expression);
		} catch (Exception e) {
			System.out.println("Something went wrong");
			System.out.println("Lets Try again");
			sc.next();
			captureExpression();
		}
	}

	public void init() {
		System.out.println("Enter an Expression to Calculate...");
		System.out.println("Please enter a space break followed by every numeral or operand..");
		System.out.println("Enter # once you are finished with the expression");
		captureExpression();
		controller.init();
	}

	public void displayResult() {
		System.out.println(model.getResult());
	}
}
