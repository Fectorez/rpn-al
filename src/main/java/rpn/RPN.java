package rpn;

import java.util.Arrays;
import java.util.Stack;

public class RPN {
    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    private static final String[] OPERATORS = {ADD, SUB, MUL, DIV};

    public RPN() {}

    public double compute(String expression) {
        Stack<String> stack = new Stack<>();

        for ( String value : expression.split("\\s+") ) {
            if ( isNumeric(value) ) {
                stack.push(value);
            }
            else if ( isOperator(value) ) {
                if ( stack.size() >= 2 ) {
                    String topValue1 = stack.pop();
                    String topValue2 = stack.pop();
                    stack.push(operation(topValue1, topValue2, value));
                }
                else {
                    throw new RuntimeException(String.format("Operator \"%s\" not expected here.", value));
                }
            }
            else {
                throw new RuntimeException(String.format("Value \"%s\" not recognized. Value must be a number or an operator in %s", value, Arrays.toString(OPERATORS)));
            }
        }

        if ( stack.size() > 1 ) {
            throw new RuntimeException(String.format("Some values cannot be computed. Remaining stack: %s", stack.toString()));
        }

        return Double.valueOf(stack.pop());
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isOperator(String str) {
        return Arrays.asList(OPERATORS).contains(str);
    }

    /**
     *
     * @param val1 first val
     * @param val2 second val
     * @param op the operator
     * @return the result of val2 op val1 as string
     * exemple: operation("5", "3", "-") returns "2"
     */
    private static String operation(String val1, String val2, String op){
        double a = Double.valueOf(val2);
        double b = Double.valueOf(val1);

        switch ( op ) {
            case ADD:
                return String.valueOf(a + b);
            case SUB:
                return String.valueOf(a - b);
            case MUL:
                return String.valueOf(a * b);
            case DIV:
                if ( b == 0.0 )
                    throw new ArithmeticException("Division by 0");
                return String.valueOf(a / b);
        }

        return "";
    }
}
