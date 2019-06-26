package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.PerfectSquares
 * author: ventry
 * create: 2019/6/26 17:02
 * description:
 */

public class PerfectSquares {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j < i; j++) {
                if (j * j == i) {
                    dp[i] = 1;
                    break;
                } else {
                    dp[i] = Math.min(dp[j] + dp[i - j], dp[i]);
                }
            }
        }
        return dp[n];
    }

    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 1, k = i + j * j; k <= n; j++, k = i + j * j) {
                if (dp[k] == 0) dp[k] = k;
                dp[k] = Math.min(dp[k], dp[i] + 1);
            }
        }
        return dp[n];
    }

    /**
     * four-square theorem
     */
    public int numSquares3(int n) {
        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt == n) return 1;
        while (n % 4 == 0) n >>= 2;
        if (n % 8 == 7) return 4;
        sqrt = (int) Math.sqrt(n);
        for (int i = 1; i <= sqrt; ++i) {
            int sec = n - i * i;
            int s = (int) Math.sqrt(sec);
            if (s * s == sec) return 2;
        }
        return 3;
    }
}
