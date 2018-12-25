package org.ventry.commons.leetcode.tree;

/**
 * file: org.ventry.commons.leetcode.tree.UniqueBinarySearchTrees
 * author: ventry
 * create: 2018/12/25 13:04
 * description:
 */

public class UniqueBinarySearchTrees {

    public int numTrees(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;

        // 卡兰塔数，递归公式：C_0 = 1 and \sum_{i=0}^{n} {{C_i}{C_{n-i}}}
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int x = i / 2;
            for (int j = 0; j < x; j++) {
                dp[i] += (2 * dp[j] * dp[i - j - 1]);
            }
            if (i % 2 == 1) {
                dp[i] += (dp[x] * dp[x]);
            }
        }
        return dp[n];
    }
}
