package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.RemoveDuplicates
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

    public int removeDuplicatesII(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0)
            return 0;

        int dup = 0;
        int prev = nums[0];
        int moved = 0;
        for (int i = 1; i < n; i++) {
            if (prev == nums[i]) {
                prev = nums[i];
                dup++;
                nums[i - moved] = nums[i];
                if (dup > 1) {
                    moved++;
                }
            } else {
                prev = nums[i];
                dup = 0;
                nums[i - moved] = nums[i];
            }
        }

        return n - moved;
    }

    public int removeDuplicatesII2(int[] nums) {
        int idx = 0;
        for (int n : nums) {
            if (idx < 2 || n != nums[idx - 2]) {
                nums[idx++] = n;
            }
        }

        return idx;
    }
}
