package csci.hw6.example1;

import java.util.ArrayList;

public class CalculatorModel {
	private ArrayList<Character> operatorPrecedence = new ArrayList<Character>();
	private String terminatingChar = "#";
	private String operatorRegex = "[+*%^()/-]";
	private String checkExpression = "[^0-9+*^()% ./-]";
	private String expression = null;
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
}
