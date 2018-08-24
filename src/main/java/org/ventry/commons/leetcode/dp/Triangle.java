package org.ventry.commons.leetcode.dp;

import java.util.List;

/**
 * file: org.ventry.commons.leetcode.dp.Triangle
 * author: ventry
 * create: 18/8/17 15:50
 * description:
 */

public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0
                || triangle.get(0) == null || triangle.get(0).size() == 0)
            return 0;

        for (int i = triangle.size() - 2; i > -1; i--) {
            List<Integer> cur = triangle.get(i);
            List<Integer> next = triangle.get(i + 1);
            for (int j = 0; j < cur.size(); j++) {
                cur.set(j, Math.min(next.get(j), next.get(j + 1)) + cur.get(j));
            }
        }

        return triangle.get(0).get(0);
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] result = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                result[j] = Math.min(result[j], result[j + 1]) + triangle.get(i).get(j);
            }
        }
        return result[0];
    }
}