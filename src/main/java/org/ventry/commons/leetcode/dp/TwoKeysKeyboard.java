package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.TwoKeysKeyboard
 * author: ventry
 * create: 18/6/22 10:20
 * description:
 */

public class TwoKeysKeyboard {

    public int minSteps(int n) {
        if (n < 2)
            return 0;

        int[] result = new int[n + 1];
        result[1] = 0;
        result[n] = calcStepsRecursively(n, result);
        return result[n];
    }

    private int calcStepsRecursively(int n, int[] results) {
        int m = n / 2 + 1;
        for (int i = 2; i < m; i++) {
            if (n % i == 0) {
                int j = n / i;
                if (results[j] == 0) {
                    results[j] = calcStepsRecursively(j, results);
                }
                return results[j] + i;
            }
        }

        return n;
    }

    public int minSteps2(int n) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        return res;
    }
}
