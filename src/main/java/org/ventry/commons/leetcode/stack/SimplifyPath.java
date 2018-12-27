package org.ventry.commons.leetcode.stack;

/**
 * file: org.ventry.commons.leetcode.stack.SimplifyPath
 * author: ventry
 * create: 2018/12/27 13:39
 * description:
 */

public class SimplifyPath {

    public String simplifyPath(String path) {
        String[] tokens = path.split("/");
        int top = 0;
        String[] stack = new String[tokens.length];
        for (String token : tokens) {
            if (token.equals(".") || token.length() == 0)
                continue;
            if (token.equals(".."))
                top = Math.max(0, top - 1);
            else
                stack[top++] = token;
        }

        if (top == 0)
            return "/";

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < top; i++) {
            res.append("/").append(stack[i]);
        }
        return res.toString();
    }
}
