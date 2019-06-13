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
}
