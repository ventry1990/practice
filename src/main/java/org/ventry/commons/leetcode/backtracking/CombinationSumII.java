package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        search(n, 1, new int[k], 0, res);
        return res;
    }

    private static void search(int target, int start, int[] one, int length,
                               List<List<Integer>> res) {
        if (length == one.length) {
            if (target == 0) {
                List<Integer> l = new ArrayList<>(length);
                for (int i : one) {
                    l.add(i);
                }
                res.add(l);
            }
            return;
        }

        for (int i = start; i < 10; i++) {
            if (target < i) return;
            one[length] = i;
            search(target - i, i + 1, one, length + 1, res);
        }
    }
}
