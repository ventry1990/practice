package org.ventry.commons.leetcode.sort;

import org.ventry.commons.leetcode.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.sort.MergeIntervals
 * author: ventry
 * create: 2018/9/27 01:09
 * description:
 */

public class MergeIntervals {

    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 2) {
            return intervals;
        }

        intervals.sort((a, b) -> {
            if (a.start == b.start) {
                return a.end - b.end;
            } else {
                return a.start - b.start;
            }
        });

        List<Interval> result = new ArrayList<>(intervals.size());
        Interval cur = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= cur.end) {
                cur.end = Math.max(intervals.get(i).end, cur.end);
            } else {
                result.add(new Interval(cur.start, cur.end));
                cur = intervals.get(i);
            }
        }
        result.add(new Interval(cur.start, cur.end));
        return result;
    }

    public List<Interval> merge2(List<Interval> intervals) {
        int n = intervals.size();
        int[] start = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        List<Interval> result = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || start[i + 1] > end[i]) {
                result.add(new Interval(start[k], end[i]));
                k = i + 1;
            }
        }
        return result;
    }
}
