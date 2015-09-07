
/*
 * 
 * Calculator.java
 * 
 * Version: 2.0
 * 
 */

package csci.hw2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Calculator class to calculate an expression
 * The program assumes that the input will be an expression in which each element 
 * is separated by a space and the expression is terminated by a #.
 * 
 *  It is assumed that the input is error free.
 * 
 * @author Vishal Bedi
 * @author Daichi Mae
 * 
 */
public class Calculator {
	private static ArrayList<Character> OPERATOR_PRECEDENCE = new ArrayList<Character>();
	private static String TERMINATING_CHAR = "#";
	private static String OPERATOR_REGEX = "[+*%^()/-]";
	private static String CHECK_EXPRESSION = "[^0-9+*^()% ./-]";
	private static String expression = null;
	private static Scanner sc;

	/**
	 * @description : Sets the precedence of the operators to carry out
	 *              mathematical operations
	 * 
	 * @return null
	 */
	private static void setPrecedence() {
		OPERATOR_PRECEDENCE.add('+');
		OPERATOR_PRECEDENCE.add('-');
		OPERATOR_PRECEDENCE.add('%');
		OPERATOR_PRECEDENCE.add('*');
		OPERATOR_PRECEDENCE.add('/');
		OPERATOR_PRECEDENCE.add('^');
	}

	/**
	 * @description : Reads from the CLI, expression to solve
	 * 
	 * @return boolean true if expression is correctly entered
	 */
	public static boolean captureExpression() {
		System.out.println("Enter an Expression to Calculate...");
		System.out.println("Please enter a space break followed by every numeral or operand..");
		System.out.println("Enter # once you are finished with the expression");
		sc = new Scanner(System.in);
		expression = sc.nextLine();
		if(expression.matches(CHECK_EXPRESSION)){
			return false;
		}
		return true;
	}

	/**
	 * @description : Splits the input into operators and operands and stores it
	 *              in and ArrayList
	 * 
	 * @param String[]  userInput
	 * @return ArrayList<String>
	 */
	private static ArrayList<String> getExpressionFromArgs(String input[]) {
		ArrayList<String> expression = new ArrayList<String>();
		for (String s : input) {
			if (!s.equals(TERMINATING_CHAR)) {
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
	private static ArrayList<String> toPostFix(ArrayList<String> expression) {
		Stack<Character> operatorStack = new Stack<Character>();
		ArrayList<String> postFixExpression = new ArrayList<String>();
		int bracesCount = 0;
		for (String s : expression) {
			if (s.matches(OPERATOR_REGEX)) {
				if(s.charAt(0) == '('){
					bracesCount++;
					continue;
				}
				if(s.charAt(0)== ')'){
					bracesCount--;
					postFixExpression.add("" + operatorStack.pop());
					continue;
				}
				if (operatorStack.empty() || bracesCount > 0)
					operatorStack.push(s.charAt(0));
				else {
					char operatorInStack = operatorStack.peek();
					char operatorToPush = s.charAt(0);
					while (OPERATOR_PRECEDENCE.indexOf(new Character(operatorInStack)) >= OPERATOR_PRECEDENCE
							.indexOf(new Character(operatorToPush))) {
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
	 * @description : Calculates the postfix expression
	 * 
	 * @param ArrayList<String>  postfix formatted ArrayList
	 * @return double
	 */
	private static double calculate(ArrayList<String> expression) {
		Stack<Double> calc = new Stack<Double>();
		double value1 = 0;
		double value2 = 0;
		double result = 0;
		for (String s : expression) {
			if (s.matches(OPERATOR_REGEX)) {
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

	public static void main(String args[]) {
		setPrecedence();
		if(!captureExpression()){
			System.out.println("Please check the expression you have entered..");
			System.out.println("You have entered: " + expression);
			return;
		}
		String[] expressionArray = expression.split(" ");
		ArrayList<String> expression = toPostFix(getExpressionFromArgs(expressionArray));
		System.out.println("PostFix Expression >>");
		System.out.println(expression.toString());
		double result = calculate(expression);
		System.out.println("Result: " + result);
	}
}

/*
 * Enter an Expression to Calculate...
 * Please enter a space break followed by every numeral or operand.. 
 * Enter # once you are finished with the expression
 * 1 + 3 * 4 - 6 * 1 + 4 - 3 % 2 # 
 * [1, 3, 4, *, 6, 1, *, -, +, 4, 3, 2, %, -, +]
	Result: 10.0
 */
