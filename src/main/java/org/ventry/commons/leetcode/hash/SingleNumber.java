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

    /**
     * https://leetcode.com/problems/single-number-ii/discuss/43294/Challenge-me-thx
     */
    public int singleNumberII(int[] nums) {
        int once = 0, twice = 0;
        for (int num : nums) {
            once = ~twice & (once ^ num);
            twice = ~once & (twice ^ num);
        }
        return once;
    }

    public int _singleNumberII(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int sumInBit = 0;
            for (int num : nums) {
                sumInBit += (num >>> i) & 1;
            }
            res |= (sumInBit % 3) << i;
        }
        return res;
    }
}
