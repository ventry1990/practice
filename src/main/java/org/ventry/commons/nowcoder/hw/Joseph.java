package org.ventry.commons.nowcoder.hw;

import java.util.Scanner;

/**
 * file: org.ventry.commons.nowcoder.hw.Joseph
 * author: ventry
 * create: 2020/3/15 23:13
 * description:
 */
public class Joseph {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            System.out.println(calc(n, 3));
        }
    }

    /**
     * https://blog.csdn.net/u011500062/article/details/72855826
     */
    private static int calc(int n, int m) {
        if (m < 2) return -1;
        if (n == 1) return 1;

        int winner = 0;
        for (int i = 2; i <= n; i++) {
            winner = (winner + m) % i;
        }
        return winner + 1;
    }
}
