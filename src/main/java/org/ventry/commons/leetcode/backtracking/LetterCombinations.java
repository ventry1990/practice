package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.backtracking.LetterCombinations
 * author: ventry
 * create: 2019/1/15 11:42
 * description:
 */

public class LetterCombinations {

    private static final char[][] map = new char[][]{
            {'*'}, {'a', 'b', 'c'}, {'d', 'e', 'f'},
            {'g', 'h', 'i'}, {'j', 'k', 'l'}, {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'}, {'t', 'u', 'v'}, {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        backtrack(res, new char[digits.length()], digits, 0);
        return res;
    }

    private void backtrack(List<String> res, char[] one, String digits, int idx) {
        if (idx == digits.length()) {
            res.add(new String(one));
            return;
        }

        char[] match = map[digits.charAt(idx) - '1'];
        for (char c : match) {
            one[idx] = c;
            backtrack(res, one, digits, idx + 1);
        }
    }
}
