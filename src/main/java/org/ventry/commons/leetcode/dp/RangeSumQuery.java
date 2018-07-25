package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.RangeSumQuery
 * author: ventry
 * create: 18/7/25 19:39
 * description:
 */

public class RangeSumQuery {
    private int[] dp;

    public RangeSumQuery(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0) return;

        dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return dp[j];
        } else {
            return dp[j] - dp[i - 1];
        }
    }
}