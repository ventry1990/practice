package org.ventry.commons.leetcode.backtracking;

import java.util.*;

import static org.ventry.commons.utils.ArrayUtils.swap;

/**
 * file: org.ventry.commons.leetcode.backtracking.PermutationsII
 * author: ventry
 * create: 18/4/23 17:43
 * description:
 */

public class PermutationsII {

    public List<List<Integer>> permuteUnique(int[] nums) {
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
                if (canSwap(src, start, i)) {
                    swap(src, start, i);
                    find(src, start + 1, res);
                    swap(src, start, i);
                }
            }
        }
    }

    /**
     * [startInclude, endExclude)区间内不能有元素与src[endExclude]重复，否则
     * 交换之后，[startInclude, endExclude]区间内存在重复元素，即存在重复的全排
     * 序列（子集）
     */
    private boolean canSwap(int[] src, int startInclude, int endExclude) {
        for (int i = startInclude; i < endExclude; i++) {
            if (src[i] == src[endExclude])
                return false;
        }
        return true;
    }
}
