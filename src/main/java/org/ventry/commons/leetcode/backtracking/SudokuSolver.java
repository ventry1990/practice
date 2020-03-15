package org.ventry.commons.leetcode.backtracking;

/**
 * file: org.ventry.commons.leetcode.backtracking.SudokuSolver
 * author: ventry
 * create: 2020/3/15 23:32
 * description:
 */
public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] columns = new boolean[9][10];
        boolean[][] blocks = new boolean[9][10];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '0';
                rows[i][num] = true;
                columns[j][num] = true;
                // i/3*3 = 0|3|6, j/3 = 0|1|2
                blocks[i / 3 * 3 + j / 3][num] = true;
            }
        }
        solve(board, 0, 0, rows, columns, blocks);
    }

    private boolean solve(char[][] board, int i, int j,
                          boolean[][] rows, boolean[][] columns, boolean[][] blocks) {
        if (i > 8) return true;
        if (board[i][j] != '.') {
            return solve(board, j == 8 ? i + 1 : i, (j + 1) % 9, rows, columns, blocks);
        } else {
            for (int num = 1; num < 10; num++) {
                if (rows[i][num] || columns[j][num] || blocks[i / 3 * 3 + j / 3][num]) continue;

                rows[i][num] = true;
                columns[j][num] = true;
                blocks[i / 3 * 3 + j / 3][num] = true;
                board[i][j] = (char) (num + '0');
                if (solve(board, j == 8 ? i + 1 : i, (j + 1) % 9, rows, columns, blocks))
                    return true;
                board[i][j] = '.';
                rows[i][num] = false;
                columns[j][num] = false;
                blocks[i / 3 * 3 + j / 3][num] = false;
            }
        }
        return false;
    }
}
