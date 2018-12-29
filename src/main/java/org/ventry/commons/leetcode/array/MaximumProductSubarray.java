package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.MaximumProductSubarray
 * author: ventry
 * create: 2018/12/29 21:24
 * description:
 */

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];
        for (int i = 1; i < nums.length; i++) {
            boolean negative = nums[i] < 0;
            int min = Math.min((negative ? maxEndingHere : minEndingHere) * nums[i], nums[i]);
            maxEndingHere = Math.max((negative ? minEndingHere : maxEndingHere) * nums[i], nums[i]);
            minEndingHere = min;
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public int maxProduct2(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        int max = 0;
        int i = 0;
        int j;
        int k;
        int temp;
        int x;
        int y;
        while (i < n) {
            while (i < n && nums[i] == 0) {
                i++;
            }
            j = i;
            temp = 1;
            if (j < n) {
                while (j < n && nums[j] != 0) {
                    temp *= nums[j];
                    j++;
                }
                k = j;
                if (temp > 0 && temp > max) {
                    max = temp;
                } else if (j - i > 1) {
                    // 两端各排除掉一个负数后，比较大小。
                    j--;
                    x = temp;
                    while (x < 0 && j > i) {
                        x /= nums[j];
                        j--;
                    }
                    y = temp;
                    while (y < 0 && i < k) {
                        y /= nums[i];
                        i++;
                    }
                    temp = Math.max(x, y);
                    if (temp > max) {
                        max = temp;
                    }
                }
                i = k;
            }
        }
        return max;
    }
}
