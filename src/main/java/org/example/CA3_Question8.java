package org.example;

import java.util.Scanner;
import java.util.Stack;

/**
 * Name:
 * Class Group:
 */
public class CA3_Question8 {

    /*
        Reads in an equation from the user
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the arithmetic expression in reverse polish notation:");
        String expression = in.nextLine().trim();

        try {
            double result = evaluateReversePolishNotation(expression);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
        Evaluates the arithmetic expression in reverse polish notation
     */
    private static double evaluateReversePolishNotation(String expression) {
        Stack<Double> stack = new Stack<>();

        String[] tokens = expression.split("\\s+");

        for (String token : tokens) {
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Insufficient operands for operator: " + token);
                }

                double operand2 = stack.pop();
                double operand1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        if (operand2 == 0) {
                            throw new IllegalArgumentException("Division by zero");
                        }
                        stack.push(operand1 / operand2);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + token);
                }

                // Display the current stack after applying the operator
                System.out.println("After applying " + token + ": " + stack);
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        if (stack.size() == 1) {
            return stack.pop();
        } else {
            throw new IllegalArgumentException("Invalid expression");
        }
    }

    /*
        Checks if a string is a numeric value
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
        Checks if a string is an operator
     */
    private static boolean isOperator(String str) {
        return str.matches("[+\\-*/]");
    }
}
