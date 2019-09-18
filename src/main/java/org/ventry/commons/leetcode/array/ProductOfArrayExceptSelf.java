package org.ventry.commons.leetcode.array;

/**
 * file: org.ventry.commons.leetcode.array.ProductOfArrayExceptSelf
 * author: ventry
 * create: 2019-09-17 20:03
 * description:
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        long total = 1;
        boolean oneMoreZero = false;
        for (int num : nums) {
            if (num == 0) {
                if (oneMoreZero)
                    return new int[nums.length];

                oneMoreZero = true;
            } else {
                total *= num;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (oneMoreZero) {
                nums[i] = nums[i] == 0 ? (int) total : 0;
            } else {
                nums[i] = (int) total / nums[i];
            }
        }
        return nums;
    }
}
