package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.UniquePaths
 * author: ventry
 * create: 18/7/19 15:26
 * description:
 */

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int[] prev = new int[m];
        int[] cur = new int[m];
        for (int i = 0; i < m; i++) {
            prev[i] = 1;
            cur[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                cur[j] = prev[j] + cur[j - 1];
                prev[j] = cur[j];
            }
        }

        return cur[m - 1];
    }
}
