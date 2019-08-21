package org.ventry.commons.leetcode.bit;

/**
 * file: org.ventry.commons.leetcode.bit.SingleNumber
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

    public int[] singleNumberIII(int[] nums) {
        // once = a ^ b, twice = (a * a) ^ (b * b)
        int once = 0;
        int twice = 0;
        for (int value : nums) {
            once = once ^ value;
            twice = twice ^ (value * value);
        }
        for (int num : nums) {
            int temp = once ^ num;
            if (temp * temp == (twice ^ (num * num))) {
                return new int[]{temp, num};
            }
        }
        return null;
    }

    public int[] _singleNumberIII(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum ^= num;
        }
        int mask = 1;
        for (int i = 0; i < 32; mask <<= (++i)) {
            if (mask == (sum & mask))
                break;
        }

        int[] res = new int[2];
        for (int num : nums) {
            if ((num & mask) == mask) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
