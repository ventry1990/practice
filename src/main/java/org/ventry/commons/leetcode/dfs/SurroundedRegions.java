package org.ventry.commons.leetcode.dfs;

/**
 * file: org.ventry.commons.leetcode.dfs.SurroundedRegions
 * author: ventry
 * create: 2020/4/7 17:02
 * description:
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        int r, c;
        if (board == null || (r = board.length) < 2 || (c = board[0].length) < 2)
            return;

        boolean[][] visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            travel(board, visited, i, 0);
            travel(board, visited, i, c - 1);
        }

        for (int i = 0; i < c; i++) {
            travel(board, visited, 0, i);
            travel(board, visited, r - 1, i);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] = visited[i][j] ? 'O' : 'X';
            }
        }
    }

    private void travel(char[][] board, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
                || visited[r][c] || board[r][c] == 'X') return;

        visited[r][c] = true;
        travel(board, visited, r + 1, c);
        travel(board, visited, r, c + 1);
        travel(board, visited, r - 1, c);
        travel(board, visited, r, c - 1);
    }
}
