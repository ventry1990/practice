package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.IntegerBreak
 * author: ventry
 * create: 2020/3/31 16:56
 * description:
 */
public class IntegerBreak {

    public int integerBreak(int n) {
        if (n < 1) return 0;

        // dp[i] = i 的最大乘积
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            // j < i -> j <= i/2
            for (int j = 1; j < i; j++) {
                // 比较切分在 j 时，比较 i - j 与 dp[i - j] 最大值
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }
        return dp[n];
    }
}
