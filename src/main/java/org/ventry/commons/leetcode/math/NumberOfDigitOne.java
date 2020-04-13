package org.ventry.commons.leetcode.math;

/**
 * file: org.ventry.commons.leetcode.math.NumberOfDigitOne
 * author: ventry
 * create: 2020/4/13 18:14
 * description:
 */
public class NumberOfDigitOne {
    private static final int[] MAP = new int[32];

    static {
        for (int i = 1; i < 32; i++) {
            MAP[i] = i * (int) Math.pow(10, i - 1);
        }
    }

    public int countDigitOne(int n) {
        if (n < 1) return 0;

        String s = String.valueOf(n);
        int high = s.charAt(0) - '0';
        int pow = (int) Math.pow(10, s.length() - 1);
        int low = n - high * pow;

        int count = high * MAP[s.length() - 1];
        if (high == 1) {
            count += low + 1; // count 1 at high
        } else {
            count += pow; // all 1 at high
        }
        count += countDigitOne(low);

        return count;
    }

    /**
     * https://leetcode.com/problems/number-of-digit-one/solution/
     */
    public int _countDigitOne(int n) {
        int res = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            res += n / divider * i + Math.min(Math.max(n % divider - i + 1, 0), i);
        }
        return res;
    }
}
