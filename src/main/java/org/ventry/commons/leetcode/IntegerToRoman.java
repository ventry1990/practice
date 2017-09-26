package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.IntegerToRoman
 * author: ventry
 * create: 17/9/26 21:34
 * description:
 */

public class IntegerToRoman {

    public String intToRoman(int num) {
        int[] decimals = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        StringBuilder result = new StringBuilder();

        while (num != 0) {
            for (int i = decimals.length - 1; i > -1; i--) {
                int t = num - decimals[i];
                if (t >= 0) {
                    num = t;
                    result.append(romans[i]);
                    break;
                }
            }
        }

        return result.toString();
    }
}
