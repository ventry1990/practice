package org.ventry.commons.leetcode.string;

/**
 * file: org.ventry.commons.leetcode.string.ValidNumber
 * author: ventry
 * create: 2020/4/2 00:53
 * description:
 */
public class ValidNumber {

    public boolean isNumber(String s) {
        System.out.print(s + "\t");
        int start = 0, end;
        if (s == null || (end = s.length()) == 0) return false;

        while (start < end && s.charAt(start) == ' ') start++;
        while (start < end && s.charAt(end - 1) == ' ') end--;
        if (start == end) return false;

        int eIndex = findE(s, start, end);
        if (eIndex == start || eIndex == end - 1) return false;
        if (eIndex == -1) {
            return canBeFloat(s, start, end);
        } else {
            return canBeFloat(s, start, eIndex) && onlyBeInteger(s, eIndex + 1, end);
        }
    }

    private int findE(String s, int startInclude, int endExclude) {
        int i = startInclude;
        for (; i < endExclude; i++) {
            if (s.charAt(i) == 'e' || s.charAt(i) == 'E')
                return i;
        }
        return -1;
    }

    private boolean onlyBeInteger(String s, int startInclude, int endExclude) {
        boolean numberSeen = false;
        for (int i = startInclude; i < endExclude; i++) {
            char c = s.charAt(i);
            if (i == startInclude && (c == '+' || c == '-')) {
                continue;
            }

            if (c < '0' || c > '9') {
                return false;
            }
            numberSeen = true;
        }
        return numberSeen;
    }

    private boolean canBeFloat(String s, int startInclude, int endExclude) {
        boolean dotSeen = false;
        boolean numberSeen = false;
        for (int i = startInclude; i < endExclude; i++) {
            char c = s.charAt(i);
            if (i == startInclude && (c == '+' || c == '-')) {
                continue;
            }

            if (c == '.') {
                if (dotSeen) return false;
                dotSeen = true;
            } else if (c < '0' || c > '9') {
                return false;
            } else {
                numberSeen = true;
            }
        }
        return numberSeen;
    }
}
