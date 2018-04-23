package org.ventry.commons.leetcode.stack;

import java.util.LinkedList;

/**
 * file: org.ventry.commons.leetcode.stack.ValidParentheses
 * author: ventry
 * create: 17/11/8 16:41
 * description:
 */

public class ValidParentheses {

    public boolean isValid(String s) {
        int len = s.length();
        if (len % 2 != 0)
            return false;

        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.addLast(c);
            } else {
                if (stack.size() == 0)
                    return false;

                char p = stack.removeLast();
                if ((c == '}' && p != '{') || (c == ']' && p != '[') || (c == ')' && p != '('))
                    return false;
            }
        }

        return stack.size() == 0;
    }
}
