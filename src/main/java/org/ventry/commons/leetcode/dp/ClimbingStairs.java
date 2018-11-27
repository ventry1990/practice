package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.ClimbingStairs
 * author: ventry
 * create: 2018/11/27 18:59
 * description:
 */

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }
}
