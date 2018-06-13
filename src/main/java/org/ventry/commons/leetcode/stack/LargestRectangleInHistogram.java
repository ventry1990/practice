package org.ventry.commons.leetcode.stack;

/**
 * file: org.ventry.commons.leetcode.stack.LargestRectangleInHistogram
 * author: ventry
 * create: 18/6/13 16:01
 * description:
 */

public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {
        int area = 0;
        if (heights == null || heights.length == 0) {
            return area;
        }

        int len = heights.length + 1;
        int[] copy = new int[len];
        System.arraycopy(heights, 0, copy, 0, heights.length);
        int[] stack = new int[len + 1];
        int top = 0;
        stack[top] = -1;
        for (int i = 0; i < len; i++) {
            while (top > 0 && copy[i] < copy[stack[top]]) {
                int height = copy[stack[top--]];
                area = Math.max(area, height * (i - stack[top] - 1));
            }

            stack[++top] = i;
        }

        return area;
    }
}