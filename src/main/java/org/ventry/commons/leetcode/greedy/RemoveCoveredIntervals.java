package org.ventry.commons.leetcode.greedy;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.greedy.RemoveCoveredIntervals
 * author: ventry
 * create: 2020/10/18 23:14
 * description:
 */
public class RemoveCoveredIntervals {

    public int removeCoveredIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) return len;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int cnt = 1;
        for (int i = 1, j = 0; i < len; i++) {
            if (intervals[j][0] > intervals[i][0] || intervals[i][1] > intervals[j][1]) {
                j = i;
                cnt++;
            }
        }
        return cnt;
    }
}
