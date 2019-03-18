package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.Point
 * author: ventry
 * create: 2019/3/18 15:08
 * description:
 */

public class Point {
    public int x;
    public int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int a, int b) {
        x = a;
        y = b;
    }

    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
