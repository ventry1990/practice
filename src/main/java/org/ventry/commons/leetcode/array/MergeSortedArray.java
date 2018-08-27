package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.MergeSortedArray
 * author: ventry
 * create: 2018/8/27 10:47
 * description:
 */

public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m + n - 1; i > -1 && m > 0 && n > 0; i--) {
            if (nums1[m - 1] >= nums2[n - 1]) {
                nums1[i] = nums1[--m];
            } else {
                nums1[i] = nums2[--n];
            }
        }

        if (n > 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
        }
    }
}
