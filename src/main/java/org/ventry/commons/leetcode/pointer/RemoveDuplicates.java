package org.ventry.commons.leetcode.pointer;

/**
 * file: org.ventry.commons.leetcode.pointer.RemoveDuplicates
 * author: ventry
 * create: 17/11/10 19:48
 * description:
 */

public class RemoveDuplicates {

    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        if (nums.length < 2)
            return nums.length;

        int dup = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                dup++;
            } else {
                nums[i - dup] = nums[i];
            }
        }

        return nums.length - dup;
    }
}
