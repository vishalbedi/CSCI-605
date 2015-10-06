package csci.hw6.example1;

import java.util.ArrayList;
import java.util.Stack;

public class CalculatorModel {
	private ArrayList<Character> operatorPrecedence = new ArrayList<Character>();
	private String terminatingChar = "#";
	private String operatorRegex = "[+*%^()/-]";
	private String checkExpression = "[^0-9+*^()% ./-]";
	private String expression = "";
	private double result = 0.0;
	
	public CalculatorModel(){
		setPrecedence();
	}
	
	/**
	 * @return the operatorPrecedence
	 */
	public ArrayList<Character> getOperatorPrecedence() {
		return operatorPrecedence;
	}
	/**
	 * @param operatorPrecedence the operatorPrecedence to set
	 */
	public void setOperatorPrecedence(ArrayList<Character> operatorPrecedence) {
		this.operatorPrecedence = operatorPrecedence;
	}
	/**
	 * @return the terminatingChar
	 */
	public String getTerminatingChar() {
		return terminatingChar;
	}
	/**
	 * @param terminatingChar the terminatingChar to set
	 */
	public void setTerminatingChar(String terminatingChar) {
		this.terminatingChar = terminatingChar;
	}
	/**
	 * @return the operatorRegex
	 */
	public String getOperatorRegex() {
		return operatorRegex;
	}
	/**
	 * @param operatorRegex the operatorRegex to set
	 */
	public void setOperatorRegex(String operatorRegex) {
		this.operatorRegex = operatorRegex;
	}
	/**
	 * @return the checkExpression
	 */
	public String getCheckExpression() {
		return checkExpression;
	}
	/**
	 * @param checkExpression the checkExpression to set
	 */
	public void setCheckExpression(String checkExpression) {
		this.checkExpression = checkExpression;
	}
	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}
	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		expression = expression.trim();
		int expressionLen = expression.length();
		int noSpaceExpression = expression.replaceAll("\\s+","").length();
		if(!(expression.matches(checkExpression) || 
				(2 * noSpaceExpression - 1) != expressionLen)){
			this.expression = expression;
		}
		
	}
	/**
	 * @return the result
	 */
	public double getResult() {
		String[] expressionArray = expression.split(" ");
		ArrayList<String> expression = toPostFix(getExpressionFromArgs(expressionArray));
		result = calculate(expression);
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(double result) {
		this.result = result;
	}
	private void setPrecedence() {
		operatorPrecedence.add('+');
		operatorPrecedence.add('-');
		operatorPrecedence.add('%');
		operatorPrecedence.add('*');
		operatorPrecedence.add('/');
		operatorPrecedence.add('^');
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
			if (!s.equals(terminatingChar)) {
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
			if (s.matches(operatorRegex)) {
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
//		System.out.println("Post fix");
//		System.out.println(postFixExpression);
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
		return operatorPrecedence.indexOf(new Character(operator1)) >= 
				operatorPrecedence.indexOf(new Character(operator2));
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
			if (s.matches(operatorRegex)) {
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
}
