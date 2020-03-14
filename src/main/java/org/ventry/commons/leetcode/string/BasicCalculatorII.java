package org.ventry.commons.leetcode.string;

import java.util.LinkedList;

/**
 * file: org.ventry.commons.leetcode.string.BasicCalculatorII
 * author: ventry
 * create: 2020/3/14 15:13
 * description:
 */
public class BasicCalculatorII {

    public int calculate(String s) {
        int len = s.length();
        int[] expressionStack = new int[len];
        char[] operatorStack = new char[len];
        int opCursor = -1;
        int expCursor = -1;
        for (int i = 0; i < len; i++) {
            char operator = s.charAt(i);
            if (operator == ' ') continue;

            if (operator >= '0' && operator <= '9') {
                int j = i;
                while (j < len && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                expressionStack[++expCursor] = Integer.parseInt(s.substring(i, j));
                i = j - 1;
            } else if (operator == '(') {
                operatorStack[++opCursor] = '(';
            } else if (operator == ')') {
                char topOperator;
                while (opCursor > -1 && (topOperator = operatorStack[opCursor]) != '(') {
                    int right = expressionStack[expCursor--];
                    int left = expressionStack[expCursor];
                    expressionStack[expCursor] = calculate(left, right, topOperator);
                    opCursor--;
                }
                opCursor--;
            } else {
                while (opCursor > -1 && !hasHighPriority(operator, operatorStack[opCursor])) {
                    int right = expressionStack[expCursor--];
                    int left = expressionStack[expCursor];
                    expressionStack[expCursor] = calculate(left, right, operatorStack[opCursor]);
                    opCursor--;
                }
                operatorStack[++opCursor] = operator;
            }
        }

        while (opCursor > -1) {
            int right = expressionStack[expCursor--];
            int left = expressionStack[expCursor];
            expressionStack[expCursor] = calculate(left, right, operatorStack[opCursor]);
            opCursor--;
        }

        return expCursor < 0 ? 0 : expressionStack[expCursor];
    }

    private int calculate(int left, int right, char op) {
        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            default:
                throw new IllegalStateException("unknown op");
        }
    }

    /**
     * https://blog.csdn.net/lcl497049972/article/details/83061274
     */
    private LinkedList<String> transformRPN(String s) {
        LinkedList<String> expressionStack = new LinkedList<>();
        char[] operatorStack = new char[s.length()];
        int cursor = -1;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char operator = s.charAt(i);
            if (operator == ' ') continue;

            if (operator >= '0' && operator <= '9') {
                int j = i;
                while (j < len && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                expressionStack.addLast(s.substring(i, j));
                i = j - 1;
            } else if (operator == '(') {
                operatorStack[++cursor] = '(';
            } else if (operator == ')') {
                char topOperator;
                while (cursor > -1 && (topOperator = operatorStack[cursor]) != '(') {
                    expressionStack.addLast(String.valueOf(topOperator));
                    cursor--;
                }
                cursor--;
            } else {
                while (cursor > -1 && !hasHighPriority(operator, operatorStack[cursor])) {
                    expressionStack.addLast(String.valueOf(operatorStack[cursor]));
                    cursor--;
                }
                operatorStack[++cursor] = operator;
            }
        }

        while (cursor > -1) {
            expressionStack.addLast(String.valueOf(operatorStack[cursor]));
            cursor--;
        }

        return expressionStack;
    }

    private static boolean hasHighPriority(char left, char right) {
        return right == '('
                || ((left == '*' || left == '/') && (right == '+' || right == '-'));
    }
}
