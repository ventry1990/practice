package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.CarPooling
 * author: ventry
 * create: 2020/10/18 23:24
 * description:
 */
public class CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (int[] trip : trips) {
            start = Math.min(start, trip[1]);
            end = Math.max(end, trip[2]);
        }
        int[] diff = new int[end + 1];
        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }

        for (int i = start + 1; i <= end; i++) {
            diff[i] += diff[i - 1];
            if (diff[i] > capacity) return false;
        }
        return true;
    }
}
