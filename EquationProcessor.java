package calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class EquationProcessor {
    public static double evaluateExpression(String postfix) {
        Deque<Double> stack = new ArrayDeque<>();
        String[] tokens = postfix.split("\\s+");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else if (token.equals("\u221A")) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid  expression");
                }
                double operand = stack.pop();
                stack.push(Math.sqrt(operand));
            } else if (token.equals("^")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid  expression");
                }
                double exponent = stack.pop();
                double base = stack.pop();
                stack.push(Math.pow(base, exponent));
            } else if (token.equals("~")) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid  expression");
                }
                double operand = stack.pop();
                stack.push(-operand);
            } else if (token.matches("[+\\-\u00D7\u00F7]")) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid  expression");
                }
                double operand2 = stack.pop();
                double operand1 = stack.pop();

                char operator = token.charAt(0);
                switch (operator) {
                    case '+' -> stack.push(operand1 + operand2);
                    case '-' -> stack.push(operand1 - operand2);
                    case '\u00D7' -> stack.push(operand1 * operand2);
                    case '\u00F7' -> {
                        if (operand2 == 0) {
                            throw new ArithmeticException("Division by zero is not allowed");
                        }
                        stack.push(operand1 / operand2);
                    }
                    default -> throw new IllegalArgumentException("Invalid operator: " + operator);
                }
            } else {
                throw new IllegalArgumentException("Invalid token in postfix expression: " + token);
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return stack.pop();
    }


    //Shunting Yard algorithm
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        boolean lastTokenWasOperator = true;

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c) || (c == '.' && i + 1 < infix.length() && Character.isDigit(infix.charAt(i + 1)))) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < infix.length() && (Character.isDigit(c) || c == '.')) {
                    numBuilder.append(c);
                    i++;
                    if (i < infix.length()) {
                        c = infix.charAt(i);
                    }
                }
                postfix.append(numBuilder).append(" ");
                i--;
                lastTokenWasOperator = false;
            } else if (c == '(') {
                stack.push(c);
                lastTokenWasOperator = true;
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop(); // Pop the '('
                }
                lastTokenWasOperator = false;
            } else if (c == '-' && lastTokenWasOperator) {
                stack.push('~'); // Use ~ as a marker for unary minus
            } else if (c == '\u221A') {
                stack.push(c);
                lastTokenWasOperator = true;
            } else if (c == '^' || c == '+' || c == '-' || c == '\u00D7' || c == '\u00F7') {
                if (!lastTokenWasOperator) {
                    while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                        postfix.append(stack.pop()).append(" ");
                    }
                    stack.push(c);
                }
                lastTokenWasOperator = true;
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }


    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '\u00D7':
            case '\u00F7':
                return 2;
            case '^':
                return 3;
            case '~':
                return 4;
            case '\u221A':
                return 5;
        }
        return -1;
    }
    }

