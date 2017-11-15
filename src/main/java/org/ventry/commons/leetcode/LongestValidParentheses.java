package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.LongestValidParentheses
 * author: ventry
 * create: 17/11/15 11:00
 * description:
 */

public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2)
            return 0;

        s = ")" + s;
        int[] len = new int[s.length()];
        int[] stack = new int[s.length()];
        int head = 0;
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack[head] = i;
                head++;
            } else if (head > 0) {
                head--;
                len[i] = len[stack[head] - 1] + len[i - 1] + 2;
                res = Math.max(len[i], res);
            } else {
                len[i] = 0;
            }
        }

        return res;
    }
}
