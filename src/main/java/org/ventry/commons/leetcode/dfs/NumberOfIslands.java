package org.ventry.commons.leetcode.dfs;

/**
 * file: org.ventry.commons.leetcode.dfs.NumberOfIslands
 * author: ventry
 * create: 2019/3/12 19:06
 * description:
 */

public class NumberOfIslands {

    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return res;

        boolean[][] marked = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (marked[i][j]) continue;

                if (grid[i][j] == '1') {
                    travel(grid, i, j, marked);
                    res++;
                } else {
                    marked[i][j] = true;
                }
            }
        }
        return res;
    }

    private void travel(char[][] grid, int i, int j, boolean[][] marked) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0) return;
        if (marked[i][j]) return;
        marked[i][j] = true;
        if (grid[i][j] == '0') return;

        travel(grid, i, j + 1, marked);
        travel(grid, i + 1, j, marked);
        travel(grid, i, j - 1, marked);
        travel(grid, i - 1, j, marked);
    }
}
