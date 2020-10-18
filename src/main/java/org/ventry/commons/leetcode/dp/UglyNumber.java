package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.UglyNumber
 * author: ventry
 * create: 2020/10/18 23:05
 * description:
 */
public class UglyNumber {
    public int nthUglyNumber(int n) {
        if (n < 1) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int idxOfFactor2 = 1;
        int idxOfFactor3 = 1;
        int idxOfFactor5 = 1;
        for (int i = 2; i <= n; ) {
            int multi2 = dp[idxOfFactor2] * 2;
            int multi3 = dp[idxOfFactor3] * 3;
            int multi5 = dp[idxOfFactor5] * 5;
            int num = Math.min(multi2, Math.min(multi3, multi5));
            if (multi2 == num) idxOfFactor2++;
            if (multi3 == num) idxOfFactor3++;
            if (multi5 == num) idxOfFactor5++;
            if (dp[i] == 0) {
                dp[i] = num;
                i++;
            }
        }
        return dp[n];
    }
}
