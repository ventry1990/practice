package org.ventry.commons.leetcode.string;

/**
 * file: org.ventry.commons.leetcode.string.DecodeString
 * author: ventry
 * create: 2019/3/14 16:45
 * description:
 */

public class DecodeString {

    public String decodeString(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return s;
        return decode(s, 0, len);
    }

    private String decode(String s, int start, int end) {
        StringBuilder res = new StringBuilder();
        int repeat = 0;
        while (start < end) {
            char c = s.charAt(start);
            if (c <= '9' && c >= '0') {
                repeat = repeat * 10 + c - '0';
                start++;
            } else if (c == '[') {
                int subEnd = findRightSquareBracketIndex(s, start + 1, end);
                String sub = decode(s, start + 1, subEnd);
                for (int j = 0; j < repeat; j++) {
                    res.append(sub);
                }
                start = subEnd + 1;
                repeat = 0;
            } else {
                res.append(c);
                start++;
            }
        }
        return res.toString();
    }

    private int findRightSquareBracketIndex(String s, int start, int end) {
        int leftCount = 1;
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (c == '[') leftCount++;
            if (c == ']') {
                if (leftCount == 1)
                    return i;
                else
                    leftCount--;
            }
        }
        return -1;
    }
}
