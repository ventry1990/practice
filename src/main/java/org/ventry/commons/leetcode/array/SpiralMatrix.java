package org.ventry.commons.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.array.SpiralMatrix
 * author: ventry
 * create: 2018/10/6 15:47
 * description:
 */

public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return Collections.emptyList();
        }

        List<Integer> res = new ArrayList<>(matrix.length * matrix[0].length);
        round(res, matrix, 0, 0, matrix[0].length - 1, matrix.length - 1);
        return res;
    }

    private void round(List<Integer> res, int[][] matrix,
                       int left, int top, int right, int bottom) {
        if (top == bottom) {
            horizontal(res, matrix, left, right, top);
        }

        if (left == right) {
            vertical(res, matrix, top, bottom, left);
        }

        if (res.size() == matrix.length * matrix[0].length) {
            return;
        }

        for (int i = left; i < right; i++) {
            res.add(matrix[top][i]);
        }

        for (int i = top; i < bottom; i++) {
            res.add(matrix[i][right]);
        }

        for (int i = right; i > left; i--) {
            res.add(matrix[bottom][i]);
        }

        for (int i = bottom; i > top; i--) {
            res.add(matrix[i][left]);
        }

        round(res, matrix, left + 1, top + 1, right - 1, bottom - 1);
    }

    private void vertical(List<Integer> res, int[][] matrix,
                          int top, int bottom, int col) {
        if (res.size() == matrix.length * matrix[0].length) {
            return;
        }

        for (int i = top; i <= bottom; i++) {
            res.add(matrix[i][col]);
        }
    }

    private void horizontal(List<Integer> res, int[][] matrix,
                            int left, int right, int row) {
        if (res.size() == matrix.length * matrix[0].length) {
            return;
        }

        for (int i = left; i <= right; i++) {
            res.add(matrix[row][i]);
        }
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return list;
        int m = matrix.length, n = matrix[0].length, i = 0, j = 0, rowStart = 0, colStart = -1, total = m * n;
        boolean left = false, right = true, down = false;

        while (total > 0) {
            list.add(matrix[i][j]);

            if (right) {
                j++;
                if (j == n) {
                    j--;
                    i++;
                    right = false;
                    down = true;
                }
            } else if (down) {
                i++;
                if (i == m) {
                    i--;
                    j--;
                    down = false;
                    left = true;
                }
            } else if (left) {
                j--;
                if (j == colStart) {
                    j++;
                    i--;
                    left = false;
                }
            } else {
                i--;
                if (i == rowStart) {
                    i++;
                    j = i;
                    rowStart = i;
                    colStart++;
                    right = true;
                    m--;
                    n--;
                }
            }
            total--;
        }
        return list;
    }
}
