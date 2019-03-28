package org.ventry.commons.leetcode.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * file: org.ventry.commons.leetcode.sort.LargestNumber
 * author: ventry
 * create: 2019/3/27 19:16
 * description:
 */

public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "0";
        if (nums.length == 1) return String.valueOf(nums[0]);

        NumberWrapper[] copied = new NumberWrapper[nums.length];
        for (int i = 0; i < nums.length; i++) {
            copied[i] = new NumberWrapper(nums[i]);
        }
        Arrays.sort(copied);

        if (copied[0].num == 0) return "0";
        StringBuilder res = new StringBuilder();
        for (NumberWrapper integer : copied) {
            res.append(integer.num);
        }
        return res.toString();
    }

    private static class NumberWrapper implements Comparable<NumberWrapper> {
        private int num;
        private int base;

        private NumberWrapper(int i) {
            num = i;
            if (i == 0) {
                base = 10;
            } else {
                base = 1;
                while (i > 0) {
                    i = i / 10;
                    base *= 10;
                }
            }
        }

        @Override
        public int compareTo(NumberWrapper o) {
            return (o.num * base + num) - (num * o.base + o.num);
        }
    }

    public String _largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "0";
        if (nums.length == 1) return String.valueOf(nums[0]);

        Integer[] copied = new Integer[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 10);
        for (int i = 0; i < nums.length; i++) {
            copied[i] = nums[i];
            if (!map.containsKey(copied[i])) {
                int base = 1;
                while (nums[i] > 0) {
                    nums[i] = nums[i] / 10;
                    base *= 10;
                }
                map.put(copied[i], base);
            }
        }
        Arrays.sort(copied, (a, b) -> (b * map.get(a) + a) - (a * map.get(b) + b));

        if (copied[0] == 0) return "0";
        StringBuilder res = new StringBuilder();
        for (Integer integer : copied) {
            res.append(integer);
        }
        return res.toString();
    }
}
