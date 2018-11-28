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

    public int[][] generateMatrix(int n) {
        if (n == 1)
            return new int[][]{{1}};

        int[][] res = new int[n][n];
        int total = n * n, row = 0, col = 0, rowStart = 0, colStart = -1;
        boolean left = false, right = true, down = false;
        for (int i = 1; i <= total; i++) {
            res[row][col] = i;
            if (right) {
                col++;
                if (col == n) {
                    col--;
                    row++;
                    right = false;
                    down = true;
                }
            } else if (down) {
                row++;
                if (row == n) {
                    row--;
                    col--;
                    down = false;
                    left = true;
                }
            } else if (left) {
                col--;
                if (col == colStart) {
                    col++;
                    i--;
                    left = false;
                }
            } else {
                row--;
                if (row == rowStart) {
                    row++;
                    col = row;
                    rowStart = row;
                    colStart++;
                    n--;
                    right = true;
                }
            }
        }
        return res;
    }

    public int[][] generateMatrix2(int n) {
        int[][] res = new int[n][n];
        int x = 1;
        int c1 = 0, c2 = n - 1;
        int r1 = 0, r2 = n - 1;
        while (r1 <= r2 && c1 <= c2) {
            //go right
            for (int i = c1; i <= c2; i++) {
                res[r1][i] = x++;
            }
            //go down
            for (int i = r1 + 1; i <= r2; i++) {
                res[i][c2] = x++;
            }
            //go left
            for (int i = c2 - 1; i >= c1; i--) {
                res[r2][i] = x++;
            }
            //go up
            for (int i = r2 - 1; i > r1; i--) {
                res[i][c1] = x++;
            }
            c1++;
            c2--;
            r1++;
            r2--;
        }
        return res;
    }
}
