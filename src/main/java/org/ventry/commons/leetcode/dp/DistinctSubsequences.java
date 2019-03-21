package org.ventry.commons.leetcode.dp;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.dp.DistinctSubsequences
 * author: ventry
 * create: 2019/3/21 16:17
 * description:
 */

public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int m, n;
        if (s == null || t == null || (m = s.length()) < (n = t.length()))
            return 0;

        int[] cur = new int[n + 1];
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = m - 1; i > -1; i--) {
            for (int j = n - 1; j > -1; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    cur[j] = dp[j + 1];
                }
            }
            for (int j = 0; j < n; j++) {
                dp[j] = dp[j] + cur[j];
                cur[j] = 0;
            }
        }
        return dp[0];
    }

    public int numDistinct2(String s, String t) {
        char[] ts = t.toCharArray();
        int tlen = ts.length;
        // map int to the character start positions
        int[] startIdx = new int[128];
        // track star the i position, what is the previous position for current character
        int[] prevIdx = new int[tlen];
        Arrays.fill(startIdx, -1);
        for (int i = 0; i < tlen; i++) {
            prevIdx[i] = startIdx[ts[i]];
            startIdx[ts[i]] = i + 1;
        }

        int[] dp = new int[tlen + 1];
        dp[0] = 1;
        for (char c : s.toCharArray()) {
            for (int j = startIdx[c]; j != -1; j = prevIdx[j - 1]) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[tlen];
    }
}
