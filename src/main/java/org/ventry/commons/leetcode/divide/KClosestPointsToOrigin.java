package org.ventry.commons.leetcode.divide;

import org.ventry.commons.algorithm.sort.OrderStatistics;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.divide.KClosestPointsToOrigin
 * author: ventry
 * create: 2020/3/13 20:08
 * description:
 */
public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int K) {
        int l = 0;
        int r = points.length - 1;
        int k = K - 1;

        while (l < r) {
            int p = partition(points, l, r);
            if (p == k) {
                break;
            } else if (p < k) {
                l = p + 1;
            } else {
                r = p - 1;
            }
        }

        return Arrays.copyOf(points, K);

    }

    private int partition(int[][] points, int l, int r) {
        int i = l;
        int j = r + 1;

        int disL = distance(points[l]);
        while (i < j) {
            while (distance(points[++i]) < disL && i < r) ;
            while (distance(points[--j]) > disL && j > l) ;
            if (i >= j) {
                break;
            }
            swap(points, i, j);
        }
        swap(points, l, j);
        return j;
    }


    private void swap(int[][] a, int i, int j) {
        int[] t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int distance(int[] point1) {
        return point1[0] * point1[0] + point1[1] * point1[1];
    }

    public int[][] _kClosest(int[][] points, int K) {
        int n;
        if ((n = points.length) <= K)
            return points;

        int[] distances = new int[n];
        for (int i = 0; i < n; i++) {
            distances[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }
        int distanceFound = OrderStatistics.select(distances, 0, n - 1, K - 1);

        int[][] result = new int[K][2];
        for (int i = 0, j = 0; i < n && j < K; i++) {
            int distance = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            if (distance <= distanceFound) {
                result[j] = points[i];
                j++;
            }
        }
        return result;
    }
}
