package org.ventry.commons.leetcode.backtracking;

import org.ventry.commons.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.backtracking.NQueens
 * author: ventry
 * create: 2018/10/6 14:40
 * description:
 */

public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        if (n == 1) {
            return Collections.singletonList(
                    Collections.singletonList("Q"));
        }
        if (n < 4) {
            return Collections.emptyList();
        }

        List<List<String>> res = new ArrayList<>();
        int[] pos = new int[n];
        lay(pos, 0, n, res);
        return res;
    }

    private void lay(int[] pos, int i, int n, List<List<String>> res) {
        if (i == n) {
            res.add(build(pos, n));
            return;
        }

        for (int j = 0; j < n; j++) {
            pos[i] = j + 1;
            if (isValid(pos, i)) {
                lay(pos, i + 1, n, res);
            }
        }
    }

    private boolean isValid(int[] pos, int i) {
        for (int j = 0; j < i; j++) {
            if (Math.abs(pos[i] - pos[j]) == Math.abs(i - j)
                    || pos[i] == pos[j])
                return false;
        }
        return true;
    }

    private List<String> build(int[] pos, int n) {
        List<String> sol = new ArrayList<>(n);
        char[] line = new char[n + 1];
        Arrays.fill(line, '.');
        line[pos[0]] = 'Q';
        sol.add(new String(line, 1, n));
        for (int i = 1; i < n; i++) {
            ArrayUtils.swap(line, pos[i - 1], pos[i]);
            sol.add(new String(line, 1, n));
        }
        return sol;
    }


    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        boolean[] cols = new boolean[n];
        boolean[] d_minus = new boolean[2 * n - 1];
        boolean[] d_plus = new boolean[2 * n - 1];
        backtracking(res, board, 0, cols, d_minus, d_plus);
        return res;
    }

    private void backtracking(List<List<String>> res, char[][] board, int r,
                              boolean[] cols, boolean[] d_plus, boolean[] d_minus) {
        if (r == board.length) {
            List<String> sol = new ArrayList<>();
            for (char[] row : board) {
                sol.add(new String(row));
            }
            res.add(sol);
        }
        for (int c = 0; c < board[0].length; c++) {
            int d_p = r + c;
            int d_m = r - c + board.length - 1;
            if (!cols[c] && !d_plus[d_p] && !d_minus[d_m]) {
                board[r][c] = 'Q';
                cols[c] = true;
                d_plus[d_p] = true;
                d_minus[d_m] = true;
                backtracking(res, board, r + 1, cols, d_plus, d_minus);
                board[r][c] = '.';
                cols[c] = false;
                d_plus[d_p] = false;
                d_minus[d_m] = false;
            }
        }
    }

    public int totalNQueens(int n) {
        boolean[] cols = new boolean[n];
        boolean[] d_plus = new boolean[2 * n - 1];// 正对角线
        boolean[] d_minus = new boolean[2 * n - 1];// 反对角线
        int[] res = new int[1];
        backtracking(res, 0, n, cols, d_minus, d_plus);
        return res[0];
    }

    private void backtracking(int[] res, int row, int n,
                              boolean[] cols, boolean[] d_plus, boolean[] d_minus) {
        if (row == n) {
            res[0]++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int d_p = row + col;
            int d_m = row - col + n - 1;
            if (!cols[col] && !d_plus[d_p] && !d_minus[d_m]) {
                cols[col] = true;
                d_plus[d_p] = true;
                d_minus[d_m] = true;
                backtracking(res, row + 1, n, cols, d_plus, d_minus);
                cols[col] = false;
                d_plus[d_p] = false;
                d_minus[d_m] = false;
            }
        }
    }
}
