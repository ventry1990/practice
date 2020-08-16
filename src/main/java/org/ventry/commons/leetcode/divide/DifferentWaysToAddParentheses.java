package org.ventry.commons.leetcode.divide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.divide.DifferentWaysToAddParentheses
 * author: ventry
 * create: 2020/8/16 15:20
 * description:
 */
public class DifferentWaysToAddParentheses {
    private static final List<Character> OPERATORS = Arrays.asList('+', '-', '*');

    public List<Integer> diffWaysToCompute(String input) {
        int len;
        if (input == null || (len = input.length()) == 0) {
            return Collections.emptyList();
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (OPERATORS.contains(input.charAt(i))) {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                if (input.charAt(i) == '+') res.addAll(add(left, right));
                if (input.charAt(i) == '-') res.addAll(minus(left, right));
                if (input.charAt(i) == '*') res.addAll(multiply(left, right));
            }
        }
        if (res.size() == 0) {
            return Collections.singletonList(Integer.valueOf(input));
        } else {
            return res;
        }
    }

    private List<Integer> add(List<Integer> left, List<Integer> right) {
        if (left.size() == 0) {
            return right;
        }
        if (right.size() == 0) {
            return left;
        }

        List<Integer> res = new ArrayList<>();
        for (Integer aLeft : left) {
            for (Integer aRight : right) {
                res.add(aLeft + aRight);
            }
        }
        return res;
    }

    private List<Integer> minus(List<Integer> left, List<Integer> right) {
        if (left.size() == 0) {
            return right;
        }
        if (right.size() == 0) {
            return left;
        }

        List<Integer> res = new ArrayList<>();
        for (Integer aLeft : left) {
            for (Integer aRight : right) {
                res.add(aLeft - aRight);
            }
        }
        return res;
    }

    private List<Integer> multiply(List<Integer> left, List<Integer> right) {
        if (left.size() == 0) {
            return right;
        }
        if (right.size() == 0) {
            return left;
        }

        List<Integer> res = new ArrayList<>();
        for (Integer aLeft : left) {
            for (Integer aRight : right) {
                res.add(aLeft * aRight);
            }
        }
        return res;
    }

    private List<Integer>[][] dp;

    public List<Integer> diffWaysToCompute2(String input) {
        dp = new List[input.length()][input.length()];
        return solve(input, 0, input.length() - 1);
    }

    private List<Integer> solve(String input, int start, int end) {
        if (start > end) {
            return null;
        }

        if (dp[start][end] != null) {
            return dp[start][end];
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = start; i <= end; ++i) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = solve(input, start, i - 1);
                List<Integer> right = solve(input, i + 1, end);

                if (left == null || right == null) {
                    continue;
                }

                for (int j = 0; j < left.size(); ++j) {
                    for (int k = 0; k < right.size(); ++k) {
                        if (c == '+') {
                            ans.add(left.get(j) + right.get(k));
                        } else if (c == '-') {
                            ans.add(left.get(j) - right.get(k));
                        } else {
                            ans.add(left.get(j) * right.get(k));
                        }
                    }
                }
            }
        }

        if (ans.size() == 0) {
            ans.add(Integer.parseInt(input.substring(start, end + 1)));
        }

        dp[start][end] = ans;
        return ans;
    }
}
