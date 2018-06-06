package org.ventry.commons.leetcode.math;

/**
 * file: org.ventry.commons.leetcode.math.StringToInteger
 * author: jojo
 * create: 2017/5/16 14:12
 * description:
 */

public class StringToInteger {

    public int myAtoi(String str) {
        if (str == null || str.length() == 0)
            return 0;

        char[] chars = str.trim().toCharArray();
        boolean symbolAtFirstChar = chars[0] == '-' || chars[0] == '+';
        int symbol = chars[0] == '-' ? -1 : 1;
        double total = 0;
        for (int i = symbolAtFirstChar ? 1 : 0; i < chars.length; i++) {
            if (48 > chars[i] || chars[i] > 57)
                break;

            int tail = chars[i] - 48;

            total = total * 10 + tail;
        }

        total = total * symbol;
        if (total > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else if (total < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else return (int) total;
    }

    public int atoi2(String str) {
        int index = 0, sign = 1, total = 0;
        // 1. Empty string
        if (str == null || str.length() == 0) return 0;

        // 2. Remove Spaces
        while (str.charAt(index) == ' ' && index < str.length())
            index++;

        // 3. Handle signs
        if (str.charAt(index) == '+' || str.charAt(index) == '-') {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // 4. Convert number and avoid overflow
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;

            // Check if total will be overflow after 10 times and add digit
            if (Integer.MAX_VALUE / 10 < total || Integer.MAX_VALUE / 10 == total && Integer.MAX_VALUE % 10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index++;
        }
        return total * sign;
    }

    public static void main(String[] args) {
        StringToInteger sti = new StringToInteger();
        System.out.println(sti.myAtoi("-123"));
        System.out.println(sti.myAtoi("123.01"));
        System.out.println(sti.myAtoi("+123.01"));
        System.out.println(sti.myAtoi("2147483647"));
        System.out.println(sti.myAtoi("2147483648"));
        System.out.println(sti.myAtoi("10522545459"));
        System.out.println(sti.myAtoi("-2147483647"));
        System.out.println(sti.myAtoi("-2147483648"));
        System.out.println(sti.myAtoi("-2147483649"));
        System.out.println(sti.myAtoi("-10522545459"));
    }
}
