package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.FirstMissingPositive
 * author: ventry
 * create: 17/11/20 16:13
 * description:
 */

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        int i = 0;
        while (i < len) {
            if (nums[i] > len || nums[i] < 1) {
                i++;
                continue;
            }

            int exchange = nums[i] - 1;
            if (nums[i] == nums[exchange]) {
                i++;
                continue;
            }
            int temp = nums[i];
            nums[i] = nums[exchange];
            nums[exchange] = temp;
        }

        for (i = 0; i < len; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return len + 1;
    }
}
