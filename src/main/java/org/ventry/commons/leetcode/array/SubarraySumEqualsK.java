package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.SubarraySumEqualsK
 * author: ventry
 * create: 2019/3/30 11:47
 * description:
 */

public class SubarraySumEqualsK {

    public int subarraySum(int[] nums, int k) {
        if (nums == null) return 0;
        int n = nums.length;
        int[] copy = new int[n + 1];
        int[] numspad = new int[n + 1];
        for (int i = 1; i <= nums.length; i++) {
            numspad[i] = numspad[i - 1] + nums[i - 1];
        }
        return mergeSort(numspad, 0, numspad.length - 1, k, copy);
    }

    private int mergeSort(int[] nums, int l, int r, int k, int[] copy) {
        if (l >= r) return 0;
        int mid = l + (r - l) / 2;
        int leftCount = mergeSort(nums, l, mid, k, copy);
        int rightCount = mergeSort(nums, mid + 1, r, k, copy);

        int count = 0;
        for (int i = l, j = mid + 1; i <= mid && j <= r; i++) {
            while (j <= r && nums[i] + k > nums[j]) {
                // nums[l .. mid] and nums[mid + 1 .. r] is ordered
                j++;
            }
            if (j <= r && nums[i] + k == nums[j]) {
                int c = j;
                while (c <= r && nums[i] + k == nums[c]) {
                    c++;
                }
                count += (c - j);
            }
        }

        for (int i = l; i <= r; i++) {
            copy[i] = nums[i];
        }

        int i = l, j = mid + 1;
        int pointer = l;
        while (i <= mid && j <= r) {
            if (copy[i] <= copy[j]) {
                nums[pointer++] = copy[i++];
            } else {
                nums[pointer++] = copy[j++];
            }
        }

        while (i <= mid) {
            nums[pointer++] = copy[i++];
        }

        return count + leftCount + rightCount;
    }

    public int _subarraySum(int[] nums, int k) {
        int n;
        if (nums == null || (n = nums.length) == 0) return 0;

        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + nums[i - 1];
            if (dp[i] == k) {
                res++;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j <= n; j++) {
                dp[j] = dp[j] - nums[i];
                if (dp[j] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
