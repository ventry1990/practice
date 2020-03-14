package org.ventry.commons.leetcode.string;

/**
 * file: org.ventry.commons.leetcode.string.FirstUniqueCharacterInAString
 * author: ventry
 * create: 2020/3/14 17:34
 * description:
 */
public class FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0)
            return -1;

        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        int res = s.length();
        for (char c = 'a'; c <= 'z'; c++) {
            int index = s.indexOf(c);
            if (index != -1 && s.lastIndexOf(c) == index) {
                res = Math.min(res, index);
            }
        }
        return res == s.length() ? -1 : res;
    }
}
