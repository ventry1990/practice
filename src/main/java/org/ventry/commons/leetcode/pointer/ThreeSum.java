package org.ventry.commons.leetcode.pointer;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.pointer.ThreeSum
 * author: ventry
 * create: 17/9/18 16:13
 * description:
 */

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Boolean> map;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> one;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            map = new HashMap<>();
            for (int j = i + 1; j < nums.length; j++) {
                if (!map.containsKey(nums[j])) {
                    map.put(0 - nums[i] - nums[j], false);
                } else if (!map.get(nums[j])) {
                    one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(0 - nums[i] - nums[j]);
                    one.add(nums[j]);
                    result.add(one);
                    map.put(nums[j], true);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();

        if (len < 3) {
            return result;
        }

        Arrays.sort(nums);// 对数组进行排序

        for (int i = 0; i < len - 2; i++) {
            if (nums[i] > 0) {
                break;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            find(nums, i, i + 1, len - 1, result);
        }

        return result;
    }

    private void find(int[] nums, int baseIndex, int start, int end, List<List<Integer>> result) {
        int target = -nums[baseIndex];
        int res;

        while (start < end) {
            res = nums[start] + nums[end];
            if (res > target) { // 正数值太大，减少end值
                end--;
            } else if (res < target) {// 负数值太小，增加负数值
                start++;
            } else {
                result.add(Arrays.asList(nums[baseIndex], nums[start], nums[end]));

                while (start < end && nums[start + 1] == nums[start]) {
                    start++;
                }

                while (start < end && nums[end - 1] == nums[end]) {
                    end--;
                }

                start++;
                end--;
            }
        }
    }

}
