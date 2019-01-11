package org.ventry.commons.leetcode.stack;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.stack.RemoveDuplicateLetters
 * author: ventry
 * create: 2019/1/9 18:37
 * description:
 */

public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return s;

        int[] set = new int[256];
        int[] counter = new int[256];
        for (int i = 0; i < len; i++) {
            counter[s.charAt(i)]++;
        }
        char[] stack = new char[len];
        int top = -1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (set[c] == 0) {
                while (top > -1 && stack[top] > c && counter[stack[top]] > 0) {
                    set[stack[top]] = 0;
                    top--;
                }
                stack[++top] = c;
                set[c] = 1;
            }
            counter[c]--;
        }

        return new String(Arrays.copyOf(stack, top + 1));
    }
}
