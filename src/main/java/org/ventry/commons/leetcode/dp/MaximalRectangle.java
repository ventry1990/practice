package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.MaximalRectangle
 * author: ventry
 * create: 18/6/8 18:30
 * description:
 */

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length, columns = matrix[0].length;
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
                area = Math.max(area, h * (i - stack[top] - 1));
            }
            stack[++top] = i;
        }
        return area;
    }

    public int _maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        char[][] input = prepare(matrix);
        int[][] width = new int[input.length][input[0].length];
        int[][] height = new int[input.length][input[0].length];
        int[][] recWidth = new int[input.length][input[0].length];
        int[][] recHeight = new int[input.length][input[0].length];
        int[][] area = new int[input.length][input[0].length];
        int maxArea = 0;
        for (int i = 1; i < input.length; i++) {
            for (int j = 1; j < input[0].length; j++) {
                if (input[i][j] == '0') continue;

                width[i][j] = width[i][j - 1] + 1;
                height[i][j] = height[i - 1][j] + 1;
                if (input[i - 1][j] == '0') {
                    area[i][j] = recWidth[i][j] = width[i][j];
                    recHeight[i][j] = 1;
                } else if (input[i][j - 1] == '0') {
                    area[i][j] = recHeight[i][j] = height[i][j];
                    recWidth[i][j] = 1;
                } else {
                    int w = Math.min(width[i][j], recWidth[i - 1][j]);
                    int h = Math.min(height[i][j], recHeight[i][j - 1]);
                    int a = area[i - 1][j] + w
                            - Math.max(0, recWidth[i - 1][j] - width[i][j]) * recHeight[i - 1][j];
                    int b = area[i][j - 1] + h
                            - Math.max(0, recHeight[i][j - 1] - height[i][j]) * recWidth[i][j - 1];
                    if (a >= b) {
                        area[i][j] = a;
                        recWidth[i][j] = w;
                        recHeight[i][j] = recHeight[i - 1][j] + 1;
                    } else {
                        area[i][j] = b;
                        recWidth[i][j] = recWidth[i][j - 1] + 1;
                        recHeight[i][j] = h;
                    }
                }
                maxArea = Math.max(maxArea, area[i][j]);
            }
        }

        return maxArea;
    }

    private char[][] prepare(char[][] matrix) {
        char[][] input = new char[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < input[0].length; i++) {
            input[0][i] = '0';
        }

        for (int i = 0; i < matrix.length; i++) {
            input[i + 1][0] = '0';
            System.arraycopy(matrix[i], 0, input[i + 1], 1, matrix[i].length);
        }
        return input;
    }
}