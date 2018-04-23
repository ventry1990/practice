package org.ventry.commons.leetcode.dp;

import java.util.LinkedList;

/**
 * file: org.ventry.commons.leetcode.dp.RegularExpressionMatching
 * author: jojo
 * create: 2017/5/31 20:07
 * description:
 */

public class RegularExpressionMatching {

    static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean isMatch(String s, String p) {
        s = s + "#";
        p = p + "#";
        int sLength = s.length();
        int pLength = p.length();

        LinkedList<Position> trace = new LinkedList<>();
        int x = 0, y = 0;
        while (x < pLength && y < sLength) {
            if (p.charAt(x) == '.' || s.charAt(y) == p.charAt(x)) {
                if (x + 1 < pLength) {
                    char next = p.charAt(x + 1);
                    if (next != '*') {
                        x++;
                        y++;
                    } else {
                        trace.push(new Position(x, y));
                        x += 2;
                    }
                } else {
                    x++;
                    y++;
                }
            } else if (x + 1 < pLength && p.charAt(x + 1) == '*') {
                x += 2;
            } else {
                if (trace.isEmpty()) {
                    return false;
                }

                Position step = trace.pop();
                x = step.x;
                y = step.y + 1;
            }
        }

        return x == pLength && y == sLength;
    }

    public boolean dynamicProgramming(String s, String p) {
        boolean[][] dp = new boolean[1 + s.length()][1 + p.length()];
        dp[s.length()][p.length()] = true;
        for (int j = p.length() - 2; j >= 0; j -= 2) {
            if (p.charAt(j + 1) == '*')
                dp[s.length()][j] = true;
            else
                break;
        }

        for (int i = s.length() - 1; i >= 0; i--)
            for (int j = p.length() - 1; j >= 0; j--) {
                if (j != p.length() - 1 && p.charAt(j + 1) == '*') {
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))
                        dp[i][j] = dp[i + 1][j] || dp[i][j + 2] || dp[i + 1][j + 2];
                    else
                        dp[i][j] = dp[i][j + 2];
                } else if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {
                    dp[i][j] = dp[i + 1][j + 1];
                }
            }

        return dp[0][0];
    }

    public static void main(String[] args) {
        String[] inputs = new String[] {
                "aa", "a", "1.false",
                "aa", "aa", "2.true",
                "aaa", "aa", "3.false",
                "aa", "a*", "4.true",
                "aa", ".*", "5.true",
                "abcdefg", ".*", "6.true",
                "aab", "c*a*b", "7.true",
                "", "c*a*b", "8.false",
                "", "c*a*b*", "9.true",
                "aaba", "ab*a*c*a", "10.false",
                "aaba", "ab*a", "11.false",
                "abc", "abc**", "12.false",
                "abc", "abc***", "13.true",
                "aaaefggbccbce123445bcdddddddddddd", "a*.*bcd*.*bcd*", "14.true",
                "aaaefggbccbce123445bc", "a*.*bcd*.*bcd*", "15.true",
                "aaabc123445bcee", "a*.*bcd*.*bcd*", "16.false",
                "abc", "**abc", "17.true",
                "abc", "*abc", "18.false",
                "abc*", "abc**", "19.true",
                "aaa", "a*a", "20.true",
                "aaa", "aa*a", "21.true",
                "aaa", "aaa*a", "22.true",
                "", "", "true"
        };
        RegularExpressionMatching rem = new RegularExpressionMatching();
        for (int i = 0; i < inputs.length; i += 3) {
            System.out.println(inputs[i + 2] + "->" + rem.isMatch(inputs[i], inputs[i + 1])
                    + "|" + rem.dynamicProgramming(inputs[i], inputs[i + 1]));
        }
    }
}
