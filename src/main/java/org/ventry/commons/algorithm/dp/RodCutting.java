package org.ventry.commons.algorithm.dp;

import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.algorithm.dp.RodCutting
 * author: ventry
 * create: 2017/8/15
 * description:
 */

public class RodCutting {
    private int[] prices;

    private RodCutting(int[] p) {
        this.prices = p;
    }

    private int cut(int length) {
        int[] result = new int[length + 1];
        result[0] = 0;

        for (int i = 1; i <= length; i++) {
            int temp = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                temp = Math.max(temp, prices[j - 1] + result[i - j]);
            }
            result[i] = temp;
        }
        return result[length];
    }

    public static void main(String[] args) {
        RodCutting rodCutting = new RodCutting(new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30});
        for (int i = 0; i < 11; i++) {
            Console.writeLine(rodCutting.cut(i));
        }
    }
}
