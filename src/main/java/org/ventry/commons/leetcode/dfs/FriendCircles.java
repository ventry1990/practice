package org.ventry.commons.leetcode.dfs;

/**
 * file: org.ventry.commons.leetcode.dfs.FriendCircles
 * author: ventry
 * create: 2020/10/18 23:29
 * description:
 */
public class FriendCircles {

    public int findCircleNum(int[][] M) {
        int n;
        if ((n = M.length) == 0) return 0;

        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(M, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, j, visited);
            }
        }
    }
}
