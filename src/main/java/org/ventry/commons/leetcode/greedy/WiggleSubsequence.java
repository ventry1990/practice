package org.ventry.commons.leetcode.greedy;

/**
 * file: org.ventry.commons.leetcode.greedy.WiggleSubsequence
 * author: ventry
 * create: 18/8/16 21:17
 * description:
 */

public class WiggleSubsequence {

    public int wiggleMaxLength(int[] nums) {
        int n;
        if (nums == null || (n = nums.length) == 0)
            return 0;
        if (n == 1)
            return 1;

        int[] stack = new int[n + 1];
        int top = -1;
        stack[++top] = nums[0];
        int i = 1;
        while (i < n && nums[i - 1] == nums[i]) {
            i++;
        }

        if (i < n) {
            stack[++top] = nums[i];
        }

        while (i < n) {
            if ((stack[top] - stack[top - 1]) * (nums[i] - stack[top]) < 0) {
                stack[++top] = nums[i++];
            } else {
                stack[top] = nums[i++];
            }
        }
        return top + 1;
    }
}