package org.ventry.commons.leetcode;

import org.ventry.commons.utils.Console;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ventry on 16/8/21.
 */
public class TwoSum {

    int[] find(int[] nums, int target) {
        int[] output = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                output[0] = map.get(nums[i]);
                output[1] = i;
                break;
            }
            map.put(target - nums[i], i);
        }

        return output;
    }

    int[] findInSorted(int[] nums, int target) {
        for (int i = 0, j = nums.length - 1; i < j; ) {
            while (nums[i] + nums[j] > target)
                j--;
            while (nums[i] + nums[j] < target)
                i++;

            if (nums[i] + nums[j] == target)
                return new int[]{i, j};
        }

        throw new RuntimeException("not exists");
    }

    public static void main(String[] args) {
        int[] source = new int[]{2, 7, 11, 15};
        int target = 9;
        Console.write(10, new TwoSum().findInSorted(source, target));

        source = new int[]{0, 4, 3, 0};
        target = 0;
        Console.write(10, new TwoSum().find(source, target));
    }
}
