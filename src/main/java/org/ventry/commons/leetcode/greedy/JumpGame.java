package org.ventry.commons.leetcode.greedy;

/**
 * file: org.ventry.commons.leetcode.greedy.JumpGame
 * author: ventry
 * create: 17/12/4 18:18
 * description:
 */

public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums[0] >= nums.length)
            return true;

        int s = 0;
        int e = s + nums[s] + 1;
        for (int i = s; i < e && e <= nums.length; i++) {
            if (nums[i] + i + 1 > e)
                e = nums[i] + i + 1;
        }

        return e >= nums.length;
    }

    public int jump(int[] nums) {
        if (nums.length < 2) return 0;

        int s = 0;
        int e = s + nums[s] + 1;
        int max = e;
        int step = 1;
        for (int i = s; i < e && e < nums.length; i++) {
            if (nums[i] + i + 1 > max) {
                max = nums[i] + i + 1;
            }

            if (i == e - 1 && max > e) {
                e = max;
                step++;
            }
        }

        return e >= nums.length ? step : 0;
    }

    public int jump2(int[] nums) {
        if (nums.length < 2) return 0;

        int curMax = nums[0];
        int nextMax = nums[0];
        int curStep = 1;
        for (int i = 1; i < nums.length; i++) {
            if (i > curMax) {
                curMax = nextMax;
                curStep++;
            }

            if (i + nums[i] > nextMax)
                nextMax = i + nums[i];
        }
        return curStep;
    }
}
