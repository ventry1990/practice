package org.ventry.commons.leetcode;

import org.ventry.commons.utils.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * file: org.ventry.commons.leetcode.RandomPickIndex
 * author: jojo
 * create: 2017/7/25 14:35
 * description:
 */

public class RandomPickIndex {

    static class Solution {
        private static class KeyValue {
            private Integer key;
            private List<Integer> value;

            KeyValue(Integer key, List<Integer> value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "key=" + key;
            }
        }

        Random random;
        List<KeyValue> dict;

        public Solution(int[] nums) {
            random = new Random();
            dict = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int index = search(nums[i]);

                if (dict.size() <= index || dict.get(index).key != nums[i]) {
                    List<Integer> indices = new ArrayList<>();
                    indices.add(i);
                    if (dict.size() <= index) {
                        dict.add(new KeyValue(nums[i], indices));
                    } else if (dict.get(index).key > nums[i]) {
                        dict.add(index, new KeyValue(nums[i], indices));
                    } else {
                        dict.add(index + 1, new KeyValue(nums[i], indices));
                    }
                } else {
                    dict.get(index).value.add(i);
                }
            }
        }

        int search(int key) {
            int low = 0;
            int high = dict.size() - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                if (key < dict.get(mid).key) {
                    high = mid - 1;
                } else if (key > dict.get(mid).key) {
                    low = mid + 1;
                } else {
                    return mid;
                }
            }

            return low;
        }

        public int pick(int target) {
            int index = search(target);
            if (target != dict.get(index).key)
                return -1;

            List<Integer> indices = dict.get(index).value;
            int pickIndex = random.nextInt(indices.size());
            return indices.get(pickIndex);
        }
    }

    static class Solution2 {
        int[] nums;
        Random rnd;

        public Solution2(int[] nums) {
            this.nums = nums;
            this.rnd = new Random();
        }

        public int pick(int target) {
            int result = -1;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != target)
                    continue;
                if (rnd.nextInt(++count) == 0)
                    result = i;
            }

            return result;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        int size = 20;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = random.nextInt(size * 100);
        }
        Solution solution = new Solution(nums);
        Solution2 solution2 = new Solution2(nums);

        Console.write(20, nums);
        int target = nums[random.nextInt(size)];
        System.out.println(solution.pick(target));
        System.out.println(solution2.pick(target));
    }
}
