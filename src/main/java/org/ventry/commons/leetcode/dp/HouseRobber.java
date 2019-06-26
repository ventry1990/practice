package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.HouseRobber
 * author: ventry
 * create: 2019/6/12 20:19
 * description:
 */

public class HouseRobber {

    public int rob(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0) return 0;
        if (n == 1) return nums[0];

        nums[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            nums[i] = Math.max(nums[i - 2] + nums[i], nums[i - 1]);
        }
        return nums[n - 1];
    }

    public int _rob2(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0) return 0;
        if (n == 1) return nums[0];

        int res = 0;
        for (int i = 0; i < n; i++) {
            int[] dp = new int[n];
            dp[0] = nums[i];
            dp[1] = Math.max(nums[i], nums[(i + 1) % n]);
            // 假定不偷第 n-1 家，使环退化成链表
            for (int j = 2, k = i + 2; j < n - 1; j++, k++) {
                dp[j] = Math.max(dp[j - 2] + nums[k % n], dp[j - 1]);
            }
            res = Math.max(res, dp[n - 2]);
        }
        return res;
    }

    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int robFirst = nums[0] + rob(nums, 2, nums.length - 2);
        int notRobFirst = rob(nums, 1, nums.length - 1);
        return Math.max(robFirst, notRobFirst);
    }

    private int rob(int[] arr, int s, int e) {
        int robPrevious = 0, notRobPrevious = 0;
        for (int i = s; i <= e; i++) {
            int robCurrent = notRobPrevious + arr[i];
            int notRobCurrent = Math.max(robPrevious, notRobPrevious);
            robPrevious = robCurrent;
            notRobPrevious = notRobCurrent;
        }
        return Math.max(robPrevious, notRobPrevious);
    }
}
