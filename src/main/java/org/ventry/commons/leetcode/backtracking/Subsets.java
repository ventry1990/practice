package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.backtracking.Subsets
 * author: ventry
 * create: 2019/1/9 15:37
 * description:
 */

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }

    public List<List<Integer>> _subsets(int[] nums) {
        List<Integer> src = new ArrayList<>(nums.length);
        for (int num : nums) {
            src.add(num);
        }
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), src, 0);
        res.add(new ArrayList<>());
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> one, List<Integer> nums, int start) {
        int size = nums.size();
        for (int i = start; i < size; i++) {
            one.add(nums.get(i));
            res.add(new ArrayList<>(one));
            backtrack(res, one, nums, i + 1);
            one.remove(one.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        result.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> subset = new ArrayList<>(result.get(j));
                subset.add(nums[i]);
                result.add(subset);
            }

            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                int newSize = result.size();
                for (int j = size; j < newSize; j++) {
                    List<Integer> subset = new ArrayList<>(result.get(j));
                    subset.add(nums[i]);
                    result.add(subset);
                }
                i++;
            }
        }
        return result;
    }
}
