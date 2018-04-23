package org.ventry.commons.leetcode.backtracking;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.backtracking.CombinationSumII
 * author: ventry
 * create: 17/11/20 14:09
 * description:
 */

public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return Collections.emptyList();

        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        search(candidates, target, 0, new Integer[candidates.length], 0, res);
        return new ArrayList<>(res);
    }

    private void search(int[] candidates, int target, int start,
                        Integer[] one, int length,
                        List<List<Integer>> res) {
        if (target == 0) {
            Integer[] temp = new Integer[length];
            System.arraycopy(one, 0, temp, 0, length);
            res.add(Arrays.asList(temp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) return;
            if (i != start && candidates[i] == candidates[i - 1]) continue;
            one[length] = candidates[i];
            search(candidates, target - candidates[i], i + 1, one, length + 1, res);
        }
    }
}
