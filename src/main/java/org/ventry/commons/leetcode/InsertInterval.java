package org.ventry.commons.leetcode;

import java.util.List;

/**
 * file: org.ventry.commons.leetcode.InsertInterval
 * author: ventry
 * create: 18/3/5 15:26
 * description:
 */

public class InsertInterval {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null)
            return intervals;

        intervals.add(0, new Interval(Integer.MIN_VALUE, Integer.MIN_VALUE));
        intervals.add(new Interval(Integer.MAX_VALUE, Integer.MAX_VALUE));
        int start = find(intervals, newInterval.start);
        int end = find(intervals, newInterval.end);

        if (newInterval.start <= intervals.get(start).end) {
            newInterval.start = Math.min(newInterval.start, intervals.get(start).start);
        }

        if (newInterval.end >= intervals.get(end).start) {
            newInterval.end = Math.max(newInterval.end, intervals.get(end).end);
        } else {
            end--;
        }

        for (int i = start; i <= end; i++) {
            intervals.remove(start);
        }
        intervals.add(start, newInterval);

        return intervals.subList(1, intervals.size() - 1);
    }

    private int find(List<Interval> intervals, int value) {
        int lo = 0;
        int hi = intervals.size() - 1;
        while (lo <= hi) {
            int mi = (lo + hi) >>> 1;
            Interval interval = intervals.get(mi);
            if (interval.start <= value && interval.end >= value) {
                return mi;
            } else if (interval.start > value) {
                hi = mi - 1;
            } else {
                lo = mi + 1;
            }
        }

        return lo;
    }
}
