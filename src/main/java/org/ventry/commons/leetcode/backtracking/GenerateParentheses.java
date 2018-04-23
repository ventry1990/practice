package org.ventry.commons.leetcode.backtracking;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.backtracking.GenerateParentheses
 * author: ventry
 * create: 17/11/8 18:01
 * description:
 */

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        if (n < 1)
            return Collections.emptyList();

        List<Set<String>> dict = new ArrayList<>();
        dict.add(null);

        Set<String> cur = new HashSet<>();
        cur.add("()");
        dict.add(cur);

        for (int i = 2; i <= n; i++) {
            cur = new HashSet<>();
            for (int j = i / 2; j < i; j++) {
                Set<String> left = dict.get(j);

                if (i - j <= j) {
                    Set<String> right = dict.get(i - j);
                    cur.addAll(concat(left, right));
                }

                if (i - 1 == j) {
                    cur.addAll(surround(left));
                }
            }
            dict.add(cur);
        }

        return new ArrayList<>(dict.get(n));
    }

    private Set<String> surround(Set<String> source) {
        Set<String> result = new HashSet<>();
        for (String s : source) {
            result.add("(" + s + ")");
        }
        return result;
    }

    private Set<String> concat(Set<String> left, Set<String> right) {
        Set<String> result = new HashSet<>();
        for (String s : left) {
            for (String s1 : right) {
                result.add(s + s1);
                result.add(s1 + s);
            }
        }
        return result;
    }

    public List<String> generate2(int n) {
        List<String> result = new ArrayList<>();
        char[] temp = new char[2 * n];
        helper(temp, n, n, 0, result);
        return result;
    }

    private void helper(char[] temp, int left, int right, int index, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(new String(temp));
            return;
        }
        if (left > 0) {
            temp[index] = '(';
            helper(temp, left - 1, right, index + 1, result);
        }
        if (right > left) {
            temp[index] = ')';
            helper(temp, left, right - 1, index + 1, result);
        }
    }
}