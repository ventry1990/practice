package org.ventry.commons.leetcode.dp;

/**
 * file: org.ventry.commons.leetcode.dp.DecodeWays
 * author: ventry
 * create: 2019/4/8 10:58
 * description:
 */

public class DecodeWays {

    public int numDecodings(String s) {
        int len = 0;
        if (s == null || s.startsWith("0") || (len = s.length()) < 2) return len;

        // prevent pointer j out of dp
        s = s + "9";
        int[] fib = calcIncrement(len + 1);
        int[] dp = new int[len + 1];
        dp[0] = 1;
        for (int i = 0; i < len; ) {
            int j = i;
            while (j < len) {
                int digit = (s.charAt(j + 1) - '0');
                int num = (s.charAt(j) - '0') * 10 + digit;
                if (num == 0 || (digit == 0 && num > 26)) return 0;
                j++;
                if (num < 10 || num > 26 || digit == 0) {
                    if (num == 10 || num == 20)
                        j = Math.max(j - 1, i + 1);
                    break;
                }
            }

            dp[j] = dp[i] * fib[j - i];
            i = j;
        }

        return dp[len];
    }

    private int[] calcIncrement(int len) {
        int[] fib = new int[len];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < len; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    public int numDecodings2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int a = 1;
        int b = 1;
        for (int i = 1; i < n; i++) {
            int temp = b;
            int cur = s.charAt(i) - '0';
            int prev = s.charAt(i - 1) - '0';
            if (cur == 0) {
                b = a;
                if (prev == 0 || prev >= 3) {
                    return 0;
                }
            } else {
                if (prev != 0 && prev * 10 + cur <= 26) {
                    b += a;
                }
            }
            a = temp;
        }
        return b;
    }
}
