package org.ventry.commons.leetcode.pointer;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.pointer.ThreeSumClosest
 * author: ventry
 * create: 17/9/18 17:05
 * description:
 */

public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        assert nums != null;
        assert nums.length >= 3;

        if (nums.length == 3)
            return nums[0] + nums[1] + nums[2];

        Arrays.sort(nums);
        int numsLength = nums.length;
        int result = 0;
        int minBias = Integer.MAX_VALUE;
        for (int i = 0; i < numsLength - 2; i++) {
            int curBias = target - nums[i];
            curBias = curBias - find(nums, i + 1, numsLength - 1, curBias);
            if (Math.abs(minBias) > Math.abs(curBias)) {
                minBias = curBias;
                result = target - minBias;
            }
            if (minBias == 0)
                break;
        }
        return result;
    }

    private int find(int[] nums, int start, int end, int base) {
        int min = Integer.MAX_VALUE;
        while (start < end) {
            int d = base - nums[start] - nums[end];
            if (Math.abs(d) < Math.abs(min)) {
                min = d;
            }

            if (d > 0)
                start++;
            else
                end--;
        }
        return base - min;
    }

}
