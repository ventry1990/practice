package org.ventry.commons.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * file: org.ventry.commons.leetcode.backtracking.Combinations
 * author: ventry
 * create: 2018/12/17 16:49
 * description:
 */

public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 0 || k <= 0)
            return res;

        int[] one = new int[k + 1];
        backtrack(res, one, n, 1, k);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] one,
                           int n, int i, int k) {
        if (i == k + 1) {
            List<Integer> ans = new ArrayList<>(k);
            for (int j = 1; j < one.length; j++) {
                ans.add(one[j]);
            }
            res.add(ans);
            return;
        }

        for (int j = one[i - 1] + 1; j <= n; j++) {
            one[i] = j;
            backtrack(res, one, n, i + 1, k);
        }
    }
}
