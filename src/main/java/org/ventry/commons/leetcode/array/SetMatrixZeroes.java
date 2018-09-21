package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.SetMatrixZeroes
 * author: ventry
 * create: 2018/9/13 19:35
 * description:
 */

public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        int row, col;
        if (matrix == null || (row = matrix.length) == 0 || (col = matrix[0].length) == 0)
            return;

        boolean firstRow = firstRow(matrix, col);
        boolean firstCol = firstCol(matrix, row);
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < col; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < col; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (firstRow) {
            for (int i = 0; i < col; i++) {
                matrix[0][i] = 0;
            }
        }

        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    private boolean firstRow(int[][] matrix, int col) {
        if (matrix[0][0] == 0) {
            return true;
        } else {
            for (int i = 0; i < col; i++) {
                if (matrix[0][i] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean firstCol(int[][] matrix, int row) {
        if (matrix[0][0] == 0) {
            return true;
        } else {
            for (int i = 0; i < row; i++) {
                if (matrix[i][0] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setZeroes2(int[][] matrix) {
        boolean[] rows = new boolean[matrix.length];
        boolean[] cols = new boolean[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    cols[j] = true;
                    rows[i] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (cols[j] || rows[i]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
