package csci.hw6.example1;

import java.util.ArrayList;
import java.util.Stack;

public class CalculatorController {
	private CalculatorModel model;
	private CalculatorView view;
	
	public CalculatorController(CalculatorModel model, CalculatorView view){
		this.model = model;
		this.view = view;
	}
	
	/**
	 * @description : Splits the input into operators and operands and stores it
	 *              in and ArrayList
	 * 
	 * @param String[]  userInput
	 * @return ArrayList<String>
	 */
	private ArrayList<String> getExpressionFromArgs(String input[]) {
		ArrayList<String> expression = new ArrayList<String>();
		for (String s : input) {
			if (!s.equals(model.getTerminatingChar())) {
				expression.add(s);
			} else {
				break;
			}
		}
		return expression;
	}
	
	/**
	 * @description : Converts the infix expression into postfix expression
	 * 
	 * 
	 * @return ArrayList<String>
	 */
	private ArrayList<String> toPostFix(ArrayList<String> expression) {
		Stack<Character> operatorStack = new Stack<Character>();
		ArrayList<String> postFixExpression = new ArrayList<String>();
		int bracesCount = 0;
		int operatorCount = 0;
		for (String s : expression) {
			if (s.matches(model.getOperatorRegex())) {
				if(s.charAt(0) == '('){
					bracesCount++;
					continue;
				}
				if(s.charAt(0)== ')'){
					bracesCount--;
					while(operatorCount > 0){
						if(operatorStack.empty()) break;
						postFixExpression.add("" + operatorStack.pop());
						operatorCount--;
					}
					continue;
				}
				if (operatorStack.empty() || bracesCount > 0){
					if(bracesCount > 0) {
						if(operatorCount > 0){
							int copyOfOperatorCount = operatorCount;
							while(copyOfOperatorCount > 0){
								char operatorInStack = operatorStack.peek();
								char operatorToPush = s.charAt(0);
								if(checkPrecedence(operatorInStack, operatorToPush)){
									operatorStack.pop();
									postFixExpression.add("" + operatorInStack);
									if(operatorStack.isEmpty())
										break;
									operatorInStack = operatorStack.peek();
								}
								copyOfOperatorCount--;
							}
						}
						operatorCount++;
					}
					operatorStack.push(s.charAt(0));
					}
				else {
					char operatorInStack = operatorStack.peek();
					char operatorToPush = s.charAt(0);
					while (checkPrecedence(operatorInStack, operatorToPush)) {
						operatorStack.pop();
						postFixExpression.add("" + operatorInStack);
						if(operatorStack.isEmpty())
							break;
						operatorInStack = operatorStack.peek();
					}
					operatorStack.push(operatorToPush);
				}
			} else {
				postFixExpression.add(s);
			}
		}
		while (!operatorStack.empty()) {
			postFixExpression.add("" + operatorStack.pop());
		}
		return postFixExpression;
	}
	
	/**
	 * @description : Compares the precedence of the operators 
	 * 
	 * @param char operator1
	 * @param char operator2
	 * @return boolean
	 */
	private boolean checkPrecedence(char operator1,  char operator2){
		return model.getOperatorPrecedence().indexOf(new Character(operator1)) >= 
				model.getOperatorPrecedence().indexOf(new Character(operator2));
	}
	
	
	/**
	 * @description : Calculates the postfix expression
	 * 
	 * @param ArrayList<String>  postfix formatted ArrayList
	 * @return double
	 */
	private double calculate(ArrayList<String> expression) {
		Stack<Double> calc = new Stack<Double>();
		double value1 = 0;
		double value2 = 0;
		double result = 0;
		for (String s : expression) {
			if (s.matches(model.getOperatorRegex())) {
				switch (s.charAt(0)) {
				case '+': {
					value2 = calc.pop();
					value1 = calc.pop();
					result = value1 + value2;
					calc.push(result);
					break;
				}
				case '-': {
					value2 = calc.pop();
					value1 = calc.pop();
					result = value1 - value2;
					calc.push(result);
					break;
				}
				case '*': {
					value2 = calc.pop();
					value1 = calc.pop();
					result = value1 * value2;
					calc.push(result);
					break;
				}
				case '/': {
					value2 = calc.pop();
					value1 = calc.pop();
					result = value1 / value2;
					calc.push(result);
					break;
				}
				case '%': {
					value2 = calc.pop();
					value1 = calc.pop();
					result = value1 % value2;
					calc.push(result);
					break;
				}
				case '^' : {
					value2 = calc.pop();
					value1 = calc.pop();
					result = Math.pow(value1, value2);
					calc.push(result);
				}
				}
			} else {
				calc.push(Double.parseDouble(s));
			}
		}
		return calc.pop();
	}
	
	public void init(){
		if(model.getExpression().isEmpty()){
			System.out.println("Something went wrong");
			view.init();
			return;
		}
		String[] expressionArray = model.getExpression().split(" ");
		ArrayList<String> expression = toPostFix(getExpressionFromArgs(expressionArray));
		double result = calculate(expression);
		model.setResult(result);
		view.displayResult();
	}
}
