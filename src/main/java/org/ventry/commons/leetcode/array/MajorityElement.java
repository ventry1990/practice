package org.ventry.commons.leetcode.array;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.array.MajorityElement
 * author: ventry
 * create: 2019/1/2 15:02
 * description:
 */

public class MajorityElement {

    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int half = nums.length / 2;
        int times = 1;
        int el = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (el == nums[i]) {
                times = times + 1;
                if (times > half) {
                    return el;
                }
            } else {
                el = nums[i];
                times = 1;
            }
        }
        return el;
    }

    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public int majorityElement3(int[] nums) {
        // 从头到尾遍历数组，遇到两个不一样的数就把这两个数同时除去。除去的两个数可能都不是majority，
        // 也可能一个是majority一个不是，但是因为majority总数大于一半（注意不能等于一半），所以这么
        // 删完了最后肯定剩下的数是majority。
        int res = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                res = num;
                count++;
            } else if (num != res) count--;
            else count++;
        }
        return res;
    }
}
