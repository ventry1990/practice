package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import static org.ventry.commons.utils.ArrayUtils.swap;

/**
 * file: org.ventry.commons.leetcode.backtracking.Permutations
 * author: ventry
 * create: 18/4/23 17:02
 * description:
 */

public class Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums != null && nums.length > 0) {
            find(nums, 0, res);
        }
        return res;
    }

    private void find(int[] src, int start, List<List<Integer>> res) {
        if (start == src.length - 1) {
            List<Integer> ans = new ArrayList<>(src.length);
            for (int i : src) {
                ans.add(i);
            }
            res.add(ans);
        } else {
            for (int i = start; i < src.length; i++) {
                swap(src, start, i);
                find(src, start + 1, res);
                swap(src, start, i);
            }
        }
    }
}
