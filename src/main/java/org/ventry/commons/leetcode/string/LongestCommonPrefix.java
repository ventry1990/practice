package org.ventry.commons.leetcode.string;

/**
 * file: org.ventry.commons.leetcode.string.LongestCommonPrefix
 * author: ventry
 * create: 17/9/18 15:33
 * description:
 */

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strings) {
        if (strings == null || strings.length == 0)
            return "";

        int shortest = Integer.MAX_VALUE;
        for (String str : strings) {
            if (str == null || str.length() == 0)
                return "";

            shortest = Math.min(shortest, str.length());
        }

        StringBuilder result = new StringBuilder(shortest);
        for (int i = 0; i < shortest; i++) {
            if (!sameAt(strings, i))
                return result.toString();
            result.append(strings[0].charAt(i));
        }

        return result.toString();
    }

    private boolean sameAt(String[] strings, int i) {
        char a = strings[0].charAt(i);
        for (int j = 1; j < strings.length; j++) {
            if (a != strings[j].charAt(i))
                return false;
        }
        return true;
    }
}
