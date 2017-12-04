package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.MultiplyStrings
 * author: ventry
 * create: 17/12/4 15:09
 * description:
 */

public class MultiplyStrings {

    public String multiply(String num1, String num2) {
        int num1Length = num1.length();
        int num2Length = num2.length();
        int[] res = new int[num1Length + num2Length];

        for (int i = num1Length - 1; i > -1; i--) {
            int a = num1.charAt(i) - 48;
            for (int j = num2Length - 1; j > -1; j--) {
                int b = num2.charAt(j) - 48;
                int d = i + j + 1;
                res[d] += a * b;

                if (res[d] > 9) {
                    res[d - 1] += res[d] / 10;
                    res[d] = res[d] % 10;
                }
            }
        }

        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }

        StringBuilder builder = new StringBuilder();
        for (; i < res.length; i++) {
            builder.append(res[i]);
        }
        return builder.length() == 0 ? "0" : builder.toString();
    }
}
