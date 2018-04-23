package org.ventry.commons.leetcode.math;

/**
 * file: org.ventry.commons.leetcode.math.ElementaryArithmetic
 * author: ventry
 * create: 17/11/10 22:36
 * description:
 */

public class ElementaryArithmetic {

    static int divide(int dividend, int divisor) {
        if (divisor == 0)
            throw new ArithmeticException("/ by zero");
        if (dividend == 0)
            return 0;

        boolean negative = (dividend < 0 && divisor > 0)
                || (dividend > 0 && divisor < 0);
        long x = Math.abs((long) dividend);
        long y = Math.abs((long) divisor);
        long res = 0;
        for (int i = 31; i >= 0; i--) {
            if ((x >> i) >= y) {
                x -= (y << i);
                res += (1L << i);
            }
        }

        if (negative) {
            res = -res;
        }
        return res > Integer.MAX_VALUE
                ? Integer.MAX_VALUE
                : (res < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) res);
    }

    static int multiply(int multiplicand, int multiplier) {
        if (multiplicand == 0 || multiplier == 0)
            return 0;

        boolean negative = (multiplicand < 0 && multiplier > 0)
                || (multiplicand > 0 && multiplier < 0);
        long x = Math.abs((long) multiplicand);
        long y = Math.abs((long) multiplier);
        long res = 0;
        while (y > 0) {
            if ((y & 1) == 1)
                res += x;

            x <<= 1;
            y >>= 1;
        }

        if (negative) {
            res = -res;
        }
        return res > Integer.MAX_VALUE
                ? Integer.MAX_VALUE
                : (res < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) res);
    }

    static int plus(int x, int y) {
        if (x == 0)
            return y;

        int res = 0;
        while (y != 0) {
            res = x ^ y;
            y = (x & y) << 1;
            x = res;
        }
        return res;
    }

    static int minus(int x, int y) {
        if (y == 0)
            return x;
        if (x == 0)
            return -y;

        y = ElementaryArithmetic.plus(~y, 1);// 补码
        return ElementaryArithmetic.plus(x, y);
    }

    static double pow(double x, int n) {
        if (n == 0)
            return 1D;

        boolean negative = n < 0;
        if (negative) {
            x = 1 / x;
            n = -(n + 1);
        }

        double res = 1;
        double temp = x;
        while (n > 0) {
            if (n % 2 != 0) {
                res = res * temp;
            }
            temp *= temp;
            n = n / 2;
        }

        return negative ? res * x : res;
    }
}
