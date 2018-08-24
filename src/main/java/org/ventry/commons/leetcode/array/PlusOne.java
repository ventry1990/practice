package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.PlusOne
 * author: ventry
 * create: 2018/8/24 12:52
 * description:
 */

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int delta = 1;
        for (int i = digits.length - 1; i > -1; i--) {
            digits[i] += delta;
            delta = digits[i] / 10;
            digits[i] %= 10;
            if (delta == 0) break;
        }

        if (delta == 1) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            System.arraycopy(digits, 0, res, 1, digits.length);
            return res;
        } else {
            return digits;
        }
    }
}
