package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.CountingBits
 * author: ventry
 * create: 2019-11-21 20:23
 * description:
 */
public class CountingBits {

    public int[] countBits(int num) {
        if (num == 0)
            return new int[1];

        int[] res = new int[num + 1];
        res[1] = 1;
        if (num == 1)
            return res;

        int j = 2;
        res[j] = 1;
        for (int i = 3; i <= num; i++) {
            if (i == j * 2) {
                res[i] = 1;
                j = i;
            } else {
                res[i] = res[i - j] + 1;
            }
        }
        return res;
    }
}
