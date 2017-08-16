package org.ventry.commons.algorithm.dp;

import org.ventry.commons.utils.Console;

/**
 * file: org.ventry.commons.algorithm.dp.LongestCommonSubsequence
 * author: ventry
 * create: 17/8/16 20:48
 * description:
 */

public class LongestCommonSubsequence {
    private String x;
    private int xLength;
    private int yLength;
    private int[][] solution;// 当不需要重构lcs时, solution表可以化简成两行: 当前正在计算的一行和前一行

    private void match(String x, String y) {
        init(x, y);
        for (int i = 1; i <= xLength; i++) {
            for (int j = 1; j <= yLength; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    solution[i][j] = solution[i - 1][j - 1] + 1;
                } else {
                    solution[i][j] = Math.max(solution[i][j - 1], solution[i - 1][j]);
                }
            }
        }
    }

    private void init(String x, String y) {
        this.x = x;
        if (x.length() == 0 || y.length() == 0) {
            xLength = yLength = -1;
            solution = null;
        } else {
            xLength = x.length();
            yLength = y.length();
            solution = new int[xLength + 1][yLength + 1];
        }
    }

    private void length() {
        Console.writeLine("lcs length: " + (solution == null ? 0 : solution[xLength][yLength]));
    }

    private void solution() {
        StringBuilder sequence = new StringBuilder();
        for (int i = xLength, j = yLength; i > 0 && j > 0; ) {
            if (solution[i][j] == solution[i - 1][j - 1] + 1) {
                sequence.append(x.charAt(i - 1));
                i--;
                j--;
            } else if (solution[i][j] == solution[i][j - 1]) {
                j--;
            } else {
                i--;
            }
        }
        sequence.reverse();
        Console.writeLine("lcs sequence: " + sequence);
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        lcs.match("abcbdab", "bdcaba");
        lcs.length();
        lcs.solution();
        lcs.match("abcbdab", "");
        lcs.length();
        lcs.solution();
        lcs.match("abcbdaba", "bdcaba");
        lcs.length();
        lcs.solution();
    }
}
