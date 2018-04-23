package org.ventry.commons.leetcode.string;

/**
 * file: org.ventry.commons.leetcode.string.ZigzagConversion
 * author: jojo
 * create: 2017/5/15 22:15
 * description:
 */

public class ZigzagConversion {

    public String convert(String s, int numRows) {
        if (numRows < 2 || s == null || s.length() <= numRows)
            return s;

        StringBuilder[] builder = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++)
            builder[i] = new StringBuilder();

        int k = 0;
        while (k < s.length()) {
            for (int i = 0; i < numRows && k < s.length(); i++) builder[i].append(s.charAt(k++));
            for (int i = numRows - 2; i > 0 && k < s.length(); i--) builder[i].append(s.charAt(k++));
        }

        for (int i = 1; i < numRows; i++)
            builder[0].append(builder[i]);

        return builder[0].toString();
    }

    public String convert2(String s, int numRows) {
        if (numRows < 2 || s == null || s.length() <= numRows)
            return s;

        char[] target = new char[s.length()];
        int row = 0;
        int step = (numRows - 1) * 2;
        for (int i = 0, j = 0, t = 0; i < target.length; i++) {
            target[i] = s.charAt(j);

            if (row == 0 || row == numRows - 1)
                j += step;
            else if (t == 0)
                j += step - row * 2;
            else
                j += row * 2;

            t ^= 1;
            if (j >= s.length()) {
                j = ++row;
                t = 0;
            }
        }

        return new String(target);
    }

    public static void main(String[] args) {
        ZigzagConversion zz = new ZigzagConversion();
        String s = "PAYPALISHIRINGABCDEFG";
        System.out.println(zz.convert(s, 5));
        System.out.println(zz.convert2(s, 5));
        /*  PAYPALISHIRINGABCDEFG
            PHCASIBDYIRAEPLIGFANG

            P       H       C
            A     S I     B D
            Y   I   R   A   E
            P L     I G     F
            A       N       G
         */
    }
}
