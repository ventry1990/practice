package org.ventry.commons.leetcode.dfs;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.dfs.TargetSum
 * author: ventry
 * create: 18/7/31 17:21
 * description:
 */

public class TargetSum {

    public int findTargetSumWays(int[] nums, int tar) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int delta;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum < tar || (delta = sum - tar) % 2 != 0) {
            return 0;
        }

        Arrays.sort(nums);
        int offset = 0;
        while (offset < nums.length && nums[offset] == 0) {
            offset++;
        }
        return search(nums, offset, new int[nums.length], 0, delta / 2) * (int) Math.pow(2, offset);
    }

    private int search(int[] nums, int offset, int[] cache, int len, int tar) {
        if (tar == 0) {
            return 1;
        }

        int r = 0;
        for (int i = offset; i < nums.length; i++) {
            if (nums[i] > tar) continue;

            cache[len] = i;
            r += search(nums, i + 1, cache, len + 1, tar - nums[i]);
        }
        return r;
    }

    /**
     * https://blog.csdn.net/hit0803107/article/details/54894227
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        return sum < S || (sum + S) % 2 > 0 ? 0 : subsetSum(nums, (S + sum) >>> 1);
    }

    private int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums) {
            for (int i = s; i >= n; i--) {
                dp[i] += dp[i - n];
            }
        }
        return dp[s];
    }
}