package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.MaximalSquare
 * author: ventry
 * create: 2019/2/24 19:36
 * description:
 */

public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int rows, columns;
        if (matrix == null || (rows = matrix.length) == 0
                || matrix[0] == null || (columns = matrix[0].length) == 0) {
            return 0;
        }

        int[] heights = new int[columns + 1];
        int maxArea = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (matrix[row][col] == '1') {
                    heights[col]++;
                } else {
                    heights[col] = 0;
                }
            }
            maxArea = Math.max(maxArea, maxArea(heights));
        }
        return maxArea;
    }

    private int maxArea(int[] heights) {
        int n = heights.length;
        int[] stack = new int[n + 1];
        int top = 0;
        stack[top] = -1;
        int area = 0;
        for (int i = 0; i < n; i++) {
            while (top > 0 && heights[i] < heights[stack[top]]) {
                int h = heights[stack[top--]];
                int w = i - 1 - stack[top];
                int l = Math.min(h, w);
                area = Math.max(area, l * l);
                if (h < w) break;
            }
            stack[++top] = i;
        }
        return area;
    }
}