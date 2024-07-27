import java.util.Scanner;
import java.util.Stack;

/**
 * Users input postfix expressions for the program
 * to evaluate them using a stack algorithm.
 */
public class PostfixEvaluator {

    /**
     * The main method presents a menu to the user,
     * to evaluate expressions or exit the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Postfix Expression Evaluator");
            System.out.println("-------------------------------");
            System.out.println("1. Evaluate a postfix expression");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter a postfix expression: ");
                String expression = scanner.nextLine();
                int result = evaluatePostfix(expression);
                System.out.println("Result: " + result);
            } else if (choice == 2) {
                System.out.println("Exiting program. Bye :)");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    /**
     * Evaluates a given postfix expression.
     *
     * @param expression The postfix expression to evaluate
     * @return The result of the evaluation
     * @throws ArithmeticException      If division by zero is attempted
     * @throws IllegalArgumentException If an invalid operator is encountered
     */
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (isOperator(token)) {
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(token, operand1, operand2);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    /**
     * Checks if a given token is an operator.
     *
     * @param token The token to check
     * @return true if the token is an operator (+, -, *, /), false otherwise
     */
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * Performs the arithmetic operation specified by the operator on the given
     * operands.
     *
     * @param operator The arithmetic operator (+, -, *, /)
     * @param operand1 The first operand
     * @param operand2 The second operand
     * @return The result of the operation
     * @throws ArithmeticException      If division by zero is attempted
     * @throws IllegalArgumentException If an invalid operator is provided
     */
    private static int performOperation(String operator, int operand1, int operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}