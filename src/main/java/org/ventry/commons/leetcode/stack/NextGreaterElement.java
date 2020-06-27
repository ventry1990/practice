package org.ventry.commons.leetcode.stack;

/**
 * file: org.ventry.commons.leetcode.stack.NextGreaterElement
 * author: ventry
 * create: 2020/6/27 23:35
 * description:
 */
public class NextGreaterElement {

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        if (len == 0) return nums;

        int max = Integer.MIN_VALUE;
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (max < nums[i]) {
                max = nums[i];
                start = i;
            }
        }

        int[] res = new int[len];
        int[] stack = new int[len + 1];
        int top = -1;
        for (int i = (start + 1) % len; ; i = (i + 1) % len) {
            while (top > -1 && nums[stack[top]] < nums[i]) {
                res[stack[top]] = nums[i];
                top--;
            }

            res[i] = -1;
            top++;
            stack[top] = i;
            if (i == start) break;
        }
        return res;
    }
}
