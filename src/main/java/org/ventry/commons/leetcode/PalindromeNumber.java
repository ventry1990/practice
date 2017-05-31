package org.ventry.commons.leetcode;

/**
 * file: org.ventry.commons.leetcode.PalindromeNumber
 * author: jojo
 * create: 2017/5/31 17:47
 * description:
 */

public class PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int temp = x;
        int result = 0;
        while (temp != 0) {
            int tail = temp % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return false;
            }
            result = newResult;
            temp = temp / 10;
        }

        return result == x;
    }

    public boolean is2(int x) {
        if (x < 0)
            return false;

        int temp = x;
        int result = 0;
        while (temp >= 10) {
            result = result * 10 + temp % 10;
            temp = temp / 10;
        }
        // 对比整个x的头尾两位，以及中间所有位reverse以后是否与不reverser时一致
        return temp == x % 10 && result == x / 10;
    }

    public static void main(String[] args) {
        PalindromeNumber pm = new PalindromeNumber();
        System.out.println(pm.isPalindrome(-1) + "-" + pm.is2(-1));
        System.out.println(pm.isPalindrome(-101) + "-" + pm.is2(-101));
        System.out.println(pm.isPalindrome(101) + "-" + pm.is2(101));
        System.out.println(pm.isPalindrome(Integer.MAX_VALUE) + "-" + pm.isPalindrome(Integer.MAX_VALUE));
        System.out.println(pm.isPalindrome(Integer.MIN_VALUE) + "-" + pm.is2(Integer.MIN_VALUE));
        System.out.println(pm.isPalindrome(1111) + "-" + pm.is2(1111));
        System.out.println(pm.isPalindrome(1234) + "-" + pm.is2(1234));
        System.out.println(pm.isPalindrome(2147447412) + "-" + pm.is2(2147447412));
        System.out.println(pm.isPalindrome(2147447419) + "-" + pm.is2(2147447419));
        System.out.println(pm.isPalindrome(20) + "-" + pm.is2(20));
    }
}
