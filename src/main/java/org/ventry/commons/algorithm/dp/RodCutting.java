package org.ventry.commons.algorithm.dp;

import org.ventry.commons.utils.Console;

import java.util.StringJoiner;

/**
 * file: org.ventry.commons.algorithm.dp.RodCutting
 * author: ventry
 * create: 2017/8/15
 * description:
 */

public class RodCutting {
    private int[] prices;
    private int[] solution;

    private RodCutting(int[] prices) {
        this.prices = prices;
    }

    private int cut(int length) {
        int[] result = new int[length + 1];
        solution = new int[length + 1];
        result[0] = 0;

        for (int i = 1; i <= length; i++) {
            int maxProfit = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                // When `length` great than `prices.length`, than means `length's cutting` can be
                // divided into several sub-questions, and each sub-question's input is less than
                // or equals to `prices.length` and the sum of them equals to `length`.
                int curProfit = prices[j - 1] + result[i - j];
                if (maxProfit < curProfit) {
                    maxProfit = curProfit;
                    solution[i] = j;
                }
            }
            result[i] = maxProfit;
        }

        printSolution(length);
        return result[length];
    }

    private void printSolution(int length) {
        StringJoiner joiner = new StringJoiner("|");
        while (length > 0) {
            joiner.add(String.valueOf(solution[length]));
            length = length - solution[length];
        }
        Console.writeLine("solution: " + joiner.toString());
    }

    public static void main(String[] args) {
        int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        RodCutting rodCutting = new RodCutting(p);
        for (int i = 0; i <= p.length; i++) {
            Console.writeLine("length: " + i);
            Console.writeLine("profit: " + rodCutting.cut(i));
            Console.writeLine();
        }
    }
}
