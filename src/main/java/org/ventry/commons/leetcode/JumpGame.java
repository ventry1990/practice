package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.JumpGame
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
}
