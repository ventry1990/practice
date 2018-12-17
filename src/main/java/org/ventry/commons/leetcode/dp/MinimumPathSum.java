package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.MinimumPathSum
 * author: ventry
 * create: 2018/12/17 16:34
 * description:
 */

public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int m, n;
        if (grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0)
            return 0;

        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int minPathSum2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (i > 0 ? dp[i - 1] : 0) + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1];
    }
}
