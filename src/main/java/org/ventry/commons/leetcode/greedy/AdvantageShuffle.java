package org.ventry.commons.leetcode.greedy;

import java.util.*;

/**
 * file: org.ventry.commons.leetcode.greedy.AdvantageShuffle
 * author: ventry
 * create: 2019/3/12 14:08
 * description:
 */

public class AdvantageShuffle {

    public int[] advantageCount(int[] A, int[] B) {
        int len = A.length;
        if (len < 2)
            return A;

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < len; i++) {
            List<Integer> list = map.computeIfAbsent(B[i], k -> new ArrayList<>());
            list.add(i);
            map.put(B[i], list);
        }

        int[] res = new int[len];
        for (int i : A) {
            Map.Entry<Integer, List<Integer>> entry = map.lowerEntry(A[i]);
            if (entry == null) {
                entry = map.lastEntry();
            }
            List<Integer> indices = entry.getValue();
            int j = indices.remove(0);
            res[j] = i;
            if (indices.size() == 0) {
                map.remove(entry.getKey());
            }
        }

        return res;
    }

    public int[] advantageCount2(int[] A, int[] B) {
        Arrays.sort(A);
        int[] res = new int[A.length];
        boolean[] marked = new boolean[A.length];
        for (int i = 0; i < B.length; i++) {
            res[i] = findBigger(A, B[i], marked);
        }
        return res;
    }

    private int findBigger(int[] A, int b, boolean[] marked) {
        int pos = Arrays.binarySearch(A, b);
        if (pos < 0) {
            //insert position
            pos = -pos - 1;
        }
        while (pos < A.length) {
            if (A[pos] > b && !marked[pos]) break;
            pos++;
        }
        // not found bigger
        if (pos >= A.length) {
            pos = 0;
            while (pos < A.length && marked[pos])
                pos++;
        }
        marked[pos] = true;
        return A[pos];
    }
}
