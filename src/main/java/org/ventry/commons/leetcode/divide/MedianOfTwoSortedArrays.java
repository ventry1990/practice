package org.ventry.commons.leetcode.divide;

import org.ventry.commons.algorithm.sort.OrderStatistics;

/**
 * file: org.ventry.commons.leetcode.divide.MedianOfTwoSortedArrays
 * author: jojo
 * create: 2017/5/12 10:00
 * description:
 */

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] numbers1, int[] numbers2) {
        int[] merged;
        if (numbers1 == null || numbers1.length == 0) {
            if (numbers2 == null || numbers2.length == 0) {
                return 0D;
            }

            merged = numbers2;
        } else if (numbers2 == null || numbers2.length == 0) {
            merged = numbers1;
        } else {
            merged = new int[numbers1.length + numbers2.length];
            System.arraycopy(numbers1, 0, merged, 0, numbers1.length);
            System.arraycopy(numbers2, 0, merged, numbers1.length, numbers2.length);
        }

        if (merged.length % 2 == 0) {
            int m1 = OrderStatistics.select(merged, 0, merged.length - 1, (merged.length - 1) / 2);
            int m2 = OrderStatistics.select(merged, 0, merged.length - 1, merged.length / 2);

            return (m1 + m2) * 1D / 2;
        } else {
            return OrderStatistics.select(merged, 0, merged.length - 1, merged.length / 2) * 1D;
        }
    }

    public double find2(int[] numbers1, int[] numbers2) {
        int lengthOfN1 = numbers1.length;
        int lengthOfN2 = numbers2.length;
        if (lengthOfN1 < lengthOfN2) return find2(numbers2, numbers1);    // Make sure numbers2 is the shorter one.

        if (lengthOfN2 == 0) return ((double) numbers1[(lengthOfN1 - 1) / 2] + (double) numbers1[lengthOfN1 / 2]) / 2;  // If numbers2 is empty

        int low = 0, high = lengthOfN2 * 2;
        while (low <= high) {
            int mid2 = (low + high) / 2;   // Try Cut 2
            int mid1 = lengthOfN1 + lengthOfN2 - mid2;  // Calculate Cut 1 accordingly

            double l1 = (mid1 == 0) ? Integer.MIN_VALUE : numbers1[(mid1 - 1) / 2];    // Get l1, R1, L2, R2 respectively
            double l2 = (mid2 == 0) ? Integer.MIN_VALUE : numbers2[(mid2 - 1) / 2];
            double r1 = (mid1 == lengthOfN1 * 2) ? Integer.MAX_VALUE : numbers1[(mid1) / 2];
            double r2 = (mid2 == lengthOfN2 * 2) ? Integer.MAX_VALUE : numbers2[(mid2) / 2];

            if (l1 > r2) low = mid2 + 1;        // numbers1's lower half is too big; need to move C1 left (C2 right)
            else if (l2 > r1) high = mid2 - 1;    // numbers2's lower half too big; need to move C2 left.
            else return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;    // Otherwise, that's the right cut.
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {1, 3}, new int[] {2}));
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {1, 3}, new int[] {2, 4}));
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[] {1, 3}, new int[0]));

        System.out.println(new MedianOfTwoSortedArrays().find2(new int[] {1, 3}, new int[] {2}));
        System.out.println(new MedianOfTwoSortedArrays().find2(new int[] {1, 3}, new int[] {2, 4}));
        System.out.println(new MedianOfTwoSortedArrays().find2(new int[] {1, 3}, new int[0]));
    }
}
