package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.PartitionEqualSubsetSum
 * author: ventry
 * create: 2020/10/18 23:31
 * description:
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        if ((sum & 0x01) == 1) return false;
        int target = sum >>> 1;
        if (target < max) return false;

        // dp[i][j] 表示从 nums 的 [0, i] 下标范围内选取若干个数（可以为0），
        // 是否存在一种选取方案使得被选取之和等于 j
        boolean[][] dp = new boolean[nums.length][target + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    public boolean _canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 0x01) == 1) return false;
        int target = sum >>> 1;
        if (target < nums[nums.length - 1]) return false;
        return dfs(nums, 0, target);
    }

    private boolean dfs(int[] nums, int idx, int target) {
        if (target == 0) return true;
        if (target < 0) return false;
        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) continue;
            if (dfs(nums, i + 1, target - nums[i])) return true;
        }
        return false;
    }
}
