package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.RotateMatrix
 * author: ventry
 * create: 17/12/8 17:40
 * description:
 */

public class RotateMatrix {

    public void rotate(int[][] matrix) {
        int len = matrix.length;
        for (int i = 0; i < len / 2; i++) {
            int end = len - 1 - i;
            for (int j = i; j < end; j++) {
                swap(matrix, i, j);
            }
        }
    }

    private static void swap(int[][] matrix, int x, int y) {
        int newX = x;
        int newY = y;
        int tmp;
        for (int i = 0; i < 4; i++) {
            int t = matrix.length - 1 - newX;
            newX = newY;
            newY = t;
            tmp = matrix[newX][newY];
            matrix[newX][newY] = matrix[x][y];
            matrix[x][y] = tmp;
        }
    }
}
