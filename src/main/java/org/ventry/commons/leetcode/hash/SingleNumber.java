package org.ventry.commons.leetcode.hash;

/**
 * file: org.ventry.commons.leetcode.hash.SingleNumber
 * author: ventry
 * create: 2019/3/28 14:30
 * description:
 */

public class SingleNumber {

    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            num ^= nums[i];
        }
        return num;
    }
}
