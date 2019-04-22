package org.ventry.commons.leetcode.string;

/**
 * file: org.ventry.commons.leetcode.string.PalindromicSubstrings
 * author: ventry
 * create: 2019/4/22 11:31
 * description:
 */

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int count = 0;
        if (s == null || s.length() == 0)
            return count;

        char[] chars = init(s);
        int len = chars.length - 1;
        for (int i = 1; i < len; i++) {
            int r = 1;
            while (i - r >= 0 && i + r <= len && chars[i - r] == chars[i + r]) {
                count++;
                r = r + (chars[i - r] != '#' ? 2 : 1);
            }
        }
        return count;
    }

    private char[] init(String s) {
        char[] target = new char[(s.length() + 1) * 2];

        target[0] = '#';
        for (int i = 0; i < s.length(); i++) {
            target[(i + 1) * 2 - 1] = s.charAt(i);
            target[(i + 1) * 2] = '#';
        }

        return target;
    }
}
