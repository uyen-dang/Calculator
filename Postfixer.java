import java.util.*;

public class Postfixer {

	private static boolean hasPrecedence(String op1, String op2) {
		return(opToPrcd(op1)>=opToPrcd(op2));
	}

	private static int opToPrcd(String op) {
		switch(op) {
		case "^": return(4);
		case "*": 
		case "/": return(3);
		case "+":
		case "-": return(2);
		case "(": return(1);
		default: return(0); // case of invalid operator
		}
	}

	private static boolean isOperator(String token) {
		if(token.equals(")")) return true;
		return(opToPrcd(token)>0);
	}

	private static double evaluate(double operand1, String operator, double operand2){
		if(!isOperator(operator)) throw new RuntimeException("IllegalArgumentException");
		switch(operator) {
		case "^": return(Math.pow(operand1, operand2));
		case "*": return(operand1*operand2);
		case "/": return(operand1/operand2);
		case "+": return(operand1+operand2);
		case "-": return(operand1-operand2);
		default: throw new RuntimeException("IllegalArgumentException "+operator+" is not valid"); // case of invalid operator
		}
	}

	public static double infixEvaluator(String line){ 
		StringSplitter data = new StringSplitter(line); 
		Stack<String> operators = new Stack<String>();  
		Stack<Double> operands = new Stack<Double>(); 
		
		while(data.hasNext()){
			String curr = data.next();
			if(!isOperator(curr)) operands.push(Double.parseDouble(curr));
			else if(curr.equals("(")) operators.push(curr);
			else if(curr.equals(")")) {
				while(!operators.peek().equals("(")) {
					String op = operators.pop();
					double num2 = operands.pop();
					double num1 = operands.pop();
					double ans = evaluate(num1, op, num2);
					operands.push(ans);
				}
				operators.pop();
			}
			else if(isOperator(curr)) {
				while(!operators.isEmpty()&&hasPrecedence(operators.peek(),curr)) {
					String op = operators.pop();
					double num2 = operands.pop();
					double num1 = operands.pop();
					double ans = evaluate(num1, op, num2);
					operands.push(ans);				
				}
				operators.push(curr);
			}			
		}

		while(!operators.isEmpty()) {
			String op = operators.pop();
			double num2 = operands.pop();
			double num1 = operands.pop();
			
			double ans = evaluate(num1, op, num2);
			operands.push(ans);	
		}
		if(!operators.isEmpty() && operands.size()!=1) throw new RuntimeException("Shunting Yard Exception");
		return(operands.pop());
	}
	
	public static String toPostfix(String line){
		StringSplitter data = new StringSplitter(line);  
		Stack<String> operators = new Stack<String>(); 
		String postfix = new String();

		while(data.hasNext()) { 
			String curr = data.next();
			if(!isOperator(curr)) postfix += curr;
			else if(isOperator(curr)&&opToPrcd(curr)>1) {
				if(operators.contains("(")&&hasPrecedence(operators.peek(),curr)) postfix += operators.pop();
				operators.push(curr);
			}
			else if(opToPrcd(curr)==1) operators.push(curr);
			else if(curr.equals(")")) {
				while(opToPrcd(operators.peek())>1) postfix += operators.pop();
				operators.pop();
			}
		}
		return(postfix);
	 }

}
