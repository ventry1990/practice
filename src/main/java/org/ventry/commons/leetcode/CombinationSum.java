package org.ventry.commons.leetcode;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.CombinationSum
 * author: ventry
 * create: 17/11/17 18:18
 * description:
 */

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0)
            return Collections.emptyList();

        Arrays.sort(candidates);
        Set<List<Integer>> res = new HashSet<>();
        sum(candidates, target, 0, res, new ArrayList<>());
        return new ArrayList<>(res);
    }

    private void sum(int[] nums, int target, int start, Set<List<Integer>> set, List<Integer> sol) {
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == target) {
                sol.add(target);
                set.add(sol);
            } else if (target - nums[i] >= nums[i]) {
                List<Integer> nextSol = new ArrayList<>(sol);
                nextSol.add(nums[i]);
                sum(nums, target - nums[i], i, set, nextSol);
            }
        }
    }

    private void search(int[] candidates, int st,
                        int target,
                        Integer[] paper, int len,
                        List<List<Integer>> ans) {
        if (target == 0) {
            final Integer[] temp = new Integer[len];
            System.arraycopy(paper, 0, temp, 0, len);
            ans.add(Arrays.asList(temp));
            return;
        }

        for(int i=st; i<candidates.length; i++) {
            if (i>st && candidates[i] == candidates[i-1]) continue;
            if (target < candidates[i]) break;
            paper[len] = candidates[i];
            search(candidates, i, target-candidates[i], paper, len+1, ans);
        }
    }
}
