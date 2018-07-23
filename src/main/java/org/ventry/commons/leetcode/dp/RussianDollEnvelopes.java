package org.ventry.commons.leetcode.dp;

import java.util.Arrays;

/**
 * file: org.ventry.commons.leetcode.dp.RussianDollEnvelopes
 * author: ventry
 * create: 18/7/21 19:17
 * description:
 */

public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        int n;
        if ((n = envelopes.length) == 0 || envelopes[0].length == 0) {
            return 0;
        }

        sort(envelopes);

        int[] solution = new int[n];
        solution[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    max = Math.max(max, solution[j] + 1);
                }
            }
            solution[i] = max;
        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.max(result, solution[i]);
        }
        return result;
    }

    private void sort(int[][] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i][0] < arr[j][0] || (arr[i][0] == arr[j][0] && arr[i][1] < arr[j][1])) {
                    int[] t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        if (envelopes.length == 1) return 1;
        Arrays.sort(envelopes, (arr1, arr2) -> {
            if (arr1[0] == arr2[0]) {
                return arr2[1] - arr1[1];
            } else {
                return arr1[0] - arr2[0];
            }
        });

        int len = 0;
        int[] dp = new int[envelopes.length];
        for (int[] envelope : envelopes) {
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = envelope[1];
            if (index == len) len++;
        }
        return len;
    }
}
