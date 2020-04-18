package org.ventry.commons.nowcoder.hw;

import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.HexToDec
 * author: ventry
 * create: 2020/3/13 19:02
 * description:
 */
public class HexToDec {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            char[] value = scanner.next().toUpperCase().toCharArray();
            long res = 0;
            for (int i = value.length - 1; i > 1; i--) {
                res += toDecimal(value[i]) * Math.pow(16, value.length - 1 - i);
            }
            System.out.println(res);
        }
    }

    private static int toDecimal(char c) {
        if (c <= '9' && c >= '0')
            return c - '0';
        else if (c <= 'F' && c >= 'A')
            return c - 'A' + 10;
        else
            return 0;
    }
}
