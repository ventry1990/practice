package org.ventry.commons.leetcode.backtracking;

/**
 * file: org.ventry.commons.leetcode.backtracking.WordSearch
 * author: ventry
 * create: 2018/8/24 15:39
 * description:
 */

public class WordSearch {

    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0)
            return true;

        int m, n;
        if (board == null || (m = board.length) == 0
                || board[0] == null || (n = board[0].length) == 0)
            return false;

        char[][] newBoard = new char[m + 2][n + 2];
        for (int i = 0; i < m; i++) {
            System.arraycopy(board[i], 0, newBoard[i + 1], 1, n);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (find(newBoard, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, String word, int c, int x, int y) {
        if (c == word.length()) {
            return true;
        }

        if (word.charAt(c) != board[x][y]) {
            return false;
        }

        board[x][y] = '\0';
        int next = c + 1;
        boolean res = find(board, word, next, x, y + 1)
                || find(board, word, next, x + 1, y)
                || find(board, word, next, x, y - 1)
                || find(board, word, next, x - 1, y);
        board[x][y] = word.charAt(c);
        return res;
    }
}
