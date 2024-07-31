package task1;

import java.util.Scanner;
import java.util.Stack;
import java.util.InputMismatchException;

/**
 * Users input postfix expressions for the program
 * to evaluate them using a stack algorithm.
 */
public class PostfixEvaluator {
    /**
     * Main method presents a menu to the user,
     * for evaluating expressions or to exit the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Postfix Expression Evaluator");
            System.out.println("-------------------------------");
            System.out.println("1. Evaluate a postfix expression");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            boolean validInput = false;

            while (!validInput) {
                try {
                    choice = scanner.nextInt();
                    validInput = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    System.out.print("Enter your choice: ");
                    scanner.nextLine();
                }
            }

            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter a postfix expression: ");
                String expression = scanner.nextLine();
                try {
                    int result = evaluatePostfix(expression);
                    System.out.println("Result: " + result);
                } catch (ArithmeticException | IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
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
     * @throws IllegalArgumentException Invalid operator is encountered
     */
    public static int evaluatePostfix(String expression) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");
        for (String token : tokens) {
            if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Insufficient operands for operator: " + token);
                }
                int operand2 = stack.pop();
                int operand1 = stack.pop();
                int result = performOperation(token, operand1, operand2);
                stack.push(result);
            } else {
                try {
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid token in expression: " + token);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: too many operands");
        }
        return stack.pop();
    }

    /**
     * Checks if a given token is an operator.
     *
     * @param token The token to check
     * @return true if the token is an operator (+, -, *, /)
     */
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /**
     * Performs the operation depending on the operator and given
     * operands.
     *
     * @param operator The operators (+, -, *, /)
     * @param operand1 The 1st operand
     * @param operand2 The 2nd operand
     * @return The result of the operation
     * @throws ArithmeticException      Division by zero is attempt
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
                    throw new ArithmeticException("Division by zero detected: " + operand1 + " / " + operand2 +
                            ". Division by zero is undefined.");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}