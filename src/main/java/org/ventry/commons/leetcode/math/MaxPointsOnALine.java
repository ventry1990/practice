package org.ventry.commons.leetcode.math;

import org.ventry.commons.leetcode.Point;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.math.MaxPointsOnALine
 * author: ventry
 * create: 2019/3/18 15:08
 * description:
 */

public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        if (points.length < 3) return points.length;
        int max = 0;
        for (int i = 1; i < points.length; i++) {
            Point a = points[i];
            Point b = points[i - 1];
            long dx = a.x - b.x; // finding x difference
            long dy = a.y - b.y; // finding y difference
            int count = 0;
            if (dx == 0 && dy == 0) {
                // overlapPoints
                for (Point point : points) {
                    if (point.x == a.x && point.y == a.y)
                        count++;
                }
            } else {
                // non overlapping
                for (Point point : points) {
                    if ((point.x - b.x) * dy == (point.y - b.y) * dx) {
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    public int _maxPoints(Point[] points) {
        int n;
        if (points == null) return 0;
        if ((n = points.length) < 3) return n;

        int max = 0;
        for (int i = 0; i < n; i++) {
            List<Point> diff = new ArrayList<>(n);
            int same = 1;
            for (int j = i + 1; j < n; j++) {
                if (isSame(points[i], points[j])) {
                    same++;
                } else {
                    diff.add(points[j]);
                }
            }
            int num = 0;
            for (int j = 0; j < diff.size(); j++) {
                List<Point> line = new ArrayList<>(n);
                line.add(diff.get(j));
                for (int k = j + 1; k < diff.size(); k++) {
                    if (isALine(points[i], diff.get(j), diff.get(k))) {
                        line.add(diff.get(k));
                    }
                }
                if (num < line.size())
                    num = line.size();
            }
            if (max < num + same) max = num + same;
        }
        return max;
    }

    private boolean isSame(Point a, Point b) {
        return a.x == b.x && a.y == b.y;
    }

    private boolean isALine(Point a, Point b, Point point) {
        if (a.x == b.x && b.x == point.x) return true;
        if (a.y == b.y && b.y == point.y) return true;
        if (a.x > b.x) {
            Point t = a;
            a = b;
            b = t;
        }
        return (point.y - b.y) * ((b.x - a.x) * 1D) - (b.y - a.y) * ((point.x - b.x) * 1D) == 0D;
    }
}
