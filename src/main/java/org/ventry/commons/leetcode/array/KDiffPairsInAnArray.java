package org.ventry.commons.leetcode.array;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.array.KDiffPairsInAnArray
 * author: ventry
 * create: 2020/10/18 23:08
 * description:
 */
public class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        int len;
        if (nums == null || (len = nums.length) == 0) return 0;

        Arrays.sort(nums);
        int[] diff = new int[len - 1];
        for (int i = 1; i < len; i++) {
            diff[i - 1] = nums[i] - nums[i - 1];
        }

        int cnt = 0;
        for (int i = 0; i < len - 1; i++) {
            int delta = diff[i];
            for (int j = i + 1; j < len - 1; j++) {
                if (delta >= k) {
                    break;
                } else {
                    delta += diff[j];
                }
            }
            if (delta == k) {
                cnt++;
            }
            // skip the same diff pair
            while (i < len - 1 && diff[i] == 0) {
                i++;
            }
        }

        return cnt;
    }
}
