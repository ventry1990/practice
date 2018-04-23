package org.ventry.commons.leetcode.search;

/**
 * file: org.ventry.commons.leetcode.search.SearchInsertPosition
 * author: ventry
 * create: 17/11/16 16:59
 * description:
 */

public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }
}
