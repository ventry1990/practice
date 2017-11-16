package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.SearchForARange
 * author: ventry
 * create: 17/11/15 22:23
 * description:
 */

public class SearchForARange {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums.length == 0)
            return res;

        int center = center(nums, target);
        if (center == -1)
            return res;

        res[0] = center;
        res[1] = center;

        int low = 0;
        int high = center;
        while (low < res[0]) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                res[0] = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        low = center;
        high = nums.length - 1;
        while (high > res[1]) {
            int mid = (high + low) >> 1;
            if (nums[mid] == target) {
                res[1] = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return res;
    }

    private int center(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }
}
