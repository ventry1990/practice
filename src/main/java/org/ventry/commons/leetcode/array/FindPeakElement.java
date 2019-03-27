package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.FindPeakElement
 * author: ventry
 * create: 2019/3/27 16:19
 * description:
 */

public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0)
            return -1;

        int res = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] > nums[i])
                break;
            else
                res = i;
        }
        return res;
    }
}
