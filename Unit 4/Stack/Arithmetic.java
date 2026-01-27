public class Arithmetic {

	// Evaluates a String exp that has an arithmetic expression, written in classic
	// notation
	public static int evaluate(String exp) {
		return evaluateStout(convertClassicToStout(exp));
	}

	// Returns the result of doing operand1 operation operand2,
	// e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation.equals("+")) return operand1 + operand2;
		if (operation.equals("-")) return operand1 - operand2;
		if (operation.equals("*")) return operand1 * operand2;
		if (operation.equals("/")) return operand1 / operand2;
		if (operation.equals("%")) return operand1 % operand2;
		if (operation.equals("^")) return (int) Math.pow(operand1, operand2);
		throw new IllegalArgumentException("Unknown operator: " + operation);
	}

	// Evaluates a String exp that has an arithmetic expression written in STOUT
	// notation
	public static int evaluateStout(String exp) {
		if (exp == null || exp.length() == 0) throw new IllegalArgumentException("bruh");
		MyStack<Integer> consts = new MyStack<>();
		String [] stuff = exp.trim().split(" ");
            for (String thingie : stuff) {
                char c0 = thingie.charAt(0);
                boolean isNum = (c0 >= '0' && c0 <= '9') || (c0 == '-' && thingie.length() > 1);
                
                if (isNum) consts.push(Integer.valueOf(thingie));

                else {
                    int b = consts.pop();
                    int a = consts.pop();
                    consts.push(operate(a, b, thingie));
                }
            }

		return consts.pop();
	}

	public static String convertClassicToStout(String exp) {
		if (exp == null || exp.length() == 0) throw new IllegalArgumentException("bruh");

		MyStack<String> ops = new MyStack<>();
		StringBuilder stout = new StringBuilder();

		String[] terms = exp.trim().split(" ");
		for (String term : terms) {
			char c0 = term.charAt(0);
			boolean isNum = (c0 >= '0' && c0 <= '9') || (c0 == '-' && term.length() > 1); //handle negatives

			if (isNum) stout.append(term).append(" ");

			if (term.equals("(")) ops.push(term);

			if (term.equals(")")) {
				while (!ops.empty() &&  !ops.peek().equals("(")) {
					stout.append(ops.pop()).append(" ");
				}
				ops.pop();
			}

			if (term.equals("+") || term.equals("-") || term.equals("*") || term.equals("/") || term.equals("%") || term.equals("^")) {
		    	while (!ops.empty() && !ops.peek().equals("(") && (order(ops.peek()) > order(term) || (order(ops.peek()) == order(term) && !term.equals("^")))) {
					stout.append(ops.pop()).append(" ");
				}
				ops.push(term);
			}	
		}
		while (!ops.empty()) stout.append(ops.pop()).append(" ");
		return stout.toString().trim();
	}

	public static int order(String op) {
		if (op.equals("^")) return 3;
		if (op.equals("*") || op.equals("/") || op.equals("%")) return 2;
		if (op.equals("+") || op.equals("-")) return 1;
		return 0;
	}
}
