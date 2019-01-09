package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.backtracking.Subsets
 * author: ventry
 * create: 2019/1/9 15:37
 * description:
 */

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
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

    public List<List<Integer>> subsets2(int[] nums) {
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
}
