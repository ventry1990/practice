package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.Interval
 * author: ventry
 * create: 18/3/5 15:26
 * description:
 */

public class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "[" + start + ", " + end + "]";
    }
}
