package org.ventry.commons.utils;

/**
 * file: org.ventry.commons.utils.MathUtils
 * author: ventry
 * create: 2018/12/17 18:35
 * description:
 */

public class MathUtils {

    public static int gcd(int a, int b) {
        if (a > b) {
            int t = b;
            b = a;
            a = t;
        }

        while (a > 0) {
            int r = b % a;
            b = a;
            a = r;
        }
        return b;
    }

    public static boolean isOdd(int num) {
        return (num & 1) == 1;
    }

    public static boolean isEven(int num) {
        return (~num & 1) == 1;
    }
}
