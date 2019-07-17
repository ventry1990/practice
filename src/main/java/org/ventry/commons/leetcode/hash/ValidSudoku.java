package org.ventry.commons.leetcode.hash;

/**
 * file: org.ventry.commons.leetcode.hash.ValidSudoku
 * author: ventry
 * create: 2019-07-17 11:16
 * description:
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] row = new int[10];
            int[] col = new int[10];
            for (int j = 0; j < 9; j++) {
                int num1 = Math.max(board[i][j] - '0', 0);
                int num2 = Math.max(board[j][i] - '0', 0);
                if ((num1 > 0 && row[num1] == 1)
                        || (num2 > 0 && col[num2] == 1)) return false;

                row[num1] = col[num2] = 1;
            }
        }

        int[] block1 = new int[10], block2 = new int[10], block3 = new int[10];
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                block1 = new int[10];
                block2 = new int[10];
                block3 = new int[10];
            }
            for (int j = 0; j < 3; j++) {
                int num1 = Math.max(board[i][j] - '0', 0);
                int num2 = Math.max(board[i][j + 3] - '0', 0);
                int num3 = Math.max(board[i][j + 6] - '0', 0);

                if ((num1 > 0 && block1[num1] == 1)
                        || (num2 > 0 && block2[num2] == 1)
                        || (num3 > 0 && block3[num3] == 1))
                    return false;

                block1[num1] = block2[num2] = block3[num3] = 1;
            }
        }

        return true;
    }
}
