package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.CorporateFlightBookings
 * author: ventry
 * create: 2020/10/18 23:11
 * description:
 */
public class CorporateFlightBookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 2];
        for (int[] booking : bookings) {
            diff[booking[0]] += booking[2];
            diff[booking[1] + 1] -= booking[2];
        }
        for (int i = 1; i <= n; i++) {
            diff[i] += diff[i - 1];
        }

        int[] res = new int[n];
        System.arraycopy(diff, 1, res, 0, n);
        return res;
    }
}
