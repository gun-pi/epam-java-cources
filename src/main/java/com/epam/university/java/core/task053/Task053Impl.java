package com.epam.university.java.core.task053;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task053Impl implements Task053 {

    /**
     * Calculate the result.
     *
     * @param input Mathematical expression.
     * @return double result
     * @throws IllegalArgumentException if input parameters not valid
     */
    @Override
    public double calculate(String input) {
        checkInput(input);
        return getResult(getRpnExpression(input));
    }

    /**
     * Check the input.
     *
     * @param input input
     */
    public void checkInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }

        Pattern pattern = Pattern.compile("[^\\d-*/^()+]");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            throw new IllegalArgumentException();
        }

        pattern = Pattern.compile("[-*/^+]$");
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            throw new IllegalArgumentException();
        }
    }

    private List<String> getRpnExpression(String input) {
        List<String> rpnExpression = new ArrayList<>();
        Deque<Character> operators = new ArrayDeque<>();
        Map<Character, Integer> priorities = Map.of(
                '(', 0,
                ')', 0,
                '+', 1,
                '-', 1,
                '*', 2,
                '/', 2,
                '^', 3
        );

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(input.substring(i));
                matcher.find();
                String number = matcher.group();
                rpnExpression.add(number);
                i += number.length() - 1;
            }

            if (String.valueOf(input.charAt(i))
                    .matches("[*+\\-/^()]")) {
                if (input.charAt(i) == '(') {
                    operators.push(input.charAt(i));
                    continue;
                }

                if (input.charAt(i) == ')') {
                    char currentOperator = operators.pop();
                    while (currentOperator != '(') {
                        rpnExpression.add(String.valueOf(currentOperator));
                        currentOperator = operators.pop();
                    }
                    continue;
                }

                while (!operators.isEmpty()
                        && priorities.get(input.charAt(i)) < priorities.get(operators.peek())) {
                    rpnExpression.add(String.valueOf(operators.pop()));
                }

                operators.push(input.charAt(i));
            }
        }

        while (!operators.isEmpty()) {
            rpnExpression.add(String.valueOf(operators.pop()));
        }

        return rpnExpression;
    }

    private double getResult(List<String> rpnExpression) {
        Deque<Double> numbers = new ArrayDeque<>();

        for (String string : rpnExpression) {
            if (string.matches("(\\d+)")) {
                numbers.push(Double.parseDouble(string));
                continue;
            }

            double b = numbers.pop();
            double a = numbers.pop();

            if (string.equals("+")) {
                numbers.push(a + b);
            }
            if (string.equals("-")) {
                numbers.push(a - b);
            }
            if (string.equals("*")) {
                numbers.push(a * b);
            }
            if (string.equals("/")) {
                numbers.push(a / b);
            }
            if (string.equals("^")) {
                numbers.push(Math.pow(a, b));
            }
        }

        return numbers.pop();
    }
}
