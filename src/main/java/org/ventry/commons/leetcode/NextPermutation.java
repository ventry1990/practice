package org.ventry.commons.leetcode;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.NextPermutation
 * author: ventry
 * create: 17/11/13 19:49
 * description:
 */

public class NextPermutation {

    public void nextPermutation(int[] nums) {
        // 找到数组右边起第一个正序对<a, b>
        // 互换<a, b> -> <b, a>
        // b元素右边元素升序排列
        if (nums.length < 2)
            return;

        int index = nums.length - 2;
        for (; index > -1; index--) {
            if (nums[index + 1] > nums[index])
                break;
        }

        if (index == -1) {
            Arrays.sort(nums, 0, nums.length);
            return;
        }

        int exchange = index;
        for (int i = nums.length - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                exchange = i;
                break;
            }
        }
        int temp = nums[index];
        nums[index] = nums[exchange];
        nums[exchange] = temp;

        Arrays.sort(nums, index + 1, nums.length);
    }
}
