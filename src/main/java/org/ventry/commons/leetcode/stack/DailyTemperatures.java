package org.ventry.commons.leetcode.stack;

/**
 * file: org.ventry.commons.leetcode.stack.DailyTemperatures
 * author: ventry
 * create: 2019/4/25 13:49
 * description:
 */

public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int[] res = new int[len];
        int[] stack = new int[len];
        int top = 0;
        for (int i = 1; i < len; i++) {
            while (top > -1 && T[stack[top]] < T[i]) {
                res[stack[top]] = i - stack[top];
                top--;
            }

            top++;
            stack[top] = i;
        }
        return res;
    }
}
