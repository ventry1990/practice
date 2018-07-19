package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.UniquePaths
 * author: ventry
 * create: 18/7/19 15:26
 * description:
 */

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        int[] prev = new int[m];
        int[] cur = new int[m];
        for (int i = 0; i < m; i++) {
            prev[i] = 1;
            cur[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                cur[j] = prev[j] + cur[j - 1];
                prev[j] = cur[j];
            }
        }

        return cur[m - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m, n;
        if ((m = obstacleGrid.length) == 0 || (n = obstacleGrid[0].length) == 0) {
            return 0;
        }

        int[][] paths = new int[m][n];
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            paths[i][0] = 1;
        }
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
            paths[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                paths[i][j] = obstacleGrid[i][j] == 1 ? 0 : paths[i - 1][j] + paths[i][j - 1];
            }
        }
        return paths[m - 1][n - 1];
    }
}