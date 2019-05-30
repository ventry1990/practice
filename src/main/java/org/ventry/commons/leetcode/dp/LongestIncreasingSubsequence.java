package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.LongestIncreasingSubsequence
 * author: ventry
 * create: 2019/5/30 15:11
 * description:
 */

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length <= 1) {
            return nums.length;
        }
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                nums[++maxIndex] = nums[i];
            } else {
                int toReplace = binarySearch(nums, 0, maxIndex, nums[i]);
                nums[toReplace] = nums[i];
            }
        }
        return maxIndex + 1;
    }

    private int binarySearch(int[] nums, int left, int right, int num) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == num) {
                return mid;
            } else if (nums[left] >= num) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // For same reason in increasingTriplet.
        // if num equals to a smaller one, it can only replace the smaller one instead of the later larger one
        if (nums[left] >= num) {
            return left;
        } else {
            return right;
        }
    }

    public int _lengthOfLIS(int[] nums) {
        int len;
        if (nums == null || (len = nums.length) == 0) return 0;

        int max = 0;
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max + 1;
    }
}
