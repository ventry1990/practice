package org.ventry.commons.leetcode.divide;

import org.ventry.commons.algorithm.sort.OrderStatistics;

/**
 * file: org.ventry.commons.leetcode.divide.KthLargestElementInAnArray
 * author: ventry
 * create: 2019/4/4 18:18
 * description:
 */

public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        return OrderStatistics.select(nums, 0, nums.length - 1, nums.length - k);
    }
}
