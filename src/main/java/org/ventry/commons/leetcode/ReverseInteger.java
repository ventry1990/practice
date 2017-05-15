package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.ReverseInteger
 * author: jojo
 * create: 2017/5/15 22:17
 * description:
 */

public class ReverseInteger {

    public int reverse(int x) {
        if (x == 0)
            return x;

        StringBuilder builder = new StringBuilder();
        if (x < 0) {
            builder.append("-");
            x = x * -1;
        }

        while (x > 0) {
            int i = x % 10;
            builder.append(i);
            x = x / 10;
        }

        // Handle integer overflow
        try {
            return Integer.valueOf(builder.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int reverse2(int x) {
        int result = 0;

        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            result = newResult;
            x = x / 10;
        }

        return result;
    }

    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        System.out.println(ri.reverse(123));
        System.out.println(ri.reverse(-123));
        System.out.println(ri.reverse(1534236469));
    }
}
